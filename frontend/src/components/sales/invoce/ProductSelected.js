import React from 'react';
import Button from "@mui/material/Button";

function ProductSelected(props) {
    return (
        <div className="product-selected-table invoice-form-body-details table-responsive"> {/* Body invoice show data */}
            <div className="invoice-form-body-details-products">
                <h4>PRODUCTOS SELECCIONADOS</h4>
                <table className="table-invoice-body-table invoice-body-table">
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
                    {props.listProductSelected && props.listProductSelected.length > 0 ? props.listProductSelected.map((product, index) => {
                        return (
                            <tr key={index} className="table table-body-data productSelected-table">
                                <th scope="row">{product.idProduct}</th>
                                <th scope="row">{product.name}</th>
                                <th scope="row">{product.description}</th>
                                <th scope="row">{product.colour}</th>
                                <th scope="row">{product.waist}</th>
                                <th scope="row">1</th>
                                <th scope="row">${new Intl.NumberFormat("es-CL").format(product.salePrice)}</th>
                                <Button className="client-high-button-remove" variant="contained" size="small" color="error"
                                        onClick={() => props.removeProductHandler(index)}>Quitar
                                </Button>
                            </tr>
                        )
                    }) : []
                    }
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default ProductSelected;