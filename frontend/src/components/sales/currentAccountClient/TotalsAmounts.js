import React from 'react';

function TotalsAmounts(props) {
    return (
        <div className="invoice-total-details">
            <div className="invoice-total-details-discount-currentAccount">
                <form onSubmit={(event) => props.saveAmountHandler(event)}>
                    <div>
                        <h4>MONTO A ENTREGAR</h4>
                        <p>El siguiente monto corresponde a un pago total de la deuda o entrega de dinero por parte
                            del cliente</p>
                        <div>
                            <label>
                                <input className="input-invoice-from-input" type="text" name="amountDebit"
                                       placeholder="Ingresar monto" onChange={(e) => props.onChangeHandler(e)}/>
                            </label>
                            <input className="search-client-from-input" type="submit" value="Ingresar"/>
                        </div>
                    </div>
                </form>
            </div>
            <div className="invoice-total-details-payment_method-currentAccount">
                <div>
                    <form onSubmit={(event) => props.saveAmountHandler(event)}>
                        <h4>MONTO FIADO</h4>
                        <p>El siguiente monto corresponde a un fiado que el cliente desea obtener</p>
                        <div>
                            <div>
                                <label>
                                    <input className="input-invoice-from-input" type="text" name="amountCredit"
                                           placeholder="Ingresar monto" onChange={(e) => props.onChangeHandler(e)}/>
                                </label>
                                <input className="search-client-from-input" type="submit" value="Ingresar"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div className="invoice-total-details-total-currentAccount"> {/* Total details */}
                <h4>DETALLE TOTAL DE LA FACTURA</h4>
                <hr/>
                <h5>Actual deuda: $<span className="moneyDebt-client-span">{new Intl.NumberFormat("es-CL").format(props.clientSelected.moneyDebt)}</span></h5>
                <hr/>
                <h5>Monto a entregar: ${new Intl.NumberFormat("es-CL").format(props.amountDebit)} </h5>
                <h5>Monto fiado: ${new Intl.NumberFormat("es-CL").format(props.amountCredit)} </h5>
                <hr/>
                <h5>Nueva deuda: $<span className="moneyDebt-client-span">{new Intl.NumberFormat("es-CL").format((Number(props.totalInvoice) - Number(props.amountDebit)) + Number(props.amountCredit))}</span> </h5>
            </div>
        </div>
    )
}

export default TotalsAmounts;