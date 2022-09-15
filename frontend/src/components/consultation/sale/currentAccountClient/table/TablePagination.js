import React from "react";
import './TablePagination.sass';

const TablePagination = (props) => {
    let arrayPage = [];
    const {
        quantityItemShow,
        invoicesLength,
        currentAccountClient,
        pagination,
    } = props;

    for (
        let i = 1;i <= Math.ceil(invoicesLength / quantityItemShow);i++) {
        arrayPage.push(i);
    }

    return (
        <div className="competition">
            <div className="table-responsive"> {/* Show client data */}
                <table className="table table-client">
                    <thead>
                    <tr className="table table-client-colum">
                        <th scope="col">id client</th>
                        <th scope="col">Vendedor</th>
                        <th scope="col">Fecha</th>
                        <th scope="col">Deuda a la fecha</th>
                        <th scope="col">Monto entregado</th>
                        <th scope="col">Monto fiado</th>
                        <th scope="col">Nueva deuda</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    {currentAccountClient && currentAccountClient.length > 0 ? currentAccountClient.map((client, index) => {
                        return (
                            <tr key={index}>
                                <th scope="row">{client.idClient}</th>
                                <th scope="row">{client.userName}</th>
                                <th scope="row">{client.dateRegister.slice(0, 10)}</th>
                                <th scope="row">${new Intl.NumberFormat("es-CL").format(client.actualMoneyDebt)}</th>
                                <th scope="row">${new Intl.NumberFormat("es-CL").format(client.moneyDebit)}</th>
                                <th scope="row">${new Intl.NumberFormat("es-CL").format(client.moneyCredit)}</th>
                                <th scope="row">${new Intl.NumberFormat("es-CL").format(client.newMoneyDebt)}</th>
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