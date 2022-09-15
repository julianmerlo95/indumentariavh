import React from "react";
import './TablePagination.sass';

const TablePagination = (props) => {
    let arrayPage = [];
    const {
        quantityItemShow,
        invoicesLength,
        invoices,
        pagination,
    } = props;

    for (
        let i = 1;i <= Math.ceil(invoicesLength / quantityItemShow);i++) {
        arrayPage.push(i);
    }

    return (
        <div className="competition">
            <div className="table-responsive">
                <table className="table table-client">
                    <thead>
                    <tr className="table table-client-colum">
                        <th scope="col">Id factura</th>
                        <th scope="col">Id vendedor</th>
                        <th scope="col">Id cliente</th>
                        <th scope="col">Metodo de pago</th>
                        <th scope="col">Fecha</th>
                        <th scope="col">Descuento</th>
                        <th scope="col">Efectivo</th>
                        <th scope="col">Tarjeta</th>
                        <th scope="col">Fiado</th>
                        <th scope="col">Total</th>
                    </tr>
                    </thead>
                    <tbody>
                    {invoices && invoices.length > 0 ? invoices.map((invoice, index) => {
                        return (
                            <tr key={index}>
                                <th scope="row">{invoice.idBill}</th>
                                <th scope="row">{invoice.idUser}</th>
                                <th scope="row">{invoice.idClient}</th>
                                <th scope="row">{invoice.idPaymentMethod === 1 ? "EFECTIVO" : invoice.idPaymentMethod === 2 ? "TARJETA" : "FIADO" }</th>
                                <th scope="row">{invoice.dateRegister.slice(0, 10)}</th>
                                <th scope="row">${new Intl.NumberFormat("es-CL").format(invoice.totalDiscount)}</th>
                                <th scope="row">${new Intl.NumberFormat("es-CL").format(invoice.cash)}</th>
                                <th scope="row">${new Intl.NumberFormat("es-CL").format(invoice.credit)}</th>
                                <th scope="row">${new Intl.NumberFormat("es-CL").format(invoice.borrowedCash)}</th>
                                <th scope="row">${new Intl.NumberFormat("es-CL").format(invoice.totalAmount)}</th>
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
                            <h5 className="numbers_pagination-h2" onClick={() => pagination(page)}> {page}|</h5>
                        </div>
                    );
                })}
            </div>
        </div>
    );
};

export default TablePagination;