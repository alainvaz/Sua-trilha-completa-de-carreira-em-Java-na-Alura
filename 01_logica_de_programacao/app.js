alert("Boas vindas ao jogo do número secreto");

let valorMaximo = 20;
let numeroSecreto = parseInt(Math.random() * valorMaximo + 1);
let chute;
let tentativas = 1;

// enquanto chute não for igual ao número secreto
while (chute != numeroSecreto) {
  chute = prompt(`Escolha um número entre 1 e ${valorMaximo}`);

  if (numeroSecreto == chute) {
    console.log("Acertou");
    break;
  } else {
    console.log("Errou");
    if (chute > numeroSecreto) {
      alert(`O número secreto é menor que ${chute}`);
    } else {
      alert(`O número secreto é maior que ${chute}`);
    }
    tentativas++;
  }
}

let palavraTentativa = tentativas > 1 ? "tentativas" : "tentativa";

alert(
  `Isso aí, você descobriu o número secreto: ${numeroSecreto} com ${tentativas} ${palavraTentativa}`
);

/*
if (tentativas > 1) {
  alert(
    `Isso aí, você descobriu o número secreto: ${numeroSecreto} com ${tentativas} tentativas`
  );
} else {
  alert(
    `Isso aí, você descobriu o número secreto: ${numeroSecreto} com ${tentativas} tentativa`
  );
}
*/
