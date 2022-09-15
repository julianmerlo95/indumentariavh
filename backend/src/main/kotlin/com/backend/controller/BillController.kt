package com.backend.controller

import com.backend.domain.Constants
import com.backend.domain.dto.bill.BillDto
import com.backend.domain.dto.billDetail.BillDetailDto
import com.backend.domain.serializable.BillCreateSerializable
import com.backend.domain.serializable.BillDetaillSerializable
import com.backend.domain.serializable.BillSerializable
import com.backend.routings.*
import com.backend.service.BillService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.ktor.ext.inject

fun Route.bill() {

    val billService by inject<BillService>()

    route("/api/v1/bills/bill") {
        post {
            try {
                val billCreateSerializable = call.receive<BillCreateSerializable>()
                val response = billService.createBill(billCreateSerializable)
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetAllByRegisterDateBill> {
        withContext(Dispatchers.IO) {
            try {
                val response = billService.findAllBillByDateRegister()

                if (response != null) {
                    val responseSerializable = convertAllBillsToSerializable(response)
                    call.respond(responseSerializable)
                }
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetFirstByRegisterDateBill> {
        withContext(Dispatchers.IO) {
            try {
                val response = billService.findFirstBillByDateRegister()

                if (response != null) {
                    val responseSerializable = convertAllBillsToSerializable(response)
                    call.respond(responseSerializable)
                }
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByIdBill> {
        withContext(Dispatchers.IO) {
            try {
                val idBill = call.parameters["id"]
                val response = billService.findByIdBill(idBill)

                if (response != null) {
                    val responseSerializable = convertAllBillsToSerializable(response?.bill)
                    call.respond(responseSerializable)
                }

                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetDetailsByIdBill> {
        withContext(Dispatchers.IO) {
            try {
                val idBill = call.parameters["id"]
                val response = billService.findDetailsByIdBill(idBill)

                if (response != null) {
                    val responseSeriabilizable = convertAllBillDetailsToSerializable(response)
                    call.respond(responseSeriabilizable)
                }

                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }


    get<GetByIdUserBill> {
        withContext(Dispatchers.IO) {
            try {
                val idUserBill = call.parameters["idUser"]

                if (idUserBill != null) {
                    val response = billService.findBillByParam(Constants.KEY_ID_USER, idUserBill)
                    val responseSerializable = convertAllBillsToSerializable(response?.bill)
                    call.respond(responseSerializable)
                } else {
                    call.respond(HttpStatusCode.OK, "The idUser cannot be empty o null")
                }
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByIdClientBill> {
        withContext(Dispatchers.IO) {
            try {
                val idClientBill = call.parameters["idClient"]

                if (idClientBill != null) {
                    val response = billService.findBillByParam(Constants.KEY_ID_CLIENT, idClientBill)
                    val responseSerializable = convertAllBillsToSerializable(response?.bill)
                    call.respond(responseSerializable)
                } else {
                    call.respond(HttpStatusCode.OK, "The idClient cannot be empty o null")
                }
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByIdBillDetailBill> {
        withContext(Dispatchers.IO) {
            try {
                val idBillDetailBill = call.parameters["idBillDetail"]

                if (idBillDetailBill != null) {
                    val response = billService.findBillByParam(Constants.KEY_ID_BILL_DETAIL, idBillDetailBill)
                    val responseSerializable = convertAllBillsToSerializable(response?.bill)
                    call.respond(responseSerializable)
                } else {
                    call.respond(HttpStatusCode.OK, "The idBillDetail cannot be empty o null")
                }
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByiIdBillStatusBill> {
        withContext(Dispatchers.IO) {
            try {
                val idBillStatusBill = call.parameters["idBillStatus"]

                if (idBillStatusBill != null) {
                    val response = billService.findBillByParam(Constants.KEY_ID_BILL_STATUS, idBillStatusBill)
                    val responseSerializable = convertAllBillsToSerializable(response?.bill)
                    call.respond(responseSerializable)
                } else {
                    call.respond(HttpStatusCode.OK, "The idBillStatus cannot be empty o null")
                }
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByidFromBillStatusBill> {
        withContext(Dispatchers.IO) {
            try {
                val fromDate = call.parameters["fromDate"]

                if (fromDate != null) {
                    val response = billService.findBillByParam(Constants.KEY_FROM_DATE, fromDate)
                    val responseSerializable = convertAllBillsToSerializable(response?.bill)
                    call.respond(responseSerializable)
                } else {
                    call.respond(HttpStatusCode.OK, "The idBillStatus cannot be empty o null")
                }
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByTodayDateBill> {
        withContext(Dispatchers.IO) {
            try {
                val response = billService.findTodayBillsByDateRegister()
                val responseSerializable = convertAllBillsToSerializable(response)
                call.respond(responseSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }
}

private fun convertAllBillsToSerializable(billDto: MutableList<BillDto?>?): MutableList<BillSerializable?> {
    val billSerializableList = mutableListOf<BillSerializable?>()

    billDto?.forEach {
        billSerializableList.add(
            BillSerializable(
                it?.idBill,
                it?.totalAmount,
                it?.totalDiscount,
                it?.cash,
                it?.credit,
                it?.borrowedCash,
                it?.idBillType,
                it?.idUser,
                it?.idBillStatus,
                it?.idClient,
                it?.idPaymentMethod,
                it?.dateRegister.toString()
            )
        )
    }

    return billSerializableList
}

private fun convertAllBillDetailsToSerializable(billDetail: MutableList<BillDetailDto?>?): MutableList<BillDetaillSerializable?> {
    val billDetaillSerializable = mutableListOf<BillDetaillSerializable?>()

    billDetail?.forEach {
        billDetaillSerializable.add(
            BillDetaillSerializable(
                it?.idBill,
                it?.quantity,
                it?.price,
                it?.idProduct,
                it?.colour,
                it?.waist,
                it?.dateRegister.toString()
            )
        )
    }

    return billDetaillSerializable
}