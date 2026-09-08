document.addEventListener("DOMContentLoaded", function () {

    const searchInput = document.getElementById("searchPedido");
    const rows = document.querySelectorAll(".pedido-row");
    const deleteButtons = document.querySelectorAll(".btn-delete");


    /*
     * =========================================================
     * BUSCA DE PEDIDOS
     * =========================================================
     */

    if (searchInput) {

        searchInput.addEventListener("input", function () {

            const searchTerm = this.value
                .toLowerCase()
                .trim();

            rows.forEach(function (row) {

                const rowText = row.textContent
                    .toLowerCase();

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
                "Tem certeza que deseja excluir este pedido?"
            );

            if (!confirmed) {

                event.preventDefault();

            }

        });

    });


    /*
     * =========================================================
     * EVITAR DUPLO CLIQUE NA EXCLUSÃO
     * =========================================================
     */

    deleteButtons.forEach(function (button) {

        button.addEventListener("click", function () {

            if (button.dataset.confirmed === "true") {
                return;
            }

            /*
             * O clique só será confirmado pelo navegador
             * se o usuário aceitar a confirmação acima.
             */

            button.dataset.confirmed = "true";

        });

    });

});