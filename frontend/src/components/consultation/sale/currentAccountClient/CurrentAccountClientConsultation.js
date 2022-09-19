import React, {useState, useEffect} from 'react';
import './CurrentAccountClientConsultation.sass';
import axios from "axios";
import imgCLient from '../../../../assets/img-client-consultation.png';
import {Loading} from "../../../loading/Loading";
import NavbarComponent from "../../../UI/header/Navbar";
import Footer from "../../../UI/footer/Footer";
import TablePagination from "./table/TablePagination";

function CurrentAccountClientConsultation() {
    let inputValue;
    let inputName;

    const [currentAccountClient, setCurrentAccountClient] = useState([]);

    // Table pagination
    const [pageInitial, setPageInitial] = useState(1);
    const quantityItemShow = 25;
    const newQuantityShow = pageInitial * quantityItemShow;
    const lastQuantityShow = newQuantityShow - quantityItemShow;
    const dataShow = currentAccountClient.slice(lastQuantityShow, newQuantityShow)
    const pagination = (page) => setPageInitial(page)

    useEffect(() => {
        axios.get(`${process.env.REACT_APP_API_URL}/current-account-client`).then((response) => setCurrentAccountClient(response.data));
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

    const searchClientHandler = async (event) => {
        try {
            let url;
            event.preventDefault();

            if (inputValue?.length >= 20 || inputValue === "" || inputValue === undefined) {
                alert("Debe ingresar algun dato para poder realizar la busqueda o ser menor a 20 caracteres")
                return
            }
            
            if (inputName === "id") {
                url = `${process.env.REACT_APP_API_URL}/current-account-client/${inputValue}`
            } else {
                url = `${process.env.REACT_APP_API_URL}/current-account-client?${inputName}=${inputValue}`
            }
            
            axios.get(url).then(response => { if (response.data.error) { setCurrentAccountClient([]) } setCurrentAccountClient(response.data) });
        } catch (ex) {
            window.location.replace('/error');
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
                                <h1 className="table-title">| Listado de facturas fiadas</h1>
                            </div>
                        </div>
                        <div className="search-client"> {/* Search input */}
                            <form className="form search-client-from" onSubmit={(event) => searchClientHandler(event)}>
                                <label>
                                    <input type="number" name="idClient" placeholder="Buscar por id cliente"
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
                    <TablePagination
                        quantityItemShow={quantityItemShow}
                        invoicesLength={currentAccountClient.length}
                        currentAccountClient={dataShow}
                        pagination={pagination}
                    />
                    </div>
                    <Footer></Footer>
                </div>
        </div>

    )
}

export default CurrentAccountClientConsultation;