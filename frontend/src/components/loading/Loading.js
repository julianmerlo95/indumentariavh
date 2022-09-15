import React from "react";
import "./Loading.sass";
import NavbarComponent from "../UI/header/Navbar";

export const Loading = () => {
    return (
        <div>
            <NavbarComponent></NavbarComponent>
            <div className="load">
                <div className="load_spinner"></div>
            </div>
        </div>
    );
};