import React from 'react';
import Modal from "react-bootstrap/Modal";
import Button from "@mui/material/Button";

function SellerSelected(props) {
    return (
        <div className="invoice-form-header-description-seller">
            <div>
                <h4>VENDEDOR SELECCIONADO</h4>
                <th scope="col">Nombre: {props.sellerSelected.name} {props.sellerSelected.lastName}</th>
            </div>
            <div>
                <Modal size="lg" show={props.showSeller} onHide={props.sellerCloseHandler}>
                    <Modal.Header closeButton>
                        <Modal.Title>Seleccionar vendedor</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <div>
                            <div className="table table-body table-responsive">
                                <table className="table table-body">
                                    <thead>
                                    <tr>
                                        <th scope="col">Usuario</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Apellido</th>
                                        <th scope="col">Mail</th>
                                        <th scope="col">Role</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {props.seller && props.seller.length > 0 ? props.seller.map((sell, index) => {
                                        return (
                                            <tr key={index} className="table table-body-data">
                                                <th scope="row">{sell.userName}</th>
                                                <th scope="row">{sell.name}</th>
                                                <th scope="row">{sell.lastName}</th>
                                                <th scope="row">{sell.mail}</th>
                                                <th scope="row">{sell.idRole === 1 ? "DUEÑO" : "EMPLEADO"}</th>
                                                <Button className="client-high-button-select" variant="contained" size="small" color="success"
                                                        onClick={() => props.sellerSelectedHandler(sell)}>Elegir
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
                                onClick={props.sellerCloseHandler}>Cerrar
                        </Button>
                    </Modal.Footer>
                </Modal>
            </div>
        </div>
    )
}

export default SellerSelected;