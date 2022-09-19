import React, {useState, useEffect} from 'react';
import './ClientsConsultation.sass';
import axios from "axios";
import imgCLient from '../../../assets/img-client-consultation.png';
import Button from "@mui/material/Button";
import Modal from "react-bootstrap/Modal";
import {Loading} from "../../loading/Loading";
import NavbarComponent from "../../UI/header/Navbar";
import Footer from "../../UI/footer/Footer";
import TablePagination from "./table/TablePagination";

function ClientsConsultation() {
    let inputValue;
    let inputName;

    const [clientSelected, setClientSelected] = useState({idClient: "", name: "", lastName: "", dni: "", moneyDebt: "", isEnable: 0});
    const [clientEditSelected, setClientEditSelected] = useState({idClient: "", name: "", lastName: "", dateOfBirth: "", mail: "", moneyDebt: "", isEnable: 0});
    const [clients, setClients] = useState([]);
    const [showClient, setShowClient] = useState(false);
    const clientCloseHandler = () => setShowClient(false);
    const clientShowHandler = (client) => {
        setClientSelected(client)
        setShowClient(true)
    };

    // Table pagination
    const [pageInitial, setPageInitial] = useState(1);
    const quantityItemShow = 20;
    const newQuantityShow = pageInitial * quantityItemShow;
    const lastQuantityShow = newQuantityShow - quantityItemShow;
    const dataShow = clients.slice(lastQuantityShow, newQuantityShow)
    const pagination = (page) => setPageInitial(page)

    useEffect(() => {
        axios.get(`${process.env.REACT_APP_API_URL}/clients`).then((response) => setClients(response.data));
    }, []);

    const onChangeHandler = (e) => {
        if (e.target.value.length >= 15) {
            alert("Los campos tienen un maximo de 15 caracteres")
            return
        }
        inputValue = e.target.value;
        inputName = e.target.name;
        return inputValue;
    };

    const onChangeEditHandler = (e) => {
        if (e.target.value.length >= 20) {
            alert("Los campos tienen un maximo de 20 caracteres")
            return
        }
        setClientEditSelected({...clientEditSelected, [e.target.name]: e.target.value});
        return inputValue;
    };

    const clientSelectedHandler = async () => {
        try {
            let url = `${process.env.REACT_APP_API_URL}/clients/${clientSelected.idClient}`;

            let clientBody = {
                "name": clientEditSelected.name ? clientEditSelected.name : clientSelected.name,
                "lastName": clientEditSelected.lastName ? clientEditSelected.lastName : clientSelected.lastName,
                "dateOfBirth": clientEditSelected.dateOfBirth ? clientEditSelected.dateOfBirth : clientSelected.dateOfBirth,
                "mail": clientEditSelected.mail ? clientEditSelected.mail : clientSelected.mail,
                "moneyDebt": clientEditSelected.moneyDebt ? clientEditSelected.moneyDebt : clientSelected.moneyDebt,
                "isEnable": clientEditSelected.isEnable ? clientEditSelected.isEnable : clientSelected.isEnable
            }
            if (clientBody?.name?.length <= 15 && clientBody?.lastName?.length <= 15 && clientBody?.dateOfBirth?.length <= 15 &&
                clientBody?.mail?.length <= 25 && clientBody?.moneyDebt?.length <= 15) {
                axios.put(url, clientBody).then(response => {
                    if (response.data.error) {
                        setClients([])
                        window.location.replace('/error');
                    }
                    setClients(response.data)
                    window.location.replace('/consultation/clients');
                });
            } else {
                alert("Validar el largo de los campos ingresados")
            }
        } catch (ex) {
            window.location.replace('/error');
            throw ex
        }
        return clientEditSelected
    }

    const searchClientHandler = async (event) => {
        try {
            let url;
            event.preventDefault();

            if (inputValue?.length >= 20 || inputValue === "" || inputValue === undefined) {
                alert("Debe ingresar algun dato para poder realizar la busqueda o ser menor a 20 caracteres")
                return
            }

            if (inputName === "id") {
                url = `${process.env.REACT_APP_API_URL}/clients/${inputValue}`
            } else {
                url = `${process.env.REACT_APP_API_URL}/v1/clients?${inputName}=${inputValue}`
            }
            axios.get(url).then(response => { if (response.data.error) { setClients([]) } setClients(response.data) });
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
                                <h1 className="table-title">| Listado de clientes</h1>
                            </div>
                        </div>
                        <div className="search-client"> {/* Search input */}
                            <form className="form search-client-from" onSubmit={(event) => searchClientHandler(event)}>
                                <label>
                                    <input type="text" name="id" placeholder="Buscar por id"
                                           onChange={(e) => onChangeHandler(e)}/>
                                </label>
                                <input className="search-client-from-input" type="submit" value="Buscar"/>
                                <label>
                                    <input type="number" name="dni" placeholder="Buscar por dni"
                                           onChange={(e) => onChangeHandler(e)}/>
                                </label>
                                <input className="search-client-from-input" type="submit" value="Buscar"/>
                                <label>
                                    <input type="text" name="name" placeholder="Buscar por nombre"
                                           onChange={(e) => onChangeHandler(e)}/>
                                </label>
                                <input className="search-client-from-input" type="submit" value="Buscar"/>
                                <label>
                                    <input type="text" name="lastName" placeholder="Buscar por apellido"
                                           onChange={(e) => onChangeHandler(e)}/>
                                </label>
                                <input className="search-client-from-input" type="submit" value="Buscar"/>
                                <label>
                                    <input type="text" name="mail" placeholder="Buscar por mail"
                                           onChange={(e) => onChangeHandler(e)}/>
                                </label>
                                <input className="search-client-from-input" type="submit" value="Buscar"/>
                            </form>
                        </div>
                        <div className="container-gray-data">
                            <h4 className="table-title">TOTAL SUMARIZADO DE DEUDA:
                                ${new Intl.NumberFormat("es-CL").format(clients && clients.length > 0  ? clients?.reduce((a, b) => Number(a) + Number(b.moneyDebt), 0) : "")}
                            </h4>
                        </div>
                        <TablePagination
                            quantityItemShow={quantityItemShow}
                            invoicesLength={clients.length}
                            clients={dataShow}
                            pagination={pagination}
                            clientShowHandler={clientShowHandler}
                        />
                        <div> {/* Show client model */}
                            <Modal className="invoice-form-header-description-seller-modal" size="lg" show={showClient}
                                   onHide={clientCloseHandler}>
                                <Modal.Header closeButton>
                                    <Modal.Title>Editar cliente</Modal.Title>
                                </Modal.Header>
                                <Modal.Body className="invoice-form-header-description-seller-modal-body">
                                    <div>
                                        <div className=" table-body table-responsive">
                                            <table className="table table-body">
                                                <thead>
                                                <tr>
                                                    <th scope="col">Id cliente</th>
                                                    <th scope="col">Nombre</th>
                                                    <th scope="col">Apellido</th>
                                                    <th scope="col">Cumpleaños</th>
                                                    <th scope="col">Dni</th>
                                                    <th scope="col">Mail</th>
                                                    <th scope="col">Estado</th>
                                                    <th scope="col">Deuda</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr className="table table-body-data">
                                                    <th scope="row">{clientSelected.idClient}</th>
                                                    <th scope="row">{clientSelected.name}</th>
                                                    <th scope="row">{clientSelected.lastName}</th>
                                                    <th scope="row">{clientSelected.dateOfBirth}</th>
                                                    <th scope="row">{clientSelected.dni}</th>
                                                    <th scope="row">{clientSelected.mail}</th>
                                                    <th scope="row">{clientSelected.isEnable === 1 ? "HABILITADO" : "DESHABILITADO"}</th>
                                                    <th scope="row">${new Intl.NumberFormat("es-CL").format(Number(clientSelected.moneyDebt))}</th>
                                                </tr>
                                                </tbody>
                                                <tbody>
                                                    <tr className="table table-body-data">
                                                        <th scope="row"><p>No editable</p></th>
                                                        <th scope="row"><input type="text" name="name" placeholder="Nombre"
                                                                               onChange={(e) => onChangeEditHandler(e)}/></th>
                                                        <th scope="row"><input type="text" name="lastName"
                                                                               placeholder="Apellido"
                                                                               onChange={(e) => onChangeEditHandler(e)}/></th>
                                                        <th scope="row"><input type="text" name="dateOfBirth"
                                                                               placeholder="Cumpleaños"
                                                                               onChange={(e) => onChangeEditHandler(e)}/></th>
                                                        <th scope="row"><p>No editable</p></th>
                                                        <th scope="row"><input type="text" name="mail" placeholder="Mail"
                                                                               onChange={(e) => onChangeEditHandler(e)}/></th>
                                                        <th scope="row"><input type="number" name="isEnable"
                                                                               placeholder="Ingrese 1 o 0"
                                                                               onChange={(e) => onChangeEditHandler(e)}/></th>
                                                        <th scope="row"><p>No editable</p></th>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </Modal.Body>
                                <Modal.Footer>
                                    <Button className="client-high-button-error" variant="contained" size="large"
                                            color="error"
                                            onClick={clientCloseHandler}>Cerrar</Button>
                                    <Button className="client-high-button-select" variant="contained" size="large"
                                            color="success"
                                            onClick={() => clientSelectedHandler()}>Editar
                                    </Button>
                                </Modal.Footer>
                            </Modal>
                        </div>
                    </div>
                    <Footer></Footer>
                </div>
        </div>
    )
}

export default ClientsConsultation;