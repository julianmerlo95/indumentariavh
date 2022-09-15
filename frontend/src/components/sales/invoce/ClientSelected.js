import React from 'react';
import Modal from "react-bootstrap/Modal";
import Button from "@mui/material/Button";


function ClientSelected(props) {
    return (
        <div className="invoice-form-header-description-client">
            <div>
                <h4>CLIENTE SELECCIONADO</h4>
                <table className="cliente-table-data">
                    <thead>
                    <tr>
                        <th scope="col">ID: {props.clientSelected.idClient}</th>
                        <th scope="col">NOMBRE: {props.clientSelected.name}, {props.clientSelected.lastName}</th>
                        <th scope="col">DEUDA: $<span className="moneyDebt-client-span">{ props.clientSelected.moneyDebt ? new Intl.NumberFormat("es-CL").format(Number(props.clientSelected.moneyDebt)) : ""}</span></th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div>
                <Modal className="invoice-form-header-description-seller-modal" size="lg" show={props.showClient}
                       onHide={props.clientCloseHandler}>
                    <Modal.Header closeButton>
                        <Modal.Title>Listado de clientes</Modal.Title>
                    </Modal.Header>
                    <Modal.Body className="invoice-form-header-description-seller-modal-body">
                        <div>
                            <div className="table table-body table-responsive">
                                <table className="table table-body">
                                    <thead>
                                    <tr>
                                        <th scope="col">Id</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Apellido</th>
                                        <th scope="col">Dni</th>
                                        <th scope="col">Estado</th>
                                        <th scope="col">Deuda</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {props.clients && props.clients.length > 0 ? props.clients.map((client, index) => {
                                        return (
                                            <tr key={index} className="table table-body-data">
                                                <th scope="row">{client.idClient}</th>
                                                <th scope="row">{client.name}</th>
                                                <th scope="row">{client.lastName}</th>
                                                <th scope="row">{client.dni}</th>
                                                <th scope="row">{client.isEnable === 1 ? "HABILITADO" : "DESHABILITADO"}</th>
                                                <th scope="row">$<span className="moneyDebt-client-span">{new Intl.NumberFormat("es-CL").format(client.moneyDebt)}</span></th>
                                                {client.isEnable === 1 ?
                                                    <Button className="client-high-button-select" variant="contained"
                                                            size="small" color="success"
                                                            onClick={() => props.clientSelectedHandler(client)}>Elegir
                                                    </Button> :
                                                    <Button disabled className="client-high-button-select" variant="contained"
                                                            size="small"
                                                            onClick={() => props.clientSelectedHandler(client)}>Inactivo
                                                    </Button>}
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
                                onClick={props.clientCloseHandler}>Cerrar
                        </Button>
                    </Modal.Footer>
                </Modal>
            </div>
        </div>
    )
}

export default ClientSelected;