import React from "react";
import './TablePagination.sass';

const TablePagination = (props) => {
    let arrayPage = [];
    const {
        quantityItemShow,
        invoicesLength,
        expenses,
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
                        <th scope="col">id usuario</th>
                        <th scope="col">Tipo de gasto</th>
                        <th scope="col">Fecha</th>
                        <th scope="col">Monto</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    {expenses && expenses.length > 0 ? expenses.map((expense, index) => {
                        return (
                            <tr key={index}>
                                <th scope="row">{expense.idUser}</th>
                                <th scope="row">{expense.expenseName}</th>
                                <th scope="row">{expense.createdBillDate.slice(0, 10)}</th>
                                <th scope="row">${new Intl.NumberFormat("es-CL").format(expense.amount)}</th>
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