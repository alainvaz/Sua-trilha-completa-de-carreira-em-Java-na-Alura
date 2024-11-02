function comprar() {
  let tipo = document.getElementById("tipo-ingresso").value;
  let quantidade = parseInt(document.getElementById("qtd").value);

  if (quantidade <= 0 || isNaN(quantidade)) {
    alert("Informe pelo menos 1 ingresso para compra");
  } else {
    comprarIngresso(quantidade, tipo);
  }
}

function comprarIngresso(quantidade, tipo) {
  let qtdSetor = parseInt(document.getElementById(`qtd-${tipo}`).textContent);

  console.log(tipo);
  if (quantidade > qtdSetor) {
    alert(`Quantidade indisponível para o tipo ${tipo}`);
  } else {
    qtdSetor = qtdSetor - quantidade;
    document.getElementById(`qtd-${tipo}`).textContent = qtdSetor;
    alert("Compra realizada com sucesso!");
  }
}
