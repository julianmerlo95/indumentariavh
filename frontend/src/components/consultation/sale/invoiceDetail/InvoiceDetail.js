import React, {useState} from 'react';
import './InvoiceDetail.sass';
import axios from "axios";
import imgCLient from '../../../../assets/img-client-consultation.png';
import {Loading} from '../../../loading/Loading'

import NavbarComponent from "../../../UI/header/Navbar";
import Footer from "../../../UI/footer/Footer";

function InvoiceDetail() {

    let inputValue;
    let inputName;

    
    const [invoices, setInvoice] = useState([]);

    const onChangeHandler = (e) => {
        if (e.target.value.length >= 20) {
            alert("Los campos tienen un maximo de 20 caracteres")
            return
        }
        inputValue = e.target.value;
        inputName = e.target.name;
        return inputValue;
    };

    const searchInvoiceDetailHandler = async (event) => {
        try {
            let url = `${process.env.REACT_APP_API_URL}/bills/${inputValue}/details`
            event.preventDefault();

            if (inputValue?.length >= 20 || inputValue === "" || inputValue === undefined) {
                alert("Debe ingresar algun dato para poder realizar la busqueda o ser menor a 20 caracteres")
                return
            }

            axios.get(url).then(response => { if (response.data.error) { setInvoice([]) } setInvoice(response.data) });
        } catch (ex) {
            window.location.replace('/error')
            throw ex
        }
    }

    return (
        <div>
            
                <div>
                    <NavbarComponent></NavbarComponent>
                    <div>
                        <div className="search-client-header">
                            <div className="search-client-header-img">
                                <img alt="client-consultation-img" className="client-consultation-img" src={imgCLient}/>
                            </div>
                            <div className="search-client-header-title">
                                <h1 className="table-title">| Detalle de una factura</h1>
                            </div>
                        </div>
                        <div className="search-client">
                            <form className="form search-client-from" onSubmit={(event) => searchInvoiceDetailHandler(event)}>
                                <label>
                                    <input type="number" name="id" placeholder="Buscar por id de factura"
                                           onChange={(e) => onChangeHandler(e)}/>
                                </label>
                                <input className="search-client-from-input" type="submit" value="Buscar"/>
                            </form>
                        </div>
                        <div>
                            <div className="container-gray-data">
                                <h4 className="table-title">TOTAL SUMARIZADO DE DETALLES:
                                    ${new Intl.NumberFormat("es-CL").format(invoices?.reduce((a, b) => Number(a) + Number(b.price), 0))}
                                </h4>
                            </div>
                        </div>
                        <div>
                            <div className="table-responsive">
                                <table className="table table-client">
                                    <thead>
                                    <tr className="table table-client-colum">
                                        <th scope="col">Id factura</th>
                                        <th scope="col">Cantidad</th>
                                        <th scope="col">Precio</th>
                                        <th scope="col">idProducto</th>
                                        <th scope="col">Color</th>
                                        <th scope="col">Talle</th>
                                        <th scope="col">Fecha</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {invoices && invoices.length > 0 ? invoices.map((invoice, index) => {
                                        return (
                                            <tr key={index}>
                                                <th scope="row">{invoice.idBill}</th>
                                                <th scope="row">{invoice.quantity}</th>
                                                <th scope="row">${new Intl.NumberFormat("es-CL").format(invoice.price)}</th>
                                                <th scope="row">{invoice.idProduct}</th>
                                                <th scope="row">{invoice.colour}</th>
                                                <th scope="row">{invoice.waist}</th>
                                                <th scope="row">{invoice.dateRegister.slice(0, 10)}</th>
                                            </tr>
                                        )
                                    }) : <div><h4 className="not-found-items">No se encontraron resultados, intente buscar nuevamente</h4></div>}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <Footer></Footer>
                </div>
        </div>
    )
}

export default InvoiceDetail;