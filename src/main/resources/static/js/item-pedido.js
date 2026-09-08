document.addEventListener("DOMContentLoaded", function () {

    const searchInput =
        document.getElementById("searchItemPedido");

    const rows =
        document.querySelectorAll(".item-pedido-row");

    const deleteButtons =
        document.querySelectorAll(".btn-delete");


    /*
     * =========================================================
     * BUSCA
     * =========================================================
     */

    if (searchInput) {

        searchInput.addEventListener("input", function () {

            const searchTerm =
                this.value.toLowerCase().trim();


            rows.forEach(function (row) {

                const rowText =
                    row.textContent.toLowerCase();


                if (rowText.includes(searchTerm)) {

                    row.style.display = "";

                } else {

                    row.style.display = "none";

                }

            });

        });

    }


    /*
     * =========================================================
     * CONFIRMAÇÃO DE EXCLUSÃO
     * =========================================================
     */

    deleteButtons.forEach(function (button) {

        button.addEventListener("click", function (event) {

            const confirmed = confirm(
                "Tem certeza que deseja excluir este item do pedido?"
            );


            if (!confirmed) {

                event.preventDefault();

            }

        });

    });

});