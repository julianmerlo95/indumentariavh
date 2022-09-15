
function BuildCurrentAccountClientDto(clientSelected, sellerSelected, borrowedCash) {
    let currentAccountClient = {
        "idClient": clientSelected.idClient,
        "userName": sellerSelected.userName,
        "actualMoneyDebt": clientSelected.moneyDebt,
        "moneyCredit": borrowedCash,
        "moneyDebit": "0",
        "newMoneyDebt": Number(clientSelected.moneyDebt) + Number(borrowedCash)
    }

    return currentAccountClient
}

export default BuildCurrentAccountClientDto;