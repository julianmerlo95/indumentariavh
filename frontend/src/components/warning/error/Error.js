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
                    <h1>¡Hubo un error, volve a intentar!</h1>
                    <hr/>
                    <img className="img-error" src={imgError}/>
                    <Link to="/home">
                        <Button className="client-high-button-error" variant="contained" size="large" color="error">
                            Volver al inicio
                        </Button>
                    </Link>
                </div>
            </div>
        </div>
    )
}

export default Error;