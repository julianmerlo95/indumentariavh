import React, {useState, useEffect} from 'react';
import './StocksConsultation.sass';
import axios from "axios";
import imgCLient from "../../../assets/img-client-consultation.png";
import Modal from "react-bootstrap/Modal";
import Button from "@mui/material/Button";
import {Loading} from '../../loading/Loading'

import NavbarComponent from '../../UI/header/Navbar'
import Footer from "../../UI/footer/Footer";
import TablePagination from "./table/TablePagination";

function StocksConsultation() {
    let inputValue;
    let inputName;

    const [stocks, setStock] = useState([]);

    // Table pagination
    const [pageInitial, setPageInitial] = useState(1);
    const quantityItemShow = 25;
    const newQuantityShow = pageInitial * quantityItemShow;
    const lastQuantityShow = newQuantityShow - quantityItemShow;
    const dataShow = stocks.slice(lastQuantityShow, newQuantityShow)
    const pagination = (page) => setPageInitial(page)

    
    const [productSelected, setProductSelected] = useState({idProduct: "", name: "", description: "", colour: "", waist: "", quantity: "", purchasePrice: "", salePrice: "", isAugmentedReality: 0, isEnable: 1});
    const [productEditSelected, setProductEditSelected] = useState({idProduct: "", name: "", description: "", colour: "", waist: "", quantity: "", purchasePrice: "", salePrice: "", isAugmentedReality: 0, isEnable: 1});
    const [products, setProduct] = useState([]);
    const [showProduct, setShowProduct] = useState(false);
    const productCloseHandler = () => setShowProduct(false);
    const productShowHandler = (client) => {
        setProductSelected(client)
        setShowProduct(true)
    };

    useEffect(() => {
        axios.get(`${process.env.REACT_APP_API_URL}/products`).then((response) => setStock(response.data));
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

    const onChangeEditHandler = (e) => {
        setProductEditSelected({...productEditSelected, [e.target.name]: e.target.value});
        return inputValue;
    };

    const productSelectedHandler = async () => {
        try {
            let url = `${process.env.REACT_APP_API_URL}/products/${productSelected.idProduct}`;
            let productBody = {
                "name": productEditSelected.name ? productEditSelected.name : productSelected.name,
                "description": productEditSelected.description ? productEditSelected.description:  productSelected.description,
                "colour": productEditSelected.colour ? productEditSelected.colour : productSelected.colour,
                "waist": productEditSelected.waist ? productEditSelected.waist : productSelected.waist,
                "quantity": productEditSelected.quantity ? productEditSelected.quantity : productSelected.quantity,
                "purchasePrice": productEditSelected.purchasePrice ? productEditSelected.purchasePrice : productSelected.purchasePrice,
                "salePrice": productEditSelected.salePrice ? productEditSelected.salePrice : productSelected.salePrice,
                "isAugmentedReality": productEditSelected.isAugmentedReality ? productEditSelected.isAugmentedReality : productSelected.isAugmentedReality,
                "isEnable": productEditSelected.isEnable ? productEditSelected.isEnable : productSelected.isEnable
            }
            if (productBody?.name?.length <= 20 && productBody?.colour?.length <= 20 && productBody?.waist?.length <= 20 && productBody?.quantity?.length <= 20 && 
                productBody?.purchasePrice?.length <= 20 && productBody?.salePrice?.length <= 20 && productBody?.description?.length <= 25)  {
                axios.put(url, productBody).then(response => {
                    if (response.data.error) {
                        setProduct([])
                        window.location.replace('/error');
                    }
                    setProduct(response.data)
                    window.location.replace('/consultation/stocks');
                });
            } else {
                alert("Validar el largo de los campos ingresados")
            }
        } catch (ex) {
            window.location.replace('/error');
            throw ex
        }

        return productEditSelected
    }

    const searchProductHandler = async (event) => {
        let url;
        event.preventDefault();

        if (inputValue?.length >= 20 || inputValue === "" || inputValue === undefined) {
            alert("Debe ingresar algun dato para poder realizar la busqueda o ser menor a 20 caracteres")
            return
        }

        if (inputName === "id") {
            url = `${process.env.REACT_APP_API_URL}/products/${inputValue}`
        } else {
            url = `${process.env.REACT_APP_API_URL}/products?${inputName}=${inputValue}`
        }

        try {
            axios.get(url).then(response => { if (response.data.error) { setStock([]) } setStock(response.data) });
        } catch (ex) {
            throw ex
        }
    }

    return (
        <div>
            
                <div>
                    <NavbarComponent></NavbarComponent>
                    <div className="search-client-header">
                        <div className="search-client-header-img">
                            <img className="client-consultation-img" src={imgCLient}/>
                        </div>
                        <div className="search-client-header-title">
                            <h1 className="table-title">| Listado de stock</h1>
                        </div>
                    </div>
                    <div className="search-product">
                        <form className="form search-product-from" onSubmit={(event) => searchProductHandler(event)}>
                            <label>
                                <input type="text" name="id" placeholder="Buscar por id" onChange={(e) => onChangeHandler(e)}/>
                            </label>
                            <input className="search-product-from-input" type="submit" value="Buscar"/>
                            <label>
                                <input type="text" name="name" placeholder="Buscar por nombre" onChange={(e) => onChangeHandler(e)}/>
                            </label>
                            <input className="search-product-from-input" type="submit" value="Buscar"/>
                            <label>
                                <input type="text" name="description" placeholder="Buscar por descripcion" onChange={(e) => onChangeHandler(e)}/>
                            </label>
                            <input className="search-product-from-input" type="submit" value="Buscar"/>
                            <label>
                                <input type="text" name="colour" placeholder="Buscar por color" onChange={(e) => onChangeHandler(e)}/>
                            </label>
                            <input className="search-product-from-input" type="submit" value="Buscar"/>
                            <label>
                                <input type="text" name="waist" placeholder="Buscar por talle" onChange={(e) => onChangeHandler(e)}/>
                            </label>
                            <input className="search-product-from-input" type="submit" value="Buscar"/>
                        </form>
                    </div>
                    <div className="container-gray-data">
                        <h4 className="table-title">TOTAL SUMARIZADO DE STOCK:
                            {new Intl.NumberFormat("es-CL").format(stocks && stocks.length > 0  ? stocks?.reduce((a, b) => Number(a) + Number(b.quantity), 0) : "")}
                        </h4>
                    </div>
                    <div>
                        <div>
                            <TablePagination
                                quantityItemShow={quantityItemShow}
                                invoicesLength={stocks.length}
                                stocks={dataShow}
                                pagination={pagination}
                                productShowHandler={productShowHandler}
                            />
                        </div>
                        <div> {/* Show product model */}
                            <Modal className="invoice-form-header-description-seller-modal" size="lg" show={showProduct} onHide={productCloseHandler}>
                                <Modal.Header closeButton>
                                    <Modal.Title>Editar cliente</Modal.Title>
                                </Modal.Header>
                                <Modal.Body className="invoice-form-header-description-seller-modal-body">
                                    <div>
                                        <div className=" table-body table-responsive">
                                            <table className="table table-body">
                                                <thead>
                                                <tr>
                                                    <th scope="col">Id producto</th>
                                                    <th scope="col">Nombre</th>
                                                    <th scope="col">Descripcion</th>
                                                    <th scope="col">Color</th>
                                                    <th scope="col">Talle</th>
                                                    <th scope="col">Cantidad</th>
                                                    <th scope="col">Precio compra</th>
                                                    <th scope="col">Precio venta</th>
                                                    <th scope="col">Realidad aumentada</th>
                                                    <th scope="col">Estado</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr className="table table-body-data">
                                                    <th scope="row">{productSelected.idProduct}</th>
                                                    <th scope="row">{productSelected.name}</th>
                                                    <th scope="row">{productSelected.description}</th>
                                                    <th scope="row">{productSelected.colour}</th>
                                                    <th scope="row">{productSelected.waist}</th>
                                                    <th scope="row">{productSelected.quantity}</th>
                                                    <th scope="row">${new Intl.NumberFormat("es-CL").format(Number(productSelected.purchasePrice))}</th>
                                                    <th scope="row">${new Intl.NumberFormat("es-CL").format(Number(productSelected.salePrice))}</th>
                                                    <th scope="row">{productSelected.isAugmentedReality === 1 ? "HABILITADO" : "DESHABILITADO"}</th>
                                                    <th scope="row">{productSelected.isEnable === 1 ? "HABILITADO" : "DESHABILITADO"}</th>
                                                </tr>
                                                </tbody>
                                                <tbody>
                                                <tr className="table table-body-data">
                                                    <th scope="row"><p>No editable</p></th>
                                                    <th scope="row"><input type="text" name="name" placeholder="Nombre" onChange={(e) => onChangeEditHandler(e)}/></th>
                                                    <th scope="row"><input type="text" name="description" placeholder="Descripcion" onChange={(e) => onChangeEditHandler(e)}/></th>
                                                    <th scope="row"><input type="text" name="colour" placeholder="Color" onChange={(e) => onChangeEditHandler(e)}/></th>
                                                    <th scope="row"><input type="text" name="waist" placeholder="Talle" onChange={(e) => onChangeEditHandler(e)}/></th>
                                                    <th scope="row"><input type="text" name="quantity" placeholder="Cantidad" onChange={(e) => onChangeEditHandler(e)}/></th>
                                                    <th scope="row"><input type="text" name="purchasePrice" placeholder="Precio compra" onChange={(e) => onChangeEditHandler(e)}/></th>
                                                    <th scope="row"><input type="text" name="salePrice" placeholder="Precio venta" onChange={(e) => onChangeEditHandler(e)}/></th>
                                                    <th scope="row"><input type="text" name="isAugmentedReality" placeholder="Ingrese 1 o 0" onChange={(e) => onChangeEditHandler(e)}/></th>
                                                    <th scope="row"><input type="text" name="isEnable" placeholder="Ingrese 1 o 0" onChange={(e) => onChangeEditHandler(e)}/></th>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </Modal.Body>
                                <Modal.Footer>
                                    <Button className="client-high-button-error" variant="contained" size="large" color="error"
                                            onClick={productCloseHandler}>Cerrar</Button>
                                    <Button className="client-high-button-select" variant="contained" size="large" color="success"
                                            onClick={() => productSelectedHandler()}>Editar
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

export default StocksConsultation;