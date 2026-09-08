document.addEventListener("DOMContentLoaded", function () {

    const searchInput =
        document.getElementById("searchProduto");

    const rows =
        document.querySelectorAll(".produto-row");

    const deleteButtons =
        document.querySelectorAll(".btn-delete");


    /* =========================================
       BUSCA DE PRODUTOS
       ========================================= */

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


    /* =========================================
       CONFIRMAÇÃO DE EXCLUSÃO
       ========================================= */

    deleteButtons.forEach(function (button) {

        button.addEventListener("click", function (event) {

            const productName =
                this.getAttribute("data-name");

            const message = productName
                ? `Tem certeza que deseja excluir o produto "${productName}"?`
                : "Tem certeza que deseja excluir este produto?";

            const confirmed =
                confirm(message);

            if (!confirmed) {
                event.preventDefault();
            }

        });

    });

});