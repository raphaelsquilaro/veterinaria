document.addEventListener("DOMContentLoaded", function () {

    const form =
        document.getElementById("historicoForm");

    const saveButton =
        document.getElementById("saveButton");

    const petSelect =
        document.getElementById("petId");

    const agendamentoSelect =
        document.getElementById("agendamentoId");


    /*
     * Ao selecionar um pet, tentamos
     * facilitar a escolha do agendamento.
     */

    if (petSelect && agendamentoSelect) {

        petSelect.addEventListener("change", function () {

            const petId = this.value;

            if (!petId) {
                return;
            }

            Array.from(
                agendamentoSelect.options
            ).forEach(function (option) {

                if (!option.value) {
                    return;
                }

                const texto =
                    option.textContent.toLowerCase();

                /*
                 * O filtro visual completo dos agendamentos
                 * poderá ser melhorado depois com dados específicos
                 * do pet.
                 */

                option.style.display = "";
            });

        });
    }


    /*
     * Evita duplo envio do formulário.
     */

    if (form) {

        form.addEventListener("submit", function () {

            if (saveButton) {

                saveButton.disabled = true;

                saveButton.innerHTML =
                    "<span>✓</span> Salvando...";

            }

        });

    }

});