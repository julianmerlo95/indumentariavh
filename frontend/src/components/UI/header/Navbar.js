import React from 'react';
import './Navbar.sass'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import {LogoutButton} from "../../login/Logout";

import {LoginButton} from "../../login/Login";
import logoImg from '../../../assets/logo.png';

function NavbarComponent() {
    const date = new Date();

    return (
        <div>
            <div className="navbar-date-container">
                <h6 className="navbar-date">{date.toLocaleString()}</h6>
            </div>
            <Navbar className="navbar" bg="light" expand="lg">
                <Container>
                    <img onClick={() => window.location.replace('/home')} alt="img-logo" className="img-logo" src={logoImg}/>
                    <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                    <Navbar.Collapse className="NavbarComponent_collapse" id="basic-navbar-nav">
                        <Nav className="NavbarComponent_left me-auto">
                            <Nav.Link href="/system">SISTEMA</Nav.Link>
                            <NavDropdown title="ALTAS" id="basic-nav-dropdown">
                                <NavDropdown.Item href="/high/client">Cliente</NavDropdown.Item>
                                <NavDropdown.Divider/>
                                <NavDropdown.Item href="/high/product">Producto</NavDropdown.Item>
                            </NavDropdown>
                            <NavDropdown title="VENDER" id="basic-nav-dropdown" className="span-sale">
                                <NavDropdown.Item href="/sales/invoice">Factura</NavDropdown.Item>
                                <NavDropdown.Divider/>
                                <NavDropdown.Item href="/sales/current-account-client">Cuenta corriente/fiado</NavDropdown.Item>
                            </NavDropdown>
                            <Nav.Link href="/expenses">GASTOS</Nav.Link>
                            <NavDropdown title="CONSULTAS" id="basic-nav-dropdown">
                                <NavDropdown.Item href="/consultation/sales">Ventas</NavDropdown.Item>
                                <NavDropdown.Item href="/consultation/sales/details">Detalle de una venta</NavDropdown.Item>
                                <NavDropdown.Divider/>
                                <NavDropdown.Item href="/consultation/expenses">Gastos</NavDropdown.Item>
                                <NavDropdown.Item href="/consultation/current-account-client">Cuenta corriente/fiado</NavDropdown.Item>
                                <NavDropdown.Divider/>
                                <NavDropdown.Item href="/consultation/daily-cash-balance">Balance de caja diario</NavDropdown.Item>
                                <NavDropdown.Divider/>
                                <NavDropdown.Item href="/consultation/stocks">Stock</NavDropdown.Item>
                                <NavDropdown.Divider/>
                                <NavDropdown.Item href="/consultation/clients">Clientes</NavDropdown.Item>
                            </NavDropdown>
                            <NavDropdown title="REALIDAD AUMENTADA" id="basic-nav-dropdown">
                                <NavDropdown.Item href="/augmentedReality.html">Camara</NavDropdown.Item>
                                <NavDropdown.Divider/>
                                <NavDropdown.Item href="/augmented-reality/models">Modelos</NavDropdown.Item>
                            </NavDropdown>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </div>
    );
}

export default NavbarComponent;