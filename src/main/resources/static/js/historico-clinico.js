document.addEventListener("DOMContentLoaded", function () {

    const searchInput =
        document.getElementById("searchHistorico");

    const rows =
        document.querySelectorAll(".historico-row");

    const noSearchResults =
        document.getElementById("noSearchResults");

    const viewButtons =
        document.querySelectorAll(".action-button.view");

    const deleteButtons =
        document.querySelectorAll(".action-button.delete");


    /*
     * PESQUISA
     */

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

                if (
                    visibleRows === 0 &&
                    searchTerm !== ""
                ) {

                    noSearchResults.style.display =
                        "table-row";

                } else {

                    noSearchResults.style.display =
                        "none";
                }

            }

        });

    }


    /*
     * MODAL
     */

    const modal =
        document.getElementById("historicoModal");

    const closeModal =
        document.getElementById("closeModal");


    function abrirModal(button) {

        if (!modal) {
            return;
        }

        const pet =
            button.getAttribute("data-pet");

        const diagnostico =
            button.getAttribute("data-diagnostico");

        const tratamento =
            button.getAttribute("data-tratamento");

        const medicacao =
            button.getAttribute("data-medicacao");

        const observacoes =
            button.getAttribute("data-observacoes");


        document.getElementById("modalPet").textContent =
            pet || "Pet não informado";

        document.getElementById("modalDiagnostico").textContent =
            diagnostico || "Não informado.";

        document.getElementById("modalTratamento").textContent =
            tratamento || "Não informado.";

        document.getElementById("modalMedicacao").textContent =
            medicacao || "Não informado.";

        document.getElementById("modalObservacoes").textContent =
            observacoes || "Não informado.";


        modal.style.display = "flex";
    }


    function fecharModal() {

        if (modal) {
            modal.style.display = "none";
        }

    }


    viewButtons.forEach(function (button) {

        button.addEventListener("click", function () {

            abrirModal(this);

        });

    });


    if (closeModal) {

        closeModal.addEventListener(
            "click",
            fecharModal
        );

    }


    if (modal) {

        modal.addEventListener(
            "click",
            function (event) {

                if (event.target === modal) {
                    fecharModal();
                }

            }
        );

    }


    document.addEventListener(
        "keydown",
        function (event) {

            if (event.key === "Escape") {
                fecharModal();
            }

        }
    );


    /*
     * EXCLUSÃO
     */

    deleteButtons.forEach(function (button) {

        button.addEventListener(
            "click",
            function () {

                const id =
                    this.getAttribute("data-id");

                const pet =
                    this.getAttribute("data-pet");


                const message = pet
                    ? `Tem certeza que deseja excluir o histórico clínico do pet "${pet}"?`
                    : "Tem certeza que deseja excluir este histórico clínico?";


                const confirmed =
                    window.confirm(message);


                if (!confirmed) {
                    return;
                }


                window.location.href =
                    `/historico-clinico/excluir/${id}`;

            }
        );

    });

});