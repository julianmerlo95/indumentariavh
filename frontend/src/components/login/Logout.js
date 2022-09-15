import React from "react";
import {useAuth0} from "@auth0/auth0-react";
import Button from "@mui/material/Button";

export const LogoutButton = () => {
    const {logout} = useAuth0();

    return (
        <Button className="client-high-button-error" variant="contained" size="small"
                color="error"
                onClick={() => logout({returnTo: "http://localhost:3000/login"})}>
                    Cerrar sesion
        </Button>
    );
};