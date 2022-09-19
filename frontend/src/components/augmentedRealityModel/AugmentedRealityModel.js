import React from 'react';
import './AugmentedRealityModel.sass'
import {Loading} from "../loading/Loading";
import NavbarComponent from "../UI/header/Navbar";
import Footer from "../UI/footer/Footer";

function AugmentedRealityModel() {

    return (
        <div className="home">
                <div>
                    <NavbarComponent></NavbarComponent>
                    <div className="">
                        <div>
                            <h1 className="table-title">Modelos de prendas temporada 2023</h1>
                            <p>Las siguientes prendas corresponden a la proxima temporada, las cuales van a estar
                                disponibles a principios de año.</p>
                        </div>
                        <div className="home-right">
                            <h3 className="title-models">Remeras de hombre</h3>
                            <div className="img-container">
                                <iframe title="model1" src="https://app.vectary.com/p/4V0Ubg6Hy2kzvbhxdHklyb"
                                        frameborder="0" width="30%" height="200"></iframe>
                                <iframe title="model2" src="https://app.vectary.com/p/2rcQbx1pRPLQelvpcC8XES"
                                        frameborder="0" width="30%" height="200"></iframe>
                                <iframe title="model3" src="https://app.vectary.com/p/5vSNvIJ5QibbKc8bqV9Mm3"
                                        frameborder="0" width="30%" height="200"></iframe>
                            </div>
                            <h3 className="title-models">Ojotas</h3>
                            <div className="img-container">
                                <iframe title="model4" src="https://app.vectary.com/p/4x2XH4s9SLp2QqDrpmZ4JM"
                                        frameBorder="0" width="30%" height="200"></iframe>
                                <iframe title="model5" src="https://app.vectary.com/p/24MVMABsvPk6WBuhllhxE0"
                                        frameBorder="0" width="30%" height="200"></iframe>
                                <iframe title="model6" src="https://app.vectary.com/p/47Z5x9a6d5MAslkNiX1MKJ"
                                        frameBorder="0" width="30%" height="200"></iframe>
                            </div>
                            <h3 className="title-models">Buzos</h3>
                            <div className="img-container">
                                <iframe title="model7" src="https://app.vectary.com/p/0x9gfLbDAIQrlJaqxyjEVn"
                                        frameBorder="0" width="30%" height="200"></iframe>
                                <iframe title="model8" src="https://app.vectary.com/p/6zah3EHyiBTIH7hPboafib"
                                        frameBorder="0" width="30%" height="200"></iframe>
                                <iframe title="model9" src="https://app.vectary.com/p/3Nc755Zec5X3FqUFXV8snD"
                                        frameBorder="0" width="30%" height="200"></iframe>
                            </div>
                            <h3 className="title-models">Remeras de mujer</h3>
                            <div className="img-container">
                                <iframe title="model10" src="https://app.vectary.com/p/2oG9ppVUNAQBdQGGQjzI68"
                                        frameBorder="0" width="30%" height="200"></iframe>
                                <iframe title="model11" src="https://app.vectary.com/p/4sWj7Uhrtn0AhY996FmwS0"
                                        frameBorder="0" width="30%" height="200"></iframe>
                                <iframe title="model12" src="https://app.vectary.com/p/6Ysf9mFpnAt7cJknlnNbpI"
                                        frameBorder="0" width="30%" height="200"></iframe>
                            </div>
                        </div>
                    </div>
                    <Footer></Footer>
                </div>
        </div>
    )
}

export default AugmentedRealityModel;