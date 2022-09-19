import React, {useState, useEffect} from 'react';
import './InvoiceConsultation.sass';
import axios from "axios";
import imgCLient from '../../../../assets/img-client-consultation.png';
import {Loading} from '../../../loading/Loading'
import NavbarComponent from "../../../UI/header/Navbar";
import Footer from "../../../UI/footer/Footer";
import TablePagination from "./table/TablePagination";


function InvoiceConsultation() {

    let inputValue;
    let inputName;

    const [invoices, setInvoice] = useState([]);

    // Table pagination
    const [pageInitial, setPageInitial] = useState(1);
    const quantityItemShow = 25;
    const newQuantityShow = pageInitial * quantityItemShow;
    const lastQuantityShow = newQuantityShow - quantityItemShow;
    const dataShow = invoices.slice(lastQuantityShow, newQuantityShow)
    const pagination = (page) => setPageInitial(page)

    useEffect(() => {
        axios.get(`${process.env.REACT_APP_API_URL}/bills`).then((response) => setInvoice(response.data));
    }, []);

    const onChangeHandler = (e) => {
        if (e.target.value.length >= 20) {
            alert("Los campos tienen un maximo de 20 caracteres")
            return
        }
        inputValue = e.target.value;
        inputName = e.target.name;
        return inputValue;
    };

    const searchInvoiceHandler = async (event) => {
        try {
            let url;
            event.preventDefault();

            if (inputValue?.length >= 20 || inputValue === "" || inputValue === undefined) {
                alert("Debe ingresar algun dato para poder realizar la busqueda o ser menor a 20 caracteres")
                return
            }

            if (inputName === "id") {
                url = `${process.env.REACT_APP_API_URL}/bills/${inputValue}`
            } else {
                url = `${process.env.REACT_APP_API_URL}/bills?${inputName}=${inputValue}`
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
                                <h1 className="table-title">| Listado de ventas</h1>
                            </div>
                        </div>
                        <div className="search-client">
                            <form className="form search-client-from" onSubmit={(event) => searchInvoiceHandler(event)}>
                                <label>
                                    <input type="number" name="id" placeholder="Buscar por id"
                                           onChange={(e) => onChangeHandler(e)}/>
                                </label>
                                <input className="search-client-from-input" type="submit" value="Buscar"/>
                                <label>
                                    <input type="number" name="idUser" placeholder="Buscar por id vendedor"
                                           onChange={(e) => onChangeHandler(e)}/>
                                </label>
                                <input className="search-client-from-input" type="submit" value="Buscar"/>
                                <label>
                                    <input type="text" name="idClient" placeholder="Buscar por id cliente"
                                           onChange={(e) => onChangeHandler(e)}/>
                                </label>
                                <input className="search-client-from-input" type="submit" value="Buscar"/>
                                <label>
                                    <input type="text" name="fromDate" placeholder="Buscar por fecha"
                                           onChange={(e) => onChangeHandler(e)}/>
                                </label>
                                <input className="search-client-from-input" type="submit" value="Buscar"/>
                            </form>
                        </div>
                        <div className="totals-show-data">
                            <div className="totals-show-data-left">
                                <h5 className="table-title">TOTAL EFECTIVO:
                                    ${new Intl.NumberFormat("es-CL").format(invoices?.reduce((a, b) => Number(a) + Number(b.cash), 0))}
                                </h5>
                                <h5 className="table-title">TOTAL TARJETA:
                                    ${new Intl.NumberFormat("es-CL").format(invoices?.reduce((a, b) => Number(a) + Number(b.credit), 0))}
                                </h5>
                                <h5 className="table-title">TOTAL FIADO:
                                    ${new Intl.NumberFormat("es-CL").format(invoices?.reduce((a, b) => Number(a) + Number(b.borrowedCash), 0))}
                                </h5>
                            </div>
                            <div className="totals-show-data-right">
                                <h4 className="table-title">TOTAL SUMARIZADO DE VENTAS:
                                    ${new Intl.NumberFormat("es-CL").format(invoices?.reduce((a, b) => Number(a) + Number(b.totalAmount), 0))}
                                </h4>
                            </div>
                        </div>
                        <div>
                            <TablePagination
                                quantityItemShow={quantityItemShow}
                                invoicesLength={invoices.length}
                                invoices={dataShow}
                                pagination={pagination}
                            />
                        </div>
                    </div>
                    <Footer></Footer>
                </div>
        </div>
    )
}

export default InvoiceConsultation;