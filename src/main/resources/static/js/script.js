document.addEventListener(
    "DOMContentLoaded",
    () => {

        const senhaInput =
            document.getElementById(
                "senha"
            );

        const togglePassword =
            document.getElementById(
                "togglePassword"
            );


        togglePassword.addEventListener(
            "click",
            () => {

                const isPassword =
                    senhaInput.type === "password";


                senhaInput.type =
                    isPassword
                        ? "text"
                        : "password";


                togglePassword.textContent =
                    isPassword
                        ? "🙈"
                        : "👁";

            }
        );

    }
);