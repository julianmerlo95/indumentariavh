package com.backend.routings

import de.nielsfalk.ktor.swagger.version.shared.Group
import io.ktor.server.locations.Location

// ---------------------------------------------------------------
@Group("Client")
@Location("/api/v1/clients")
class GetAllByRegisterDateClient
@Location("/api/v1/clients/{id}")
class GetByIdClient(val id: Long)
@Location("/api/v1/clients")
class GetByDniClient(val dni: Long)
@Location("/api/v1/clients")
class GetByMailClient(val mail: String)
@Location("/api/v1/clients")
class GetByNameClient(val name: String)
@Location("/api/v1/clients")
class GetByLastNameClient(val lastName: String)
@Location("/api/v1/clients")
class GetByDateOfBirthClient(val dateOfBirth: String)
@Location("/api/v1/clients")
class GetByIsEnableClient(val isEnable: String)
// ---------------------------------------------------------------
@Group("Bill")
@Location("/api/v1/bills")
class GetAllByRegisterDateBill
@Location("/api/v1/bills/first")
class GetFirstByRegisterDateBill
@Location("/api/v1/bills/{id}")
class GetByIdBill(val id: Long)
@Location("/api/v1/bills")
class GetByIdUserBill(val idUser: Int)
@Location("/api/v1/bills")
class GetByIdClientBill(val idClient: Int)
@Location("/api/v1/bills")
class GetByIdBillDetailBill(val idBillDetail: Int)
@Location("/api/v1/bills")
class GetByiIdBillStatusBill(val idBillStatus: Int)
@Location("/api/v1/bills")
class GetByidFromBillStatusBill(val fromDate: String)
@Location("/api/v1/bills/today")
class GetByTodayDateBill()
@Location("/api/v1/bills/{id}/details")
class GetDetailsByIdBill(val id: Long)
// ---------------------------------------------------------------
@Group("User")
@Location("/api/v1/users")
class GetAllByRegisterDateUsers
@Location("/api/v1/users/{id}")
class GetByIdUser(val id: Long)
// ---------------------------------------------------------------
@Group("Expenses")
@Location("/api/v1/expenses")
class GetAllByRegisterDateExpenses
@Location("/api/v1/expenses")
class GetByIdUserExpenses(val idUser: Int)
@Location("/api/v1/expenses")
class GetByFromDateExpenses(val fromDate: String?)
// ---------------------------------------------------------------
@Group("Product")
@Location("/api/v1/products")
class GetAllByRegisterDateProducts
@Location("/api/v1/products/first")
class GetFirstByRegisterDateProducts
@Location("/api/v1/products/{id}")
class GetByIdProduct(val id: String)
@Location("/api/v1/products")
class GetByNameProduct(val name: String)
@Location("/api/v1/products")
class GetByDescripcionProduct(val description: String)
@Location("/api/v1/products")
class GetByColourProduct(val colour: String)
@Location("/api/v1/products")
class GetByWaistProduct(val waist: String)
@Location("/api/v1/products")
class GetByIsEnableProduct(val isEnable: String)
// ---------------------------------------------------------------
@Group("AugmentedReality")
@Location("/api/v1/augmented-reality/{id}")
class GetByIdAugmentedReality(val id: Long)
@Location("/api/v1/augmented-reality")
class GetByIdProductAugmentedReality(val idProduct: String)
// ---------------------------------------------------------------
@Group("HealthCheck")
@Location("/api/v1/health-check")
class GetHealthCheck
// ---------------------------------------------------------------
@Group("CurrentAccountClient")
@Location("/api/v1/current-account-client")
class GetAllByRegisterDateCurrentAccountClient
@Location("/api/v1/current-account-client/first")
class GetFirstByRegisterDateCurrentAccountClient
@Location("/api/v1/current-account-client/{id}")
class GetByIdCurrentAccountClient(val id: Int)
@Location("/api/v1/current-account-client")
class GetByIdClientCurrentAccountClient(val idClient: String)
@Location("/api/v1/current-account-client")
class GetByFromDateClientCurrentAccountClient(val fromDate: String)
@Location("/api/v1/current-account-client/today")
class GetByTodayDateCurrentAccountClient()
// ---------------------------------------------------------------
@Group("Report")
@Location("/api/v1/report")
class GetByIdReport(val id: Long)