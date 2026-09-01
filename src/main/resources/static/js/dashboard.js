document.addEventListener(
    "DOMContentLoaded",
    () => {

        console.log(
            "Dashboard carregado com sucesso!"
        );


        /*
         * ITEM ATIVO DA SIDEBAR
         */
        const navItems =
            document.querySelectorAll(
                ".nav-item"
            );


        const currentPath =
            window.location.pathname;


        navItems.forEach(
            (item) => {

                const href =
                    item.getAttribute(
                        "href"
                    );


                if (
                    href === currentPath
                ) {

                    item.classList.add(
                        "active"
                    );

                }

            }
        );


        /*
         * MENSAGEM DE BOAS-VINDAS
         *
         * Podemos futuramente utilizar
         * dados reais do usuário autenticado.
         */
        const welcomeMessage =
            document.querySelector(
                ".welcome-message"
            );


        if (welcomeMessage) {

            console.log(
                "Área de boas-vindas disponível."
            );

        }

    }

);