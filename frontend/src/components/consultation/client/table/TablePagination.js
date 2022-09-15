import React from "react";
import Button from "@mui/material/Button";
import './TablePagination.sass';

const TablePagination = (props) => {
    let arrayPage = [];
    const {
        quantityItemShow,
        invoicesLength,
        clients,
        pagination,
        clientShowHandler
    } = props;

    for (
        let i = 1;i <= Math.ceil(invoicesLength / quantityItemShow);i++) {
        arrayPage.push(i);
    }

    return (
        <div>
            <div className="table-responsive"> {/* Show client data */}
                <table className="table table-client">
                    <thead>
                    <tr className="table table-client-colum">
                        <th scope="col">Id</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Apellido</th>
                        <th scope="col">Cumpleaños</th>
                        <th scope="col">Dni</th>
                        <th scope="col">Mail</th>
                        <th scope="col">Estado</th>
                        <th scope="col">Deuda</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    {clients && clients.length > 0 ? clients.map((client, index) => {
                        return (
                            <tr key={index}>
                                <th scope="row">{client.idClient}</th>
                                <th scope="row">{client.name}</th>
                                <th scope="row">{client.lastName}</th>
                                <th scope="row">{client.dateOfBirth}</th>
                                <th scope="row">{client.dni}</th>
                                <th scope="row">{client.mail}</th>
                                <th scope="row">{client.isEnable === 1 ? "HABILITADO" : "DESHABILITADO"}</th>
                                <th scope="row">${new Intl.NumberFormat("es-CL").format(Number(client.moneyDebt))}</th>
                                <Button className="client-high-button-select" variant="contained"
                                        size="small" color="success"
                                        onClick={() => clientShowHandler(client)}>Editar
                                </Button>
                            </tr>
                        )
                    }) : <div><h4 className="not-found-items">No se encontraron resultados, intente buscar nuevamente</h4></div>}
                    </tbody>
                </table>
            </div>
            <div className="numbers_pagination">
                <h5>Paginas: </h5>
                {arrayPage.map((page, index) => {
                    return (
                        <div key={index}>
                            <h5 className="numbers_pagination-h2" onClick={() => pagination(page)}>{page}</h5>
                        </div>
                    );
                })}
            </div>
        </div>
    );
};

export default TablePagination;