document.addEventListener("DOMContentLoaded", function () {

    const quantidadeInput =
        document.getElementById("quantidade");

    const valorUnitarioInput =
        document.getElementById("valorUnitario");

    const displayTotal =
        document.getElementById("displayItemTotal");


    function atualizarTotal() {

        const quantidade =
            parseFloat(quantidadeInput?.value) || 0;

        const valorUnitario =
            parseFloat(valorUnitarioInput?.value) || 0;


        const total =
            quantidade * valorUnitario;


        if (displayTotal) {

            displayTotal.textContent =
                total.toLocaleString(
                    "pt-BR",
                    {
                        style: "currency",
                        currency: "BRL"
                    }
                );

        }

    }


    if (quantidadeInput) {

        quantidadeInput.addEventListener(
            "input",
            atualizarTotal
        );

    }


    if (valorUnitarioInput) {

        valorUnitarioInput.addEventListener(
            "input",
            atualizarTotal
        );

    }


    atualizarTotal();

});