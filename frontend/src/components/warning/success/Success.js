import React from 'react';
import './Success.sass'
import imgSuccess from '../../../assets/success.png';
import {Link} from "react-router-dom";
import Button from "@mui/material/Button";
import Navbar from "../../UI/header/Navbar";

function Success() {
    return (
        <div className="home">
            <Navbar></Navbar>
            <div className="home-body">
                <div className="home-left">
                    <h1 className="home-title">¡Se guardo correctamente!</h1>
                    <hr/>
                    <img alt="img-home" className="img-home" src={imgSuccess}/>
                    <Link to="/home">
                        <Button className="client-high-button-success" variant="contained" size="large" color="success">
                            Volver al inicio
                        </Button>
                    </Link>
                </div>
            </div>
        </div>
    )
}

export default Success;