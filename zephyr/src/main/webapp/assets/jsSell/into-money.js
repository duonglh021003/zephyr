const intoMoneyInput = document.getElementById('intoMoney');
const customerPaymentInput = document.getElementById('customerPayment');
const changeAmountInput = document.getElementById('changeAmount');

customerPaymentInput.addEventListener('input', calculateChangeAmount);


function calculateChangeAmount() {
    const intoMoney = parseFloat(intoMoneyInput.value);
    const customerPayment = parseFloat(customerPaymentInput.value);

    if (!isNaN(customerPayment)) {
        const changeAmount = customerPayment - intoMoney;
        changeAmountInput.value = changeAmount >= 0 ? changeAmount.toFixed(3) : "FALL";
    } else {
        changeAmountInput.value = 0;
    }
}