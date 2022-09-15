import React from "react";
import {useAuth0} from "@auth0/auth0-react";
import imgLogin from "../../assets/login1.png";
import Button from "@mui/material/Button";
import './Login.sass';
import Navbar from "react-bootstrap/Navbar";
import Container from "react-bootstrap/Container";
import Home from "../home/Home";
import logoImg from "../../assets/logo.png";

export const LoginButton = () => {
    const {loginWithRedirect} = useAuth0();
    return <Button onClick={() => loginWithRedirect()} className="client-high-button-error" variant="contained"
                   size="small" color="success">
                    Iniciar sesion
            </Button>
};

function Login() {
    const {isAuthenticated} = useAuth0();
    return (
        <div>
            {isAuthenticated ?
                <Home/>
                : <div>
                    <div>
                        <Navbar className="navbar" bg="light" expand="lg">
                            <Container>
                                <img alt="img-logo" className="img-logo" src={logoImg}/>
                                <div>
                                    <LoginButton></LoginButton>
                                </div>
                            </Container>
                        </Navbar>
                    </div>
                    <h1 className="title-login">Inicio de sesion</h1>
                    <p>Para ingresar en el sistema oprima el boton "iniciar sesion"</p>
                    <div>
                        <img alt="login-img" className="login-img" src={imgLogin}/>
                    </div>
                    <LoginButton></LoginButton>
                </div>}
        </div>
    )
}

export default Login;