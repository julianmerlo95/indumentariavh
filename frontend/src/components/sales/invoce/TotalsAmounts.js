import React from 'react';
import {paymentMethodList} from "../../../config/config";
import Button from '@mui/material/Button';

function TotalsAmounts(props) {
    return (
        <div className="invoice-total-details">
            <div className="invoice-total-details-discount"> {/* Discounts */}
                <form onSubmit={(event) => props.saveDiscountHandler(event)}>
                    <div>
                        <h4>DESCUENTO GLOBAL</h4>
                        <div>
                            <label>
                                <input className="input-invoice-from-input" type="number" name="discount" placeholder="Ingresar monto"
                                       onChange={(e) => props.onChangeHandler(e)}/>
                            </label>
                            <input className="search-client-from-input" type="submit" value="Ingresar"/>
                        </div>
                    </div>
                </form>
            </div>
            <div className="invoice-total-details-payment_method"> {/* Payment method */}
                <div>
                    <h4>FORMAS DE PAGO</h4>
                    <div className="table table-body table-responsive">
                        <form onSubmit={(event) => props.paymentMethodSelectedHandler(event)}>
                            <table className="table table-body">
                                <thead>
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">Nombre</th>
                                </tr>
                                </thead>
                                <tbody>
                                {paymentMethodList.map((paymentMethod, index) => {
                                    return (
                                        <tr key={index} className="table table-body-data">
                                            <th scope="row">{paymentMethod.idPaymentMethod}</th>
                                            <th scope="row">{paymentMethod.name}</th>
                                            <label>
                                                <input className="input-invoice-from-input" type="number"
                                                       name={paymentMethod.name} placeholder="Ingresar monto"
                                                       onChange={(e) => props.onChangeHandler(e)}/>
                                            </label>
                                            <input name={paymentMethod.name} className="search-client-from-input button-payment-method" type="submit" value="Ingresar"/>
                                        </tr>
                                    )})}
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
            <div className="invoice-total-details-total"> {/* Total details */}
                <h4>DETALLE TOTAL DE LA FACTURA</h4>
                <>
                    {props.listProductSelected ? (
                        <>
                            <hr/>
                            <h6>Descuento: ${new Intl.NumberFormat("es-CL").format(props.discount)} </h6>
                            <hr/>
                            <h5>FORMAS DE PAGO</h5>
                            <h6>Efectivo: ${new Intl.NumberFormat("es-CL").format(props.cash)}</h6>
                            <h6>Tarjeta: ${new Intl.NumberFormat("es-CL").format(props.credit)}</h6>
                            <h6>Fiado: ${new Intl.NumberFormat("es-CL").format(props.borrowedCash)}</h6>
                            <h6>Total formas de pago: ${(new Intl.NumberFormat("es-CL").format(Number(props.cash) + Number(props.credit) + Number(props.borrowedCash)))}
                            </h6>
                            <hr/>
                            <h5>TOTAL FACTURA: ${new Intl.NumberFormat("es-CL").format(Number(props.totalItems) - Number(props.discount))} </h5>
                        </>
                    ) : (
                        <h4>Total: $0</h4>
                    )}
                </>
            </div>
        </div>
    )
}

export default TotalsAmounts;