document.addEventListener("DOMContentLoaded", function () {

    const searchInput =
        document.getElementById("searchVeterinario");

    const rows =
        document.querySelectorAll(".veterinario-row");

    const noSearchResults =
        document.getElementById("noSearchResults");

    const deleteButtons =
        document.querySelectorAll(".action-button.delete");


    /* ================================
       BUSCA
    ================================= */

    if (searchInput) {

        searchInput.addEventListener("input", function () {

            const searchTerm =
                this.value.toLowerCase().trim();

            let visibleRows = 0;

            rows.forEach(function (row) {

                const rowText =
                    row.textContent.toLowerCase();

                const matches =
                    rowText.includes(searchTerm);

                row.style.display =
                    matches ? "" : "none";

                if (matches) {
                    visibleRows++;
                }

            });

            if (noSearchResults) {

                if (visibleRows === 0 && searchTerm !== "") {

                    noSearchResults.style.display = "block";

                } else {

                    noSearchResults.style.display = "none";
                }
            }

        });
    }


    /* ================================
       EXCLUSÃO
    ================================= */

    deleteButtons.forEach(function (button) {

        button.addEventListener("click", function () {

            const id =
                this.getAttribute("data-id");

            const name =
                this.getAttribute("data-name");

            const message = name
                ? `Tem certeza que deseja excluir o veterinário "${name}"?`
                : "Tem certeza que deseja excluir este veterinário?";

            const confirmed =
                window.confirm(message);

            if (!confirmed) {
                return;
            }

            window.location.href =
                `/veterinario/excluir/${id}`;

        });

    });

});