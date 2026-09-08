document.addEventListener("DOMContentLoaded", function () {

    const searchInput = document.getElementById("searchAgendamento");
    const rows = document.querySelectorAll(".agendamento-row");

    /*
     * Busca de agendamentos
     */
    if (searchInput) {

        searchInput.addEventListener("input", function () {

            const searchTerm = this.value
                .toLowerCase()
                .trim();

            rows.forEach(function (row) {

                const text = row.textContent
                    .toLowerCase();

                if (text.includes(searchTerm)) {
                    row.style.display = "";
                } else {
                    row.style.display = "none";
                }

            });

        });

    }


    /*
     * Confirmação de exclusão
     */
    const deleteButtons = document.querySelectorAll(
        ".btn-delete"
    );

    deleteButtons.forEach(function (button) {

        button.addEventListener("click", function (event) {

            const confirmed = confirm(
                "Tem certeza que deseja excluir este agendamento?"
            );

            if (!confirmed) {
                event.preventDefault();
            }

        });

    });

});