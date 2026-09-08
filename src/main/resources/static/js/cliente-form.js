document.addEventListener("DOMContentLoaded", () => {

    const form = document.getElementById("clienteForm");

    const cpfInput = document.getElementById("cpf");
    const telefoneInput = document.getElementById("telefone");

    /*
     * Máscara de CPF
     */
    if (cpfInput) {

        cpfInput.addEventListener("input", () => {

            let value = cpfInput.value
                .replace(/\D/g, "")
                .slice(0, 11);

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

            cpfInput.value = value;

        });

    }

    /*
     * Máscara de telefone
     */
    if (telefoneInput) {

        telefoneInput.addEventListener("input", () => {

            let value = telefoneInput.value
                .replace(/\D/g, "")
                .slice(0, 11);

            if (value.length > 10) {

                value = value.replace(
                    /(\d{2})(\d{5})(\d{1,4})/,
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

            }

            telefoneInput.value = value;

        });

    }

    /*
     * Evita envio duplicado
     */
    if (form) {

        form.addEventListener("submit", () => {

            const saveButton =
                document.getElementById("saveButton");

            if (saveButton) {

                saveButton.disabled = true;

                saveButton.innerHTML =
                    "<span>✓</span> Salvando...";

            }

        });

    }

});