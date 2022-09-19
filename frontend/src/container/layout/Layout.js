import React, {Suspense} from 'react';
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import Home from '../../components/home/Home';
import ClientHigh from '../../components/high/client/ClientHigh';
import ProductHigh from '../../components/high/product/ProductHigh';
import ClientsConsultation from '../../components/consultation/client/ClientsConsultation';
import StocksConsultation from '../../components/consultation/stock/StocksConsultation';
import CurrentAccountClientConsultation from '../../components/consultation/sale/currentAccountClient/CurrentAccountClientConsultation';
import InvoiceConsultation from '../../components/consultation/sale/invoice/InvoiceConsultation';
import Invoice from '../../components/sales/invoce/Invoice';
import CurrentAccountClient from '../../components/sales/currentAccountClient/CurrentAccountClient';
import Success from '../../components/warning/success/Success';
import Error from '../../components/warning/error/Error';
import ErrorClient from '../../components/warning/error/ErrorClient';
import System from '../../components/system/System';
import Login from "../../components/login/Login";
import DailyCashBalance from "../../components/consultation/dailyCashBalance/DailyCashBalance";
import InvoiceDetail from "../../components/consultation/sale/invoiceDetail/InvoiceDetail";
import AugmentedRealityModel from '../../components/augmentedRealityModel/AugmentedRealityModel';
import Expenses from "../../components/expenses/Expenses";
import ExpensesConsultation from "../../components/consultation/expenses/Expenses";

function Layout() {

    return (
        <>
            <Suspense>
                <BrowserRouter>
                    <Routes>

                        <Route  path="/" element={<Home/>}/>
                        <Route  path="/home" element={<Home/>}/>
                        <Route  path="/system" element={<System/>}/>

                        <Route  path="/sales/invoice" element={<Invoice/>}/>
                        <Route  path="/sales/current-account-client" element={<CurrentAccountClient/>}/>

                        <Route  path="/high/client" element={<ClientHigh/>}/>
                        <Route  path="/high/product" element={<ProductHigh/>}/>

                        <Route  path="/expenses" element={<Expenses/>}/>

                        <Route  path="/consultation/sales" element={<InvoiceConsultation/>}/>
                        <Route  path="/consultation/sales/details" element={<InvoiceDetail/>}/>
                        <Route  path="/consultation/current-account-client" element={<CurrentAccountClientConsultation/>}/>
                        <Route  path="/consultation/expenses" element={<ExpensesConsultation/>}/>
                        <Route  path="/consultation/stocks" element={<StocksConsultation/>}/>
                        <Route  path="/consultation/clients" element={<ClientsConsultation/>}/>
                        <Route  path="/consultation/reports" element={<Home/>}/>
                        <Route  path="/consultation/daily-cash-balance" element={<DailyCashBalance/>}/>

                        <Route  path="/augmented-reality/models" element={<AugmentedRealityModel/>}/>

                        <Route  path="/success" element={<Success/>}/>
                        <Route  path="/error" element={<Error/>}/>
                        <Route  path="/error-client" element={<ErrorClient/>}/>
                    </Routes>
                </BrowserRouter>
            </Suspense>
        </>
    );
}

export default Layout;