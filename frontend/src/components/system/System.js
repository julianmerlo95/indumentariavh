import React from 'react';
import './System.sass'
import imgSystem from '../../assets/system.png';

import NavbarComponent from "../UI/header/Navbar";
import {Loading} from "../loading/Loading";
import Footer from "../UI/footer/Footer";

function System() {
    

    return (
        <div className="system">
            
                <div>
                    <NavbarComponent></NavbarComponent>
                    <div className="system-body">
                        <div className="system-left">
                            <img className="img-system" src={imgSystem}/>
                        </div>
                        <div className="system-right">
                            <h1 className="system-title">INDUMENTARIAVH</h1>
                            <hr/>
                            <p>El presente trabajo final de grado, tuvo como objetivo indagar sobre el funcionamiento de los
                                sistemas de gestión; los cuales, se encargan de administrar las actividades de un local
                                comercial. Resulta de vital importancia analizar de qué forma se registran las actividades
                                dentro de un local comercial, ya que, a través del control de las mismas, se logra
                                planificar,
                                organizar y sistematizar las distintas tareas. De esta manera, con el propósito de ofrecer
                                una
                                propuesta que actúe como un diferencial en relación a otros sistemas y además, aporte
                                innovación
                                en su funcionamiento; se propuso crear un sistema de gestión. Dicho sistema, es capaz de
                                controlar el stock, generar informes y realizar la facturación de forma eficaz y eficiente.
                                Por
                                otra parte, se destacó la implementación de realidad aumentada en la presentación de los
                                productos, lo cual, nos permitió innovar en un producto tradicional y entregar valor
                                agregado.
                                Dichos factores, generaron un impacto positivo en el local comercial dónde se implementó por
                                su
                                capacidad de organización, rapidez y control. Pero además, le dio la posibilidad al cliente
                                de
                                vivir una nueva experiencia de compra.
                            </p>
                        </div>
                    </div>
                    <Footer></Footer>
                </div>
        </div>
    )
}

export default System;