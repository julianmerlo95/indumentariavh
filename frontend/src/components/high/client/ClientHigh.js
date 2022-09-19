import React, {useState} from 'react';
import './ClientHigh.sass';
import Button from '@mui/material/Button';
import Form from 'react-bootstrap/Form';
import axios from "axios";
import imgCLient from '../../../assets/client-high2.png';
import {Link} from 'react-router-dom';
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import OutlinedInput from "@mui/material/OutlinedInput";
import Checkbox from '@mui/material/Checkbox';

import {Loading} from "../../loading/Loading";
import NavbarComponent from "../../UI/header/Navbar";
import Footer from "../../UI/footer/Footer";

function ClientHigh() {
    
    const [client, setClient] = useState({idClient: "", name: "", lastName: "", dni: "", dateOfBirth: "", mail: "", isEnable: 0});
    const [checked, setChecked] = React.useState(true);

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setChecked(event.target.checked);
    };

    const onChangeHandler = (e) => {
        if (e.target.name === "mail" && e.target.value.length <= 25) {
            setClient({...client, [e.target.name]: e.target.value});
            return
        }

        if (e.target.value.length <= 15) {
            setClient({...client, [e.target.name]: e.target.value});
            return
        }
        alert("Los campos tienen un maximo de 15 caracteres")
        return
    };

    const createClientHandler = async (event) => {
        client.isEnable = checked === true ? 1 : 0
        try {
            if (client?.idClient?.length <= 15 && client?.name?.length <= 15 && client?.lastName?.length <= 15 &&
                client?.dni?.length <= 15 && client?.dateOfBirth?.length <= 15 && client?.mail?.length <= 25 &&
                 client?.idClient && client?.name && client?.dni) {
                event.preventDefault();
                axios.post(`${process.env.REACT_APP_API_URL}/clients/client`, {client}).then(response => window.location.replace('/success'));
                return
            } else {
                alert("Los campos id, nombre y dni son obligatorios. Ademas del largo de los campos")
            }
        } catch (ex) {
            window.location.replace('/error');
            throw ex
        }
    }

    return (
        <div>
            
                <div>
                    <NavbarComponent></NavbarComponent>
                    <div className="client-high">
                        <div className="client-high-left">
                            <h1 className="client-high-title">Alta de cliente</h1>
                            <img className="img-client-high" src={imgCLient}/>
                        </div>
                        <div className="client-high-right">
                            <Form className="form" onSubmit={(event) => createClientHandler(event)}>
                                <Form.Group className="mb-3" controlId="formBasicEmail">
                                    <FormControl fullWidth sx={{m: 1}} required={true}>
                                        <InputLabel htmlFor="outlined-adornment-amount">Id cliente</InputLabel>
                                        <OutlinedInput
                                            id="outlined-adornment-amount"
                                            name="idClient"
                                            placeholder="Se recomienda que sea el Nro de documento"
                                            onChange={(e) => onChangeHandler(e)}
                                            label="Id cliente"
                                        />
                                    </FormControl>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="formBasicName">
                                    <FormControl fullWidth sx={{m: 1}} required={true}>
                                        <InputLabel htmlFor="outlined-adornment-amount">Nombre</InputLabel>
                                        <OutlinedInput
                                            id="outlined-adornment-amount"
                                            name="name"
                                            placeholder="Nombre"
                                            onChange={(e) => onChangeHandler(e)}
                                            label="Nombre"
                                            type="text"
                                        />
                                    </FormControl>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="formBasicLastName">
                                    <FormControl fullWidth sx={{m: 1}}>
                                        <InputLabel htmlFor="outlined-adornment-amount">Apellido</InputLabel>
                                        <OutlinedInput
                                            id="outlined-adornment-amount"
                                            name="lastName"
                                            placeholder="Apellido"
                                            onChange={(e) => onChangeHandler(e)}
                                            label="Apellido"
                                        />
                                    </FormControl>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="formBasicDateOfBirth">
                                    <FormControl fullWidth sx={{m: 1}}>
                                        <InputLabel htmlFor="outlined-adornment-amount">Fecha de cumpleaños</InputLabel>
                                        <OutlinedInput
                                            id="outlined-adornment-amount"
                                            name="dateOfBirth"
                                            placeholder="Fecha de cumpleaños"
                                            onChange={(e) => onChangeHandler(e)}
                                            label="Fecha de cumpleaños"
                                        />
                                    </FormControl>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="formBasicDni">
                                    <FormControl fullWidth sx={{m: 1}} required={true}>
                                        <InputLabel htmlFor="outlined-adornment-amount">Dni</InputLabel>
                                        <OutlinedInput
                                            id="outlined-adornment-amount"
                                            name="dni"
                                            placeholder="Dni"
                                            onChange={(e) => onChangeHandler(e)}
                                            label="Dni"
                                            type="number"
                                        />
                                    </FormControl>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="formBasicMail">
                                    <FormControl fullWidth sx={{m: 1}}>
                                        <InputLabel htmlFor="outlined-adornment-amount">Mail</InputLabel>
                                        <OutlinedInput
                                            id="outlined-adornment-amount"
                                            name="mail"
                                            placeholder="Mail"
                                            onChange={(e) => onChangeHandler(e)}
                                            label="Mail"
                                        />
                                    </FormControl>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="formBasicMoneyDebt">
                                    <FormControl fullWidth sx={{m: 1}}>
                                        <InputLabel htmlFor="outlined-adornment-amount">Deuda del cliente</InputLabel>
                                        <OutlinedInput
                                            id="outlined-adornment-amount"
                                            name="moneyDebt"
                                            placeholder="Deuda del cliente"
                                            onChange={(e) => onChangeHandler(e)}
                                            label="Deuda del cliente"
                                            type="number"
                                        />
                                    </FormControl>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="formBasicMoneyDebt">
                                    <FormControl fullWidth sx={{m: 1}}>
                                        <InputLabel htmlFor="outlined-adornment-amount">Cliente habilitado</InputLabel>
                                        <Checkbox
                                            checked={checked}
                                            onChange={handleChange}
                                            inputProps={{'aria-label': 'controlled'}}
                                        />
                                    </FormControl>
                                </Form.Group>
                                <Link to="/home">
                                    <Button className="client-button-error" variant="contained" size="large"
                                            color="error">
                                        Cancelar
                                    </Button>
                                </Link>
                                <Button variant="contained" size="large" color="success"
                                        onClick={(event) => createClientHandler(event)}>
                                    Guardar
                                </Button>
                            </Form>
                        </div>
                    </div>
                    <Footer></Footer>
                </div>
        </div>
    )
}

export default ClientHigh;