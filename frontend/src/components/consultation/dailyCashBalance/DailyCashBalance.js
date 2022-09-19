import React, {useEffect, useState} from 'react';
import './DailyCashBalance.sass'
import {Loading} from "../../loading/Loading";
import NavbarComponent from "../../UI/header/Navbar";
import axios from "axios";
import imgCLient from "../../../assets/img-client-consultation.png";
import Footer from "../../UI/footer/Footer";

function DailyCashBalance() {

    const date = new Date();
    // Invoice
    let {isAuthenticated} = useAuth0();
    let [invoices, setInvoice] = useState(0);
    let [discount, setDiscount] = useState(0);
    let [invoiceTotal, setInvoiceTotal] = useState(0);
    let [invoiceTotalCash, setInvoiceTotalCash] = useState(0);
    let [invoiceTotalCard, setInvoiceTotalCard] = useState(0);
    let [invoiceTotalFiado, setInvoiceTotalFiado] = useState(0);
    // Current account client
    let [currentAccountClient, setCurrentAccountClient] = useState(0);
    let [moneyCredit, setMoneyCredit] = useState(0);
    let [moneyDebt, setMoneyDebt] = useState(0);
    // Expenses
    let [expenses, setExpenses] = useState(0);
    let [amount, setAmount] = useState(0);

    useEffect(() => {
        axios.get(`${process.env.REACT_APP_API_URL}/bills/today`).then((response) => {
            setInvoice(response.data.length)
            response.data.map((invoice) => {

                if (invoice.totalAmount) {
                    invoiceTotal += Number(invoice.totalAmount) / 2
                    setInvoiceTotal(invoiceTotal)
                }

                if (invoice.totalDiscount > 0) {
                    discount += Number(invoice.totalDiscount) / 2
                    setDiscount(discount)
                }
                if (invoice.cash > 0) {
                    invoiceTotalCash += Number(invoice.cash) / 2
                    setInvoiceTotalCash(invoiceTotalCash)
                }
                if (invoice.credit > 0) {
                    invoiceTotalCard += Number(invoice.credit) / 2
                    setInvoiceTotalCard(invoiceTotalCard)
                }
                if (invoice.borrowedCash > 0) {
                    invoiceTotalFiado += Number(invoice.borrowedCash) / 2
                    setInvoiceTotalFiado(invoiceTotalFiado)
                }
                return
            })
        });
        axios.get(`${process.env.REACT_APP_API_URL}/current-account-client/today`).then((response) => {
            setCurrentAccountClient(response.data.length)
            response.data.map((money) => {
                if (Number(money.moneyCredit) > 0) {
                    moneyCredit += Number(money.moneyCredit) / 2
                    setMoneyCredit(moneyCredit)
                }

                if (Number(money.moneyDebit) > 0) {
                    moneyDebt += Number(money.moneyDebit) / 2
                    setMoneyDebt(moneyDebt)
                }
            });
        });
        axios.get(`${process.env.REACT_APP_API_URL}/expenses`).then((response) => {
            setExpenses(response.data.length)
            response.data.map((expense) => {
                if (Number(expense.amount) > 0) {
                    amount += Number(expense.amount) / 2
                    setAmount(amount)
                }
            });
        });
    }, []);

    return (
        <div>
                <div>
                    <NavbarComponent></NavbarComponent>
                    <div>
                        <div>
                            <div className="search-client-header">
                                <div className="search-client-header-img">
                                    <img alt="client-consultation-img" className="client-consultation-img" src={imgCLient}/>
                                </div>
                                <div className="search-client-header-title">
                                    <h1 className="table-title">| Balance de caja diario</h1>
                                </div>
                            </div>
                            <div>
                                <h3 className="daily-cash-date">FECHA DE CAJA: [{date.toLocaleString()}]</h3>
                            </div>
                            <div className="totals-show-data-daily">
                                <h3>Totales sumados: ${new Intl.NumberFormat("es-CL").format((invoiceTotalCash + invoiceTotalCard + moneyDebt) - amount)}</h3>
                                <p>Se suma el monto en efectivo, tarjeta y las facturas fiadas pagadas y se restan los gastos</p>
                            </div>
                            <div className="daily-cash-show-info">
                                <div className="daily-cash-show-info-left">
                                    <h2>VENTAS</h2>
                                    <hr/>
                                    <h4>Total cobrado con efectivo: ${new Intl.NumberFormat("es-CL").format(invoiceTotalCash)}</h4>
                                    <hr/>
                                    <h4>Total cobrado con tarjeta: ${new Intl.NumberFormat("es-CL").format(invoiceTotalCard)}</h4>
                                    <hr/>
                                    <h4>Total cobrado con fiado: ${new Intl.NumberFormat("es-CL").format(invoiceTotalFiado)}</h4>
                                    <hr/>
                                    <h4>Descuentos tolales en facturas: ${new Intl.NumberFormat("es-CL").format(discount)}</h4>
                                    <h4>Total facturas del tipo ventas: {invoices}</h4>
                                    <h4>Total facturado: ${new Intl.NumberFormat("es-CL").format(invoiceTotal)}</h4>
                                </div>
                                <div className="daily-cash-show-info-right">
                                    <h2>CUENTA CORRIENTE/FIADO Y GASTOS</h2>
                                    <hr/>
                                    <h4>Total fiado: ${new Intl.NumberFormat("es-CL").format(moneyCredit)}</h4>
                                    <h4>Total pagado: ${new Intl.NumberFormat("es-CL").format(moneyDebt)}</h4>
                                    <h4>Total facturas del tipo fiado: {currentAccountClient}</h4>
                                    <hr/>
                                    <h4>Total gastos: ${new Intl.NumberFormat("es-CL").format(amount)}</h4>
                                    <h4>Total facturas del tipo gasto: {expenses}</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                    <Footer></Footer>
                </div>
        </div>
    )
}

export default DailyCashBalance;