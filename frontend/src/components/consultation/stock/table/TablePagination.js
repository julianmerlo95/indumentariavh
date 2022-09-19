import React from "react";
import Button from "@mui/material/Button";
import './TablePagination.sass';


const TablePagination = (props) => {
    const {user} = useAuth0();
    let arrayPage = [];
    const {
        quantityItemShow,
        invoicesLength,
        stocks,
        pagination,
        productShowHandler
    } = props;

    for (
        let i = 1;i <= Math.ceil(invoicesLength / quantityItemShow);i++) {
        arrayPage.push(i);
    }

    return (
        <div>
            <div className="table-responsive"> {/* Show stock data */}
                <table className="table table-client">
                    <thead>
                    <tr className="table table-client-colum">
                        <th scope="col">Id producto</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Descripcion</th>
                        <th scope="col">Color</th>
                        <th scope="col">Talle</th>
                        <th scope="col">Cantidad</th>
                        {user.nickname == "pame" ? <th scope="col"></th> : <th scope="col">Precio de compra</th>}
                        <th scope="col">Precio de venta</th>
                        <th scope="col">Realidad aumentada</th>
                        <th scope="col">Estado</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    {stocks && stocks.length > 0 ? stocks.map((stock, index) => {
                        return (
                            <tr key={index}>
                                <th scope="row">{stock.idProduct}</th>
                                <th scope="row">{stock.name}</th>
                                <th scope="row">{stock.description}</th>
                                <th scope="row">{stock.colour}</th>
                                <th scope="row">{stock.waist}</th>
                                <th scope="row">{stock.quantity}</th>
                                <th scope="row">{user.nickname == "pame" ? "" : `$${new Intl.NumberFormat("es-CL").format(Number(stock.purchasePrice))}`}</th>
                                <th scope="row">${new Intl.NumberFormat("es-CL").format(Number(stock.salePrice))}</th>
                                <th scope="row">{stock.isAugmentedReality === 1 ? "HABILITADO" : "DESHABILITADO"}</th>
                                <th scope="row">{stock.isEnable === 1 ? "HABILITADO" : "DESHABILITADO"}</th>
                                <Button className="client-high-button-select" variant="contained" size="small" color="success"
                                        onClick={() => productShowHandler(stock)}>Editar
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
                            <h5 className="numbers_pagination-h2" onClick={() => pagination(page)}>{page}|</h5>
                        </div>
                    );
                })}
            </div>
        </div>
    );
};

export default TablePagination;