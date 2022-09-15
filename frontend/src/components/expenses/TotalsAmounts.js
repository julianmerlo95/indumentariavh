import React from 'react';
import {expensesList, paymentMethodList} from '../../config/config';
import './Expenses.sass';

function TotalsAmounts(props) {
    return (
        <div className="invoice-total-details">
            <div className="invoice-total-details-discount-currentAccount invoice-total-details-data-expense">
                <form onSubmit={(event) => props.saveAmountHandler(event)}>
                    <div>
                        <h4>MONTO</h4>
                        <p>Este monto corresponde al gasto que se va a realizar</p>
                        <div>
                            <label>
                                <input className="input-invoice-from-input" type="text" name="amount"
                                       placeholder="Ingresar monto" onChange={(e) => props.onChangeHandler(e)}/>
                            </label>
                            <input className="search-client-from-input" type="submit" value="Ingresar"/>
                        </div>
                    </div>
                </form>
                <hr/>
                <div>
                    <form onSubmit={(event) => props.expensesSelectedHandler(event)}>
                        <h4>TIPO DE GASTOS</h4>
                        <table className="table table-body">
                            <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nombre</th>
                            </tr>
                            </thead>
                            <tbody>
                            {expensesList.map((expense, index) => {
                                return (
                                    <tr key={index} className="table table-body-data">
                                        <th scope="row">{expense.idExpenses}</th>
                                        <th scope="row">{expense.name}</th>
                                        <input onClick={(e) => props.onChangeHandler(e)}
                                               name={expense.name} className="search-client-from-input button-payment-method" type="submit" value="Elegir"/>
                                    </tr>
                                )})}
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
            <div className="invoice-total-details-total-expense"> {/* Total details */}
                <h4>DETALLE DEL GASTO</h4>
                <hr/>
                <br/>
                <h5>Monto: ${new Intl.NumberFormat("es-CL").format(props.amount)} </h5>
                <h5>Tipo de gasto: {props.expensesMethodSelected.name} </h5>
                <br/>
                <hr/>
            </div>
        </div>
    )
}

export default TotalsAmounts;