document.addEventListener("DOMContentLoaded", function () {

    const addItemButton =
        document.getElementById("addItemButton");

    const itemsContainer =
        document.getElementById("itemsContainer");

    const emptyItems =
        document.getElementById("emptyItems");

    const displayTotal =
        document.getElementById("displayTotal");

    const valorTotalInput =
        document.getElementById("valorTotal");

    const produtos =
        window.vetmarkProdutos || [];


    /* =========================================
       VERIFICAR PRODUTOS RECEBIDOS
       ========================================= */

    console.log("Produtos recebidos:", produtos);


    /* =========================================
       ADICIONAR PRODUTO
       ========================================= */

    if (addItemButton) {

        addItemButton.addEventListener(
            "click",
            function () {

                const produtosAtivos =
                    produtos.filter(function (produto) {

                        return (
                            produto.ativo === true ||
                            produto.ativo === "true" ||
                            produto.ativo === 1 ||
                            produto.ativo === "1"
                        );

                    });


                if (produtosAtivos.length === 0) {

                    alert(
                        "Não existem produtos ativos cadastrados."
                    );

                    return;
                }


                if (emptyItems) {
                    emptyItems.style.display = "none";
                }


                const item =
                    document.createElement("div");

                item.classList.add("pedido-item");


                let options =
                    '<option value="">Selecione o produto</option>';


                produtosAtivos.forEach(
                    function (produto) {

                        options += `
                            <option
                                value="${produto.id}"
                                data-preco="${produto.preco}"
                                data-estoque="${produto.estoque}"
                            >
                                ${produto.nome}
                            </option>
                        `;

                    }
                );


                item.innerHTML = `

                    <div class="item-field">

                        <label>
                            Produto
                        </label>

                        <select
                            name="produtoIds"
                            class="item-produto"
                            required
                        >
                            ${options}
                        </select>

                    </div>


                    <div class="item-field">

                        <label>
                            Quantidade
                        </label>

                        <input
                            type="number"
                            name="quantidades"
                            class="item-quantidade"
                            min="1"
                            value="1"
                            required
                        >

                        <small class="estoque-info"></small>

                    </div>


                    <div class="item-field">

                        <label>
                            Preço unitário
                        </label>

                        <input
                            type="text"
                            class="item-valor"
                            value="R$ 0,00"
                            readonly
                        >

                    </div>


                    <button
                        type="button"
                        class="btn-remove-item"
                        title="Remover produto"
                    >
                        🗑️
                    </button>

                `;


                itemsContainer.appendChild(item);

                configurarProduto(item);

                atualizarTotal();

            }
        );
    }


    /* =========================================
       CONFIGURAR PRODUTO
       ========================================= */

    function configurarProduto(item) {

        const select =
            item.querySelector(".item-produto");

        const quantidadeInput =
            item.querySelector(".item-quantidade");

        const valorInput =
            item.querySelector(".item-valor");

        const estoqueInfo =
            item.querySelector(".estoque-info");


        if (
            !select ||
            !quantidadeInput ||
            !valorInput
        ) {
            return;
        }


        select.addEventListener(
            "change",
            function () {

                const option =
                    select.options[
                        select.selectedIndex
                        ];


                const preco =
                    parseFloat(
                        option.getAttribute(
                            "data-preco"
                        )
                    ) || 0;


                const estoque =
                    parseInt(
                        option.getAttribute(
                            "data-estoque"
                        )
                    ) || 0;


                valorInput.value =
                    formatarMoeda(preco);


                quantidadeInput.max =
                    estoque;


                if (estoqueInfo) {

                    estoqueInfo.textContent =
                        `Estoque disponível: ${estoque}`;

                }


                atualizarTotal();

            }
        );


        quantidadeInput.addEventListener(
            "input",
            function () {

                const option =
                    select.options[
                        select.selectedIndex
                        ];


                if (!option) {
                    return;
                }


                const estoque =
                    parseInt(
                        option.getAttribute(
                            "data-estoque"
                        )
                    ) || 0;


                const quantidade =
                    parseInt(
                        quantidadeInput.value
                    ) || 0;


                if (
                    estoque > 0 &&
                    quantidade > estoque
                ) {

                    quantidadeInput.value =
                        estoque;

                }


                atualizarTotal();

            }
        );

    }


    /* =========================================
       REMOVER PRODUTO
       ========================================= */

    if (itemsContainer) {

        itemsContainer.addEventListener(
            "click",
            function (event) {

                const removeButton =
                    event.target.closest(
                        ".btn-remove-item"
                    );


                if (!removeButton) {
                    return;
                }


                const item =
                    removeButton.closest(
                        ".pedido-item"
                    );


                if (item) {
                    item.remove();
                }


                verificarItens();

                atualizarTotal();

            }
        );
    }


    /* =========================================
       ATUALIZAR TOTAL
       ========================================= */

    function atualizarTotal() {

        const items =
            document.querySelectorAll(
                ".pedido-item"
            );


        let total = 0;


        items.forEach(function (item) {

            const select =
                item.querySelector(
                    ".item-produto"
                );


            const quantidadeInput =
                item.querySelector(
                    ".item-quantidade"
                );


            if (
                !select ||
                !quantidadeInput
            ) {
                return;
            }


            const option =
                select.options[
                    select.selectedIndex
                    ];


            if (!option) {
                return;
            }


            const preco =
                parseFloat(
                    option.getAttribute(
                        "data-preco"
                    )
                ) || 0;


            const quantidade =
                parseInt(
                    quantidadeInput.value
                ) || 0;


            total +=
                preco * quantidade;

        });


        if (displayTotal) {

            displayTotal.textContent =
                formatarMoeda(total);

        }


        if (valorTotalInput) {

            valorTotalInput.value =
                total.toFixed(2);

        }

    }


    /* =========================================
       VERIFICAR ITENS
       ========================================= */

    function verificarItens() {

        const items =
            document.querySelectorAll(
                ".pedido-item"
            );


        if (!emptyItems) {
            return;
        }


        emptyItems.style.display =
            items.length === 0
                ? "block"
                : "none";

    }


    /* =========================================
       FORMATAR MOEDA
       ========================================= */

    function formatarMoeda(valor) {

        return valor.toLocaleString(
            "pt-BR",
            {
                style: "currency",
                currency: "BRL"
            }
        );

    }


    verificarItens();

    atualizarTotal();

});