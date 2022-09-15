import React from 'react';
import './Error.sass'
import imgError from '../../../assets/error.png';
import {Link} from "react-router-dom";
import Button from "@mui/material/Button";
import Navbar from "../../UI/header/Navbar";

function Error() {
    return (
        <div className="home">
            <Navbar></Navbar>
            <div className="home-body">
                <div className="home-left">
                    <h4>¡Hubo un error al guardar la deuda del cliente, valide si se guardo la factura correctamente en el apartado de [CONSULTAS/Ventas],
                    de ser asi genere una factura en el apartado de [VENDER/Cuenta corriente/fiado]!</h4>
                    <hr/>
                    <img className="img-error" src={imgError}/>
                    <Link to="/sales/current-account-client">
                        <Button className="client-high-button-error" variant="contained" size="large" color="error">
                            Ir a Cuenta corriente/fiado
                        </Button>
                    </Link>
                </div>
            </div>
        </div>
    )
}

export default Error;