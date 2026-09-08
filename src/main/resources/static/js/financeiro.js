document.addEventListener("DOMContentLoaded", function () {

    const searchInput =
        document.getElementById("searchFinanceiro");

    const rows =
        document.querySelectorAll(".financeiro-row");

    const deleteButtons =
        document.querySelectorAll(".btn-delete");


    // Busca

    if (searchInput) {

        searchInput.addEventListener("input", function () {

            const searchTerm =
                this.value.toLowerCase().trim();

            rows.forEach(function (row) {

                const rowText =
                    row.textContent.toLowerCase();

                row.style.display =
                    rowText.includes(searchTerm)
                        ? ""
                        : "none";

            });

        });

    }


    // Confirmação de exclusão

    deleteButtons.forEach(function (button) {

        button.addEventListener("click", function (event) {

            const name =
                this.getAttribute("data-name");

            const message = name
                ? `Tem certeza que deseja excluir a movimentação "${name}"?`
                : "Tem certeza que deseja excluir esta movimentação?";

            if (!confirm(message)) {
                event.preventDefault();
            }

        });

    });

});