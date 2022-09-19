import React, {useState} from 'react';
import Button from "@mui/material/Button";
import axios from "axios";
import './Expenses.sass';
import {Link} from "react-router-dom";
import SellerSelected from "../sales/currentAccountClient/SellerSelected";
import TotalsAmounts from "./TotalsAmounts";

import {Loading} from "../loading/Loading";
import NavbarComponent from "../UI/header/Navbar";
import Footer from "../UI/footer/Footer";

function Expenses() {

    const date = new Date();
    
    // Seller
    const [seller, setSeller] = useState([]);
    const [sellerSelected, setSellerSelected] = useState({idUser: "", userName: "", name: "", lastName: "", mail: "", idRole: 0});
    const [showSeller, setShowSeller] = useState(false);
    const sellerCloseHandler = () => setShowSeller(false);
    const sellerShowHandler = () => {
        validateInputsValue(inputValue)
        setShowSeller(true)
    };
    // Discount
    const [amount, setAmount] = useState(0);
    // Inputs
    const [inputValue, setInputValue] = useState("");
    const [inputName, setInputName] = useState("");
    const onChangeHandler = (e) => {
        validateInputsValue(inputValue)
        setInputValue(e.target.value);
        setInputName(e.target.name);
        return inputValue;
    };
    // Expenses method
    const [expensesMethodSelected, setExpensesMethodSelected] = useState({"idExpenses": 1, "name": ""});

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
            axios.get(url).then(response => {
                if (response.data.error) {
                    setSeller([])
                }
                setSeller(response.data)
            });
        } catch (ex) {
            throw ex //TODO: Redirect
        }
    }

    // Expenses method
    const expensesSelectedHandler = async (event) => {
        event.preventDefault()
        console.log(inputName)
        if (inputName === "Sueldo") {
            setExpensesMethodSelected({"idExpenses": 1, "name": "Sueldo"})
        } else if (inputName === "Mercaderia") {
            setExpensesMethodSelected({"idExpenses": 2, "name": "Mercaderia"})
        } else if (inputName === "Retiro de dinero") {
            setExpensesMethodSelected({"idExpenses": 3, "name": "Retiro de dinero"})
        }
        console.log(expensesMethodSelected)
    }

    // Save
    const saveAmountHandler = async (event) => {
        event.preventDefault()
        validateInputsValue(inputValue)

        if (inputName === "amount") {
            setAmount(inputValue)
            return
        }
        alert("Debe ingresar algun dato para poder realizar la busqueda")
        return
    }

    const createCurrentAccountClientHandler = async (event) => {
        event.preventDefault()
        let url = `${process.env.REACT_APP_API_URL}/expenses/expense`

        if (expensesMethodSelected && sellerSelected.userName && amount) {
            try {
                let expense = {
                    "idUser": sellerSelected.idUser,
                    "amount": amount,
                    "idExpenseType": expensesMethodSelected.idExpenses,
                    "expenseName": expensesMethodSelected.name,
                }
                axios.post(url, {expense}).then(response => { if (!response.data.error) { window.location.replace('/success')} });
                return
            } catch (ex) {
                window.location.replace('/error');
                throw ex
            }
        } else {
            alert(`Debe ingresar algun vendedor y monto para crear la factura: \n-Vendedor: ${sellerSelected.userName}  \n-Monto: ${amount}`)
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
                        <h1 className="table-title">Registro de los gastos</h1>
                        <div className="invoice-form-header"> {/* Header invoice selected */}
                            <div className="invoice-form-header-left">
                                <form onSubmit={(event) => searchSellerHandler(event)}>
                                    <label>
                                        <input className="input-invoice-from-input" type="number" name="dni"
                                               placeholder="Vendedor" onChange={(e) => onChangeHandler(e)} required/>
                                    </label>
                                    <input className="search-client-from-input" type="submit" value="Buscar"
                                           onClick={sellerShowHandler}/>
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
                            <SellerSelected sellerSelected={sellerSelected} showSeller={showSeller}
                                            sellerCloseHandler={sellerCloseHandler}
                                            seller={seller} sellerSelectedHandler={sellerSelectedHandler}/>
                        </div>
                        <div> {/* Total, payment_method and discount invoice */}
                            <TotalsAmounts saveAmountHandler={saveAmountHandler}
                                           amount={amount}
                                           expensesSelectedHandler={expensesSelectedHandler}
                                           expensesMethodSelected={expensesMethodSelected}
                                           onChangeHandler={onChangeHandler}/>
                        </div>
                        <div className="invoice-footer-buttons"> {/* Footer buttons */}
                            <Link to="/home">
                                <Button className="client-high-button-error" variant="contained" size="large"
                                        color="error">
                                    Cancelar
                                </Button>
                            </Link>
                            <div>
                                <Button className="client-high-button-error" variant="contained" size="large"
                                        color="success" onClick={createCurrentAccountClientHandler}>
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

export default Expenses;