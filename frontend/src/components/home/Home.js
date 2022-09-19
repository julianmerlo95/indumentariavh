import React from 'react';
import './Home.sass'
import imgHome from '../../assets/home.png';
import {Loading} from "../loading/Loading";
import NavbarComponent from "../UI/header/Navbar";
import Footer from "../UI/footer/Footer";

function Home() {
    
    return (
        <div className="home">
            
                <div>
                    <NavbarComponent></NavbarComponent>
                    <div className="home-body">
                        <div className="home-left">
                            <h1 className="home-title">INDUMENTARIAVH</h1>
                            <hr/>
                            <p>Sistema de gestion de indumentaria deportiva integrado a realidad aumentada.</p>
                        </div>
                        <div className="home-right">
                            <img alt="img-home" className="img-home" src={imgHome}/>
                        </div>
                    </div>
                    <Footer></Footer>
                </div>
        </div>
    )
}

export default Home;