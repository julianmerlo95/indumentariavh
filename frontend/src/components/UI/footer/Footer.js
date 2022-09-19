import React from 'react';
import './Footer.sass'

import {LogoutButton} from "../../login/Logout";

function Footer() {

    return (
        <footer className="page-footer font-small teal pt-4">
            <div className="container-fluid text-center text-md-left">
                <br/>
                <div className="row">
                    <div className="footer-left col-md-6 mt-md-0 mt-3">
                        <h5 className="font-weight-bold">SOBRE EL USUARIO</h5>
                        <p className="footer-p-left">Usuario actual: julian</p>
                        <p className="footer-p-left">Correo actual: julian@indumentariavh.com</p>
                        <p className="footer-p-left">Rol: Empleado</p>
                        <LogoutButton></LogoutButton>
                        <br></br>
                    </div>
                    <hr className="clearfix w-100 d-md-none pb-3"></hr>
                    <div className="col-md-6 mb-md-0 mb-3">
                        <h5>LINKS RAPIDOS</h5>
                        <a className="a-footer" href="/high/client"> Alta de cliente</a> <br></br>
                        <a className="a-footer" href="/high/product"> Alta de producto</a> <br></br>
                        <a className="a-footer" href="/sales/invoice"> Factura</a> <br></br>
                        <a className="a-footer" href="/sales/current-account-client"> Cuenta corriente/fiado</a>
                    </div>
                </div>
            </div>
            <div className="footer-copyright text-center py-3">Version de la app: 1.0 - © 2020 Copyright:
                <a className="a-footer" href="/home"> INDUMENTARIAVH</a>
            </div>
        </footer>
    );
}

export default Footer;