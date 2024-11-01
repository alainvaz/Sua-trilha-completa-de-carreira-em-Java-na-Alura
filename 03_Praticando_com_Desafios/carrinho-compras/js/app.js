let total = 0;
function adicionar() {
  //recuperar valores: nome do produto, quantidade e valor

  let produto = document.getElementById("produto").value;
  let quantidade = document.getElementById("quantidade").value;

  let nomeProduto = produto.split("-")[0];
  let valorUnitario = produto.split("R$")[1];

  // Verificar se o produto selecionado é válido
  if (!produto || produto.trim() === "") {
    alert("Selecione um produto válido.");
    return;
  }

  // Verificar se a quantidade inserida é válida
  if (isNaN(quantidade) || quantidade <= 0) {
    alert("Insira uma quantidade válida.");
    return;
  }
  // calcular o preço, o nosso subtotal
  let preco = quantidade * valorUnitario;

  // adicionar no carrinho

  let carrinho = document.getElementById("lista-produtos");
  carrinho.innerHTML += `
          <section class="carrinho__produtos__produto">
          <span class="texto-azul">${quantidade}x</span> ${nomeProduto} <span class="texto-azul">R$${preco}</span>
        </section>
  `;

  // atualizar o total da compra
  total = total + preco;
  let campoTotal = document.getElementById("valor-total");
  campoTotal.textContent = `R$ ${total}`;
  document.get.ElementById("quantidade").value = 0;
}

function limpar() {
  total = 0;
  document.getElementById("lista-produtos").innerHTML = "";
  document.getElementById("valor-total").textContent = "R$ 0";
}
