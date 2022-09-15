
function BuildBillDto(idBill, totalInvoice, discount, cash, credit, borrowedCash, idUser, idClient, paymentMethodSelected, listProductSelected) {
    let bill = {
        "idBill": `${idBill}`,
        "totalAmount": `${totalInvoice}`,
        "totalDiscount": `${discount}`,
        "cash": `${cash}`,
        "credit": `${credit}`,
        "borrowedCash": `${borrowedCash}`,
        "idBillType": "1",
        "idUser": `${idUser}`,
        "idClient": `${idClient}`,
        "idBillStatus": "1",
        "idPaymentMethod": `${paymentMethodSelected.idPaymentMethod}`,
        "listBillDetail": listProductSelected.map((product, index) => {
            let productDto = {
                "idBill": `${idBill}`,
                "quantity": 1,
                "price": product.salePrice,
                "idProduct": product.idProduct,
                "colour": product.colour,
                "waist": product.waist
            }
            return productDto
        })
    }

    return bill
}

export default BuildBillDto;