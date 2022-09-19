import React, {useState} from 'react';
import Button from "@mui/material/Button";
import axios from "axios";
import './CurrentAccountClient.sass';
import {Link} from "react-router-dom";
import SellerSelected from "./SellerSelected";
import ClientSelected from "./ClientSelected";
import TotalsAmounts from "./TotalsAmounts";

import {Loading} from "../../loading/Loading";
import NavbarComponent from "../../UI/header/Navbar";
import Footer from "../../UI/footer/Footer";

function CurrentAccountClient() {

    const date = new Date();
    
    // Total invoice
    const [totalInvoice, setTotalInvoice] = useState(0);
    // Client
    const [clients, setClients] = useState([]);
    const [clientSelected, setClientSelected] = useState({idClient: "", name: "", lastName: "", dni: "", moneyDebt: ""});
    const [showClient, setShowClient] = useState(false);
    const clientCloseHandler = () => setShowClient(false);
    const clientShowHandler = () => {
        validateInputsValue(inputValue)
        setShowClient(true)
    };
    // Seller
    const [seller, setSeller] = useState([]);
    const [sellerSelected, setSellerSelected] = useState({userName: "", name: "", lastName: "", mail: "", idRole: 0});
    const [showSeller, setShowSeller] = useState(false);
    const sellerCloseHandler = () => setShowSeller(false);
    const sellerShowHandler = () => {
        validateInputsValue(inputValue)
        setShowSeller(true)
    };
    // Discount
    const [amountDebit, setAmountDebit] = useState(0);
    const [amountCredit, setAmountCredit] = useState(0);
    // Inputs
    const [inputValue, setInputValue] = useState("");
    const [inputName, setInputName] = useState("");
    const onChangeHandler = (e) => {
        validateInputsValue(inputValue)
        setInputValue(e.target.value);
        setInputName(e.target.name);
        return inputValue;
    };

    // Client methods
    const clientSelectedHandler = async (client) => {
        validateInputsValue(inputValue)
        setTotalInvoice(client.moneyDebt)
        setClientSelected({...client});
        setShowClient(false);
        return client
    }

    const searchClientHandler = async (event) => {
        let url;
        event.preventDefault();

        validateInputsValue(inputValue)

        if (inputName === "id") {
            url = `${process.env.REACT_APP_API_URL}/clients/${inputValue}`
        } else {
            url = `${process.env.REACT_APP_API_URL}/clients?${inputName}=${inputValue}`
        }

        try {
            axios.get(url).then(response => { if (response.data.error) { setClients([]) } setClients(response.data) });
        } catch (ex) {
            throw ex //TODO: Redirect
        }
    }

    // Seller methods
    const sellerSelectedHandler = async (seller) => {
        setSellerSelected({...seller});
        setShowSeller(false);
        return seller;
    }

    const searchSellerHandler = async (event) => {
        let url = `${process.env.REACT_APP_API_URL}/users/${inputValue}`;
        event.preventDefault();

        validateInputsValue(inputValue)

        try {
            axios.get(url).then(response => { if (response.data.error) { setSeller([]) } setSeller(response.data) });
        } catch (ex) {
            throw ex //TODO: Redirect
        }
    }

    // Save
    const saveAmountHandler = async (event) => {
        event.preventDefault()
        validateInputsValue(inputValue)

        if (inputName === "amountDebit") {
            setAmountDebit(inputValue)
            return
        } else if (inputName === "amountCredit") {
            setAmountCredit(inputValue)
            return
        }

        if (clientSelected.moneyDebt > 0) {
            clientSelected.moneyDebt = +amountCredit
            return
        }
        alert("Debe ingresar algun dato para poder realizar la busqueda")
        return
    }

    const createCurrentAccountClientHandler = async (event) => {
        event.preventDefault()
        let url = `${process.env.REACT_APP_API_URL}/current-account-client`

        if (clientSelected.idClient && sellerSelected.userName) {
            try {
                let currentAccountClient = {
                    "idClient": clientSelected.idClient,
                    "userName": sellerSelected.userName,
                    "actualMoneyDebt": clientSelected.moneyDebt,
                    "moneyCredit": amountCredit,
                    "moneyDebit": amountDebit,
                    "newMoneyDebt": (Number((totalInvoice - amountDebit)) + Number(amountCredit))
                }
                axios.post(url, {currentAccountClient}).then(response => { if (!response.data.error) { window.location.replace('/success')} });
                return
            } catch (ex) {
                window.location.replace('/error');
                throw ex
            }
        } else {
            alert(`Debe ingresar algun cliente y vendedor para crear la factura: \n-Cliente: ${clientSelected.name} \n-Vendedor: ${sellerSelected.userName}`)
        }
    }

    function validateInputsValue(inputValue) {
        if (inputValue.length >= 20 && (inputValue === "" || inputValue === undefined)) {
            alert("Debe ingresar algun dato para poder realizar la busqueda y largo maximo es 20 caracteres")
            return
        }
    }

    return (
        <div>
            
                <div>
                    <NavbarComponent></NavbarComponent>
                    <div>
                        <h1 className="table-title">Registro del fiado a los clientes</h1>
                        <div className="invoice-form-header"> {/* Header invoice selected */}
                            <div className="invoice-form-header-left">
                                <form onSubmit={(event) => searchClientHandler(event)}>
                                    <label>
                                        <input className="input-invoice-from-input" type="text" name="id" placeholder="Cliente" onChange={(e) => onChangeHandler(e)} required/>
                                    </label>
                                    <input className="search-client-from-input" type="submit" value="Buscar" onClick={clientShowHandler}/>
                                </form>
                                <form onSubmit={(event) => searchSellerHandler(event)}>
                                    <label>
                                        <input className="input-invoice-from-input" type="number" name="dni" placeholder="Vendedor" onChange={(e) => onChangeHandler(e)} required/>
                                    </label>
                                    <input className="search-client-from-input" type="submit" value="Buscar" onClick={sellerShowHandler}/>
                                </form>
                            </div>
                            <div className="invoice-form-header-right">
                                <div>
                                    <label></label>
                                </div>
                                <div>
                                    <label>
                                        <p>FECHA DE FACTURA: [{date.toLocaleString()}]</p>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div className="invoice-form-header-description"> {/* Header show data for client and seller */}
                            <ClientSelected clientSelected={clientSelected} showClient={showClient}
                                            clientCloseHandler={clientCloseHandler}
                                            clients={clients} totalInvoice={totalInvoice}
                                            clientSelectedHandler={clientSelectedHandler}/>
                            <SellerSelected sellerSelected={sellerSelected} showSeller={showSeller}
                                            sellerCloseHandler={sellerCloseHandler}
                                            seller={seller} sellerSelectedHandler={sellerSelectedHandler}/>
                        </div>
                        <div>

                        </div> {/* Total, payment_method and discount invoice */}
                        <TotalsAmounts saveAmountHandler={saveAmountHandler} onChangeHandler={onChangeHandler}
                                       clientSelected={clientSelected} amountDebit={amountDebit} amountCredit={amountCredit}
                                       totalInvoice={totalInvoice}/>
                        <div className="invoice-footer-buttons"> {/* Footer buttons */}
                            <Link to="/home">
                                <Button className="client-high-button-error" variant="contained" size="large" color="error">
                                    Cancelar
                                </Button>
                            </Link>
                            <div>
                                <Button className="client-high-button-error" variant="contained" size="large" color="success" onClick={createCurrentAccountClientHandler}>
                                    Guardar
                                </Button>
                            </div>
                        </div>
                    </div>
                    <Footer></Footer>
                </div>
        </div>

    )
}

export default CurrentAccountClient;