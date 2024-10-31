let listaDeNumerosSorteados = [];
const qtdInicial = 10;

let numeroSecreto = gerarNumeroAleatorio(qtdInicial);
let tentativas = 1;

function exibirTextoNaTela(tag, texto) {
  let campo = document.querySelector(tag);
  campo.innerHTML = texto;
  responsiveVoice.speak(texto, "Brazilian Portuguese Male", { rate: 1.2 });
}

mensagemInicial();

function verificarChute() {
  let chute = document.querySelector("input").value;
  // console.log("O botão foi clicado");
  // console.log(chute == numeroSecreto);

  if (chute == numeroSecreto) {
    let palavra = tentativas > 1 ? "tentativas" : "tentativa";
    exibirTextoNaTela("h1", "Acertou!!!");
    exibirTextoNaTela(
      "p",
      `Você descobriu o número secreto com ${tentativas} ${palavra}.`
    );
    document.getElementById("reiniciar").removeAttribute("disabled");
  } else {
    if (chute > numeroSecreto) {
      exibirTextoNaTela("p", "O número secreto é menor.");
    } else {
      exibirTextoNaTela("p", "O número secreto é maior.");
    }
    tentativas++;
    limparCampo();
  }
}

function gerarNumeroAleatorio(qtd) {
  let numeroEscolhido = parseInt(Math.random() * qtd + 1);
  let quantidadeDeElementosNaLista = listaDeNumerosSorteados.length;

  if (quantidadeDeElementosNaLista == qtd) {
    listaDeNumerosSorteados = [];
  }

  if (listaDeNumerosSorteados.includes(numeroEscolhido)) {
    return gerarNumeroAleatorio(qtd);
  } else {
    listaDeNumerosSorteados.push(numeroEscolhido);
    console.log(listaDeNumerosSorteados);
    return numeroEscolhido;
  }
}

function limparCampo() {
  chute = document.querySelector("input");
  chute.value = "";
}

function reiniciarJogo() {
  numeroSecreto = gerarNumeroAleatorio(qtdInicial);
  limparCampo();
  tentativas = 1;
  mensagemInicial();
}

function mensagemInicial() {
  exibirTextoNaTela("h1", "Jogo do número secreto");
  exibirTextoNaTela("p", `Escolha um número entre 1 e ${qtdInicial}`);
  document.getElementById("reiniciar").setAttribute("disabled", true);
}
