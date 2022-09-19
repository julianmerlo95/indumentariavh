import React, {useState, useEffect} from 'react';
import './Invoice.sass';
import axios from "axios";
import Button from '@mui/material/Button';
import {Link} from "react-router-dom";
import ClientSelected from "./ClientSelected";
import SellerSelected from "./SellerSelected";
import ProductSearch from "./ProductSearch";
import ProductSelected from "./ProductSelected";
import TotalsAmounts from "./TotalsAmounts";

import {Loading} from "../../loading/Loading";
import NavbarComponent from "../../UI/header/Navbar";
import Footer from "../../UI/footer/Footer";
import BuildBillDto from "./methods/BuildBillDto";
import BuildCurrentAccountClientDto from "./methods/CurrentAccountClient";

function Invoice() {

    const date = new Date();
    

    // Totals
    let totalInvoice = 0
    const [totalItems, setTotalItems] = useState(0);
    // Client
    const [clients, setClients] = useState([]);
    const [clientSelected, setClientSelected] = useState({idClient: "", name: "", lastName: "", dni: ""});
    const [showClient, setShowClient] = useState(false);
    const clientCloseHandler = () => setShowClient(false);
    const clientShowHandler = () => {
        if (inputValue === "" || inputValue === undefined) {
            alert("Debe ingresar algun dato para poder realizar la busqueda")
            return
        }
        setShowClient(true)
    };
    // Seller
    const [seller, setSeller] = useState([]);
    const [sellerSelected, setSellerSelected] = useState({userName: "", name: "", lastName: "", mail: "", idRole: 0});
    const [showSeller, setShowSeller] = useState(false);
    const sellerCloseHandler = () => setShowSeller(false);
    const sellerShowHandler = () => {
        if (inputValue === "" || inputValue === undefined) {
            alert("Debe ingresar algun dato para poder realizar la busqueda")
            return
        }
        setShowSeller(true)
    };
    // Products
    const [products, setProducts] = useState([]);
    const [listProductSelected, setListProductSelected] = useState([]);
    const [showProduct, setShowProduct] = useState(false);
    const productCloseHandler = () => setShowProduct(false);
    const productShowHandler = () => {
        if (inputValue === "" || inputValue === undefined) {
            alert("Debe ingresar algun dato para poder realizar la busqueda")
            return
        }
        setShowProduct(true)
    };
    // Discount
    const [discount, setDiscount] = useState(0);
    // Invoice
    const [invoice, setInovice] = useState([]);
    // Payment method
    const [borrowedCash, setBorrowedCash] = useState(0);
    const [cash, setCash] = useState(0);
    const [credit, setCredit] = useState(0);
    const [paymentMethodSelected, setPaymentMethodSelected] = useState({"idPaymentMethod": 1, "name": ""});
    // Inputs
    const [inputValue, setInputValue] = useState("");
    const [inputName, setInputName] = useState("");
    const onChangeHandler = (e) => {
        if (e.target.value.length >= 20) {
            alert("Los campos tienen un maximo de 20 caracteres")
            return
        }
        setInputValue(e.target.value);
        setInputName(e.target.name);
        return inputValue;
    };

    useEffect(() => {
        axios.get(`${process.env.REACT_APP_API_URL}/bills/first`).then((response) => setInovice(response.data));
    }, []);

    // Client methods
    const clientSelectedHandler = async (client) => {
        setClientSelected({...client});
        setShowClient(false);
        return client
    }

    const searchClientHandler = async (event) => {
        let url;
        event.preventDefault();

        if (inputName === "id") {
            url = `${process.env.REACT_APP_API_URL}/clients/${inputValue}`
        } else {
            url = `${process.env.REACT_APP_API_URL}/clients?${inputName}=${inputValue}`
        }

        if (inputValue.length >= 20 && (inputValue === "" || inputValue === undefined)) {
            alert("Debe ingresar algun dato para poder realizar la busqueda o ser menor a 20 caracteres")
            return
        }

        try {
            axios.get(url).then(response => { if (response.data.error) { setClients([]) } setClients(response.data) });
        } catch (ex) {
            throw ex
        }
    }

    // Seller methods
    const sellerSelectedHandler = async (seller) => {
        setSellerSelected({...seller});
        setShowSeller(false);
        console.log(seller)
        return seller;
    }

    const searchSellerHandler = async (event) => {
        let url = `${process.env.REACT_APP_API_URL}/users/${inputValue}`;
        event.preventDefault();

        if (inputValue.length >= 20 && (inputValue === "" || inputValue === undefined)) {
            alert("Debe ingresar algun dato para poder realizar la busqueda o ser menor a 20 caracteres")
            return
        }

        try {
            axios.get(url).then(response => { if (response.data.error) { setSeller([]) } setSeller(response.data)});
        } catch (ex) {
            throw ex
        }
    }

    // Product methods
    const productSelectedHandler = async (product) => {
        if (product.isEnable === 1) {
            setListProductSelected([...listProductSelected, product])

            let totalItem = (Number(totalItems) + Number(product.salePrice))
            setTotalItems(totalItem)

            return listProductSelected
        } else {
            alert("El producto esta deshabilitado, para poder usarlo debe habilitarlo desde la pestaña: Consultas/Stock")
        }

    }

    const searchProductHandler = async (event) => {
        let url;
        event.preventDefault();

        if (inputName === "id") {
            url = `${process.env.REACT_APP_API_URL}/products/${inputValue}`
        } else {
            url = `${process.env.REACT_APP_API_URL}/products?${inputName}=${inputValue}`
        }

        if (inputValue.length >= 20 && (inputValue === "" || inputValue === undefined)) {
            alert("Debe ingresar algun dato para poder realizar la busqueda o ser menor a 20 caracteres")
            return
        }

        try {
            axios.get(url).then(response => {
                if (response.data.error) {
                    setProducts([])
                }
                setProducts(response.data.filter(product => {
                    if (product.quantity > 0) {
                        return product
                    }
                }));
            });
        } catch (ex) {
            throw ex
        }
    }

    const removeProductHandler = async (idx) => {
        setListProductSelected(listProductSelected.filter((product, index) => {
            if (index !== idx) {
                return product
            }
            let totalItem = (Number(totalItems) - Number(product.salePrice))
            setTotalItems(totalItem)
        }));
        return listProductSelected
    }

    // Discount methods
    const saveDiscountHandler = async (event) => {
        event.preventDefault()

        if (totalItems > 0 && inputValue < totalItems) {
            setDiscount(inputValue)
            totalInvoice = discount - totalItems
            return
        }
        alert("El descuento no se puede ingresar por que no tiene productos cargados, o es mayor al total")
        return
    }

    // Payment method
    const paymentMethodSelectedHandler = async (event) => {
        event.preventDefault()
        if (inputName === "Efectivo") {
            setCash(inputValue)
            setPaymentMethodSelected({"idPaymentMethod": 1, "name": "Efectivo"})
        } else if (inputName === "Tarjeta") {
            setCredit(inputValue)
            setPaymentMethodSelected({"idPaymentMethod": 2, "name": "Tarjeta"})
        } else if (inputName === "Fiado") {
            setBorrowedCash(inputValue)
            setPaymentMethodSelected({"idPaymentMethod": 3, "name": "Fiado"})
        }
    }

    // Invoice methods
    const createInvoiceHandler = async (event) => {
        event.preventDefault()
        const urlBill =`${process.env.REACT_APP_API_URL}/bills/bill`;
        const urlClient = `${process.env.REACT_APP_API_URL}/current-account-client`;
        const idBill = invoice[0]?.idBill ? `${String(Number(invoice[0].idBill) + 1)}` : "1"

        try {
            totalInvoice = totalItems - discount
            if (idBill && listProductSelected && totalInvoice && seller.length > 0 && clients.length > 0) {

                if ((new Intl.NumberFormat("es-CL").format(Number(cash) + Number(credit) + Number(borrowedCash))) !== new Intl.NumberFormat("es-CL").format(Number(totalInvoice))) {
                    alert(`El total en medios de pago debe ser igual al total de la factura: \n-Efectivo: $${cash} \n-Tarjeta: $${credit} \n-Fiado: $${borrowedCash} \n-Total factura: $${totalInvoice}`)
                    return
                }

                const idUser = seller[0].idUser;
                const idClient = clients[0].idClient;

                let bill = BuildBillDto(idBill, totalInvoice, discount, cash, credit, borrowedCash, idUser, idClient, paymentMethodSelected, listProductSelected)

                if (bill?.totalAmount?.length <= 25 && bill?.idUser?.length <= 15 && bill?.idClient?.length <= 15) {
                    axios.post(urlBill, {bill}).then(() => {
                        if (borrowedCash > 0) {
                            let currentAccountClient = BuildCurrentAccountClientDto(clientSelected, sellerSelected, borrowedCash)
                            try {
                                axios.post(urlClient, {currentAccountClient}).then(response => { if (!response.data.error) { window.location.replace('/success')} });
                            } catch (ex) {
                              window.location.replace('/error-client');
                              throw ex
                          }
                        }
                        window.location.replace('/success')
                    });
                } else {
                    alert(`Se deben completar todos los campos requeridos: \n-Cliente: [${clientSelected.name}] \n-Vendedor: [${sellerSelected.name}] \n-Productos cargados: ${listProductSelected?.length} \n-Total factura: ${totalInvoice}`)
                }
            } else {
                alert(`Se deben completar todos los campos requeridos: \n-Cliente: [${clientSelected.name}] \n-Vendedor: [${sellerSelected.name}] \n-Productos cargados: ${listProductSelected?.length} \n-Total factura: ${totalInvoice}`)
            }
        } catch (ex) {
            window.location.replace('/error');
            throw ex
        }
    }

    return (
        <div>
            
                <div>
                    <NavbarComponent></NavbarComponent>
                    <div className="invoice">
                        <h1 className="table-title">Creacion de factura</h1>
                        <div className="invoice-form">
                            <div className="invoice-form-header"> {/* Header invoice selected */}
                                <div className="invoice-form-header-left">
                                    <form onSubmit={(event) => searchClientHandler(event)}>
                                        <label>
                                            <input className="input-invoice-from-input" type="text" name="id"
                                                   placeholder="Cliente"
                                                   onChange={(e) => onChangeHandler(e)} required/>
                                        </label>
                                        <input className="search-client-from-input" type="submit" value="Buscar"
                                               onClick={clientShowHandler}/>
                                    </form>
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
                                        <label>
                                            <p>NUMERO DE FACTURA:
                                                <span className="bill-span"> 000-0{invoice && invoice.length > 0 ? String(Number(invoice[0].idBill) + 1) : "1"}</span></p>
                                        </label>
                                    </div>
                                    <div>
                                        <label>
                                            <p>FECHA DE FACTURA: [{date.toLocaleString()}]</p>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div className="invoice-form-header-description"> {/* Cliente and seller */}
                                <ClientSelected clientSelected={clientSelected} showClient={showClient}
                                                clients={clients} clientSelectedHandler={clientSelectedHandler}
                                                clientCloseHandler={clientCloseHandler}/>
                                <SellerSelected sellerSelected={sellerSelected} showSeller={showSeller}
                                                seller={seller} sellerSelectedHandler={sellerSelectedHandler}
                                                sellerCloseHandler={sellerCloseHandler}/>
                            </div>
                            <div> {/* Product search and selection */}
                                <ProductSearch searchProductHandler={searchProductHandler} onChangeHandler={onChangeHandler}
                                               productShowHandler={productShowHandler}
                                               showProduct={showProduct}
                                               products={products}
                                               productSelectedHandler={productSelectedHandler}
                                               productCloseHandler={productCloseHandler}/>
                                <ProductSelected listProductSelected={listProductSelected}
                                                 removeProductHandler={removeProductHandler}/>
                            </div>
                            <div> {/* Total, payment_method and discount invoice */}
                                <TotalsAmounts saveDiscountHandler={saveDiscountHandler}
                                               paymentMethodSelectedHandler={paymentMethodSelectedHandler}
                                               listProductSelected={listProductSelected} totalItems={totalItems}
                                               discount={discount}
                                               cash={cash}
                                               credit={credit}
                                               borrowedCash={borrowedCash}
                                               paymentMethodSelected={paymentMethodSelected}
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
                                            color="success"
                                            onClick={createInvoiceHandler}>Guardar
                                    </Button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <Footer></Footer>
                </div>
        </div>
    )
}

export default Invoice;