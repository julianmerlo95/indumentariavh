import React, {useState} from 'react';
import './ProductHigh.sass';
import Button from '@mui/material/Button';
import Form from 'react-bootstrap/Form';
import axios from "axios";
import {Link} from 'react-router-dom';
import imgProduct from '../../../assets/product-high.png';
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import OutlinedInput from "@mui/material/OutlinedInput";
import Checkbox from '@mui/material/Checkbox';

import {Loading} from "../../loading/Loading";
import NavbarComponent from "../../UI/header/Navbar";
import Footer from "../../UI/footer/Footer";

function ProductHigh() {
    
    const [product, setProduct] = useState({idProduct: "", name: "", description: "", color: "", waist: "", quantity: 0, purchasePrice: "", salePrice: "", isAugmentedReality: 0, isEnable: 1});
    const [checkedAugmentedReality, setCheckedAugmentedReality] = React.useState(false);
    const [checkedIsEnable, setCheckedIsEnbale] = React.useState(true);

    const checkboxIsEnableHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
        setCheckedIsEnbale(event.target.checked);
    };

    const checkboxAugmentedRealityHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
        setCheckedAugmentedReality(event.target.checked);
    };

    const onChangeHandler = (e) => {
        if (e.target.name === "description" && e.target.value.length <= 25) {
            setProduct({...product, [e.target.name]: e.target.value});
            return
        }

        if (e.target.value.length <= 15) {
            setProduct({...product, [e.target.name]: e.target.value});
            return
        }
        alert("Los campos tienen un maximo de 15 caracteres")
        return
    };

    const createProductHandler = async (event) => {
        event.preventDefault();

        try {
            if (product?.idProduct?.length <= 15 && product?.name?.length <= 15 && product?.color?.length <= 15 && 
                product?.waist?.length <= 15 && product?.quantity?.length <= 15 && product?.salePrice?.length <= 15) {
                product.isAugmentedReality = checkedAugmentedReality === true ? 1 : 0
                product.isEnable = checkedIsEnable === true ? 1 : 0
                axios.post(`${process.env.REACT_APP_API_URL}/products/product`, {product}).then(response => window.location.replace('/success')); // TODO: Redirect
            } else {
                alert("Los campos id producto, nombre, color, talle, cantidad y precio de venta son obligatorios. Ademas del largo de los campos")
            }
        } catch (ex) {
            window.location.replace('/error')
            throw ex
        }
    }

    return (
        <div>
            
                <div>
                    <NavbarComponent></NavbarComponent>
                    <div className="product-high">
                        <div className="product-high-left">
                            <h1 className="product-high-title">Alta de producto</h1>
                            <img className="img-product-high" src={imgProduct}/>
                        </div>
                        <div className="product-high-right">
                            <Form className="form" onSubmit={(event) => createProductHandler(event)}>
                                <Form.Group className="mb-3" controlId="formBasicIdProduct">
                                    <FormControl fullWidth sx={{m: 1}} required={true}>
                                        <InputLabel htmlFor="outlined-adornment-amount">Id del producto</InputLabel>
                                        <OutlinedInput
                                            id="outlined-adornment-amount"
                                            name="idProduct"
                                            placeholder="Se permite ingresar letras y numeros"
                                            onChange={(e) => onChangeHandler(e)}
                                            label="Id del producto"
                                        />
                                    </FormControl>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="formBasicName">
                                    <FormControl fullWidth sx={{m: 1}} required={true}>
                                        <InputLabel htmlFor="outlined-adornment-amount">Nombre</InputLabel>
                                        <OutlinedInput
                                            id="outlined-adornment-amount"
                                            name="name"
                                            placeholder="Nombre"
                                            onChange={(e) => onChangeHandler(e)}
                                            label="Nombre"
                                        />
                                    </FormControl>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="formBasicDescription">
                                    <FormControl fullWidth sx={{m: 1}}>
                                        <InputLabel htmlFor="outlined-adornment-amount">Descripcion</InputLabel>
                                        <OutlinedInput
                                            id="outlined-adornment-amount"
                                            name="description"
                                            placeholder="Descripcion"
                                            onChange={(e) => onChangeHandler(e)}
                                            label="Descripcion"
                                        />
                                    </FormControl>
                                </Form.Group>
                                <div className="product-high-colour-and-waist">
                                    <Form.Group className="colour mb-3" controlId="formBasicColour">
                                        <FormControl fullWidth sx={{m: 1}} required={true}>
                                            <InputLabel htmlFor="outlined-adornment-amount">Color</InputLabel>
                                            <OutlinedInput
                                                id="outlined-adornment-amount"
                                                name="colour"
                                                placeholder="Color"
                                                onChange={(e) => onChangeHandler(e)}
                                                label="Color"
                                            />
                                        </FormControl>
                                    </Form.Group>
                                    <Form.Group className="waist mb-3" controlId="formBasicWaist">
                                        <FormControl fullWidth sx={{m: 1}} required={true}>
                                            <InputLabel htmlFor="outlined-adornment-amount">Talle</InputLabel>
                                            <OutlinedInput
                                                id="outlined-adornment-amount"
                                                name="waist"
                                                placeholder="Talle"
                                                onChange={(e) => onChangeHandler(e)}
                                                label="Talle"
                                            />
                                        </FormControl>
                                    </Form.Group>
                                </div>
                                <Form.Group className="mb-3" controlId="formBasicQuantity">
                                    <FormControl fullWidth sx={{m: 1}} required={true}>
                                        <InputLabel htmlFor="outlined-adornment-amount">Cantidad</InputLabel>
                                        <OutlinedInput
                                            id="outlined-adornment-amount"
                                            name="quantity"
                                            type="number"
                                            placeholder="Cantidad"
                                            onChange={(e) => onChangeHandler(e)}
                                            label="Cantidad"
                                        />
                                    </FormControl>
                                </Form.Group>
                                <div className="product-high-prices">
                                    <Form.Group className="pruchase-price mb-3" controlId="formBasicPurchasePrice">
                                        <FormControl fullWidth sx={{m: 1}}>
                                            <InputLabel htmlFor="outlined-adornment-amount">Precio de compra</InputLabel>
                                            <OutlinedInput
                                                id="outlined-adornment-amount"
                                                name="purchasePrice"
                                                placeholder="Precio de compra"
                                                onChange={(e) => onChangeHandler(e)}
                                                label="Precio de compra"
                                                type="number"
                                            />
                                        </FormControl>
                                    </Form.Group>
                                    <Form.Group className="sale-price mb-3" controlId="formBasicSalePrice">
                                        <FormControl fullWidth sx={{m: 1}} required={true}>
                                            <InputLabel htmlFor="outlined-adornment-amount">Precio de venta</InputLabel>
                                            <OutlinedInput
                                                id="outlined-adornment-amount"
                                                name="salePrice"
                                                placeholder="Precio de venta"
                                                onChange={(e) => onChangeHandler(e)}
                                                label="Precio de venta"
                                                type="number"
                                            />
                                        </FormControl>
                                    </Form.Group>
                                </div>
                                <Form.Group className="mb-3" controlId="formBasicMoneyDebt">
                                    <FormControl fullWidth sx={{m: 1}}>
                                        <InputLabel htmlFor="outlined-adornment-amount">Realidad aumentada</InputLabel>
                                        <Checkbox
                                            checked={checkedAugmentedReality}
                                            onChange={checkboxAugmentedRealityHandler}
                                            inputProps={{'aria-label': 'controlled'}}
                                        />
                                    </FormControl>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="formBasicMoneyDebt">
                                    <FormControl fullWidth sx={{m: 1}}>
                                        <InputLabel htmlFor="outlined-adornment-amount">Producto habilitado</InputLabel>
                                        <Checkbox
                                            checked={checkedIsEnable}
                                            onChange={checkboxIsEnableHandler}
                                            inputProps={{'aria-label': 'controlled'}}
                                        />
                                    </FormControl>
                                </Form.Group>
                                <Link to="/home">
                                    <Button className="client-button-error" variant="contained" size="large" color="error">
                                        Cancelar
                                    </Button>
                                </Link>
                                <Button variant="contained" size="large" color="success"
                                        onClick={(event) => createProductHandler(event)}>Guardar</Button>
                            </Form>
                        </div>
                    </div>
                    <Footer></Footer>
                </div>
        </div>
    )
}

export default ProductHigh;