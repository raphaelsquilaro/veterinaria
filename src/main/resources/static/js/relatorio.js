document.addEventListener("DOMContentLoaded", function () {

    const btnImprimir =
        document.getElementById("btnImprimir");

    if (btnImprimir) {

        btnImprimir.addEventListener("click", function () {
            window.print();
        });

    }

});