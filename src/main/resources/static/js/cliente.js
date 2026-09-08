document.addEventListener("DOMContentLoaded", () => {

    const searchInput = document.getElementById("searchCliente");
    const rows = document.querySelectorAll(".cliente-row");
    const noSearchResults = document.getElementById("noSearchResults");

    /*
     * Busca de clientes
     */
    if (searchInput) {

        searchInput.addEventListener("input", () => {

            const search = searchInput.value
                .toLowerCase()
                .trim();

            let visibleRows = 0;

            rows.forEach((row) => {

                const text = row.textContent
                    .toLowerCase();

                const matches = text.includes(search);

                row.style.display = matches ? "" : "none";

                if (matches) {
                    visibleRows++;
                }

            });

            if (noSearchResults) {

                noSearchResults.style.display =
                    visibleRows === 0 ? "block" : "none";

            }

        });

    }

    /*
     * Exclusão de clientes
     */
    const deleteButtons =
        document.querySelectorAll(".action-button.delete");

    deleteButtons.forEach((button) => {

        button.addEventListener("click", () => {

            const id = button.dataset.id;
            const name = button.dataset.name;

            const confirmed = confirm(
                `Deseja realmente excluir o cliente "${name}"?`
            );

            if (!confirmed) {
                return;
            }

            window.location.href =
                `/cliente/excluir/${id}`;

        });

    });

});