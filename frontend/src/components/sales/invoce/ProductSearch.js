import React from 'react';
import Modal from "react-bootstrap/Modal";
import Button from "@mui/material/Button";

function ProductSearch(props) {
    return (
        <div>
            <form onSubmit={(event) => props.searchProductHandler(event)}> {/* Invoice body */}
                <div className="invoice-form-body">
                    <h4>BUSQUEDA DE PRODUCTOS</h4>
                    <div>
                        <label>
                            <input className="input-invoice-from-input" type="text" name="id"
                                   placeholder="Buscar por id" onChange={(e) => props.onChangeHandler(e)}/>
                        </label>
                        <input className="search-client-from-input" type="submit" value="Buscar"
                               onClick={props.productShowHandler}/>
                        <label>
                            <input className="input-invoice-from-input" type="text" name="name"
                                   placeholder="Buscar por nombre" onChange={(e) => props.onChangeHandler(e)}/>
                        </label>
                        <input className="search-client-from-input" type="submit" value="Buscar"
                               onClick={props.productShowHandler}/>
                        <label>
                            <input className="input-invoice-from-input" type="text" name="isEnable"
                                   placeholder="Buscar por estado" onChange={(e) => props.onChangeHandler(e)}/>
                        </label>
                        <input className="search-client-from-input" type="submit" value="Buscar"
                               onClick={props.productShowHandler}/>
                        <label>
                            <input className="input-invoice-from-input" type="text" name="colour"
                                   placeholder="Buscar por color" onChange={(e) => props.onChangeHandler(e)}/>
                        </label>
                        <input className="search-client-from-input" type="submit" value="Buscar"
                               onClick={props.productShowHandler}/>
                        <label>
                            <input className="input-invoice-from-input" type="text" name="waist"
                                   placeholder="Buscar por talle" onChange={(e) => props.onChangeHandler(e)}/>
                        </label>
                        <input className="search-client-from-input" type="submit" value="Buscar"
                               onClick={props.productShowHandler}/>
                    </div>
                </div>
            </form>
            <div> {/* Body invoice - Show invoice model */}
                <Modal size="xl" show={props.showProduct} onHide={props.productCloseHandler}>
                    <Modal.Header closeButton>
                        <Modal.Title>Seleccionar producto</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <div>
                            <div className="table table-body table-responsive">
                                <table className="table table-body">
                                    <thead>
                                    <tr>
                                        <th scope="col">Producto</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Descripcion</th>
                                        <th scope="col">Color</th>
                                        <th scope="col">Talle</th>
                                        <th scope="col">Cantidad</th>
                                        <th scope="col">Precio</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {props.products && props.products.length > 0 ? props.products.map((product, index) => {
                                        return (
                                            <tr key={index} className="table table-body-data">
                                                <th scope="row">{product.idProduct.toUpperCase()}</th>
                                                <th scope="row">{product.name.toUpperCase()}</th>
                                                <th scope="row">{product.description.toUpperCase()}</th>
                                                <th scope="row">{product.colour.toUpperCase()}</th>
                                                <th scope="row">{product.waist}</th>
                                                <th scope="row">{product.quantity}</th>
                                                <th scope="row">${product.salePrice}</th>
                                                <Button className="client-high-button-select" variant="contained" size="small" color="success"
                                                        onClick={() => props.productSelectedHandler(product)}>Elegir
                                                </Button>
                                            </tr>
                                        )
                                    }) : []
                                    }
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button className="client-high-button-error" variant="contained" size="large" color="error"
                                onClick={props.productCloseHandler}>Cerrar</Button>
                    </Modal.Footer>
                </Modal>
            </div>
        </div>
    )
}

export default ProductSearch;