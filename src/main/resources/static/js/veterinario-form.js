document.addEventListener("DOMContentLoaded", function () {

    const form = document.getElementById("veterinarioForm");
    const cpfInput = document.getElementById("cpf");
    const telefoneInput = document.getElementById("telefone");
    const crmvInput = document.getElementById("crmv");
    const ativoInput = document.getElementById("ativo");
    const statusText = document.getElementById("statusText");
    const saveButton = document.getElementById("saveButton");

    /* ================================
       MÁSCARA CPF
    ================================= */

    if (cpfInput) {

        cpfInput.addEventListener("input", function () {

            let value = this.value.replace(/\D/g, "");

            value = value.substring(0, 11);

            if (value.length > 9) {

                value = value.replace(
                    /(\d{3})(\d{3})(\d{3})(\d{1,2})/,
                    "$1.$2.$3-$4"
                );

            } else if (value.length > 6) {

                value = value.replace(
                    /(\d{3})(\d{3})(\d{1,3})/,
                    "$1.$2.$3"
                );

            } else if (value.length > 3) {

                value = value.replace(
                    /(\d{3})(\d{1,3})/,
                    "$1.$2"
                );
            }

            this.value = value;
        });
    }

    /* ================================
       MÁSCARA TELEFONE
    ================================= */

    if (telefoneInput) {

        telefoneInput.addEventListener("input", function () {

            let value = this.value.replace(/\D/g, "");

            value = value.substring(0, 11);

            if (value.length > 10) {

                value = value.replace(
                    /(\d{2})(\d{5})(\d{4})/,
                    "($1) $2-$3"
                );

            } else if (value.length > 6) {

                value = value.replace(
                    /(\d{2})(\d{4})(\d{1,4})/,
                    "($1) $2-$3"
                );

            } else if (value.length > 2) {

                value = value.replace(
                    /(\d{2})(\d{1,5})/,
                    "($1) $2"
                );

            } else if (value.length > 0) {

                value = value.replace(
                    /(\d{1,2})/,
                    "($1"
                );
            }

            this.value = value;
        });
    }

    /* ================================
       CRMV
    ================================= */

    if (crmvInput) {

        crmvInput.addEventListener("input", function () {

            this.value = this.value
                .toUpperCase()
                .substring(0, 20);
        });
    }

    /* ================================
       STATUS DO VETERINÁRIO
    ================================= */

    function atualizarStatus() {

        if (!ativoInput || !statusText) {
            return;
        }

        if (ativoInput.checked) {

            statusText.textContent = "Veterinário ativo";

        } else {

            statusText.textContent = "Veterinário inativo";
        }
    }

    if (ativoInput) {
        ativoInput.addEventListener("change", atualizarStatus);
        atualizarStatus();
    }

    /* ================================
       ENVIO DO FORMULÁRIO
    ================================= */

    if (form) {

        form.addEventListener("submit", function () {

            if (saveButton) {

                saveButton.disabled = true;

                saveButton.innerHTML = `
<span>⏳</span>
Salvando...
`;
            }
        });
    }

});