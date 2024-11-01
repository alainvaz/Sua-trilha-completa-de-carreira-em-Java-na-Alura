function alterarStatus(id) {
  let gameClicado = document.getElementById(`game-${id}`);
  let imagem = gameClicado.querySelector(".dashboard__item__img");
  let botao = gameClicado.querySelector(".dashboard__item__button");
  let nomeJogo = gameClicado.querySelector(".dashboard__item__name");

  if (imagem.classList.contains("dashboard__item__img--rented")) {
    if (
      confirm(
        `Você tem certeza que deseja devolver o jogo ${nomeJogo.textContent}?`
      )
    ) {
      imagem.classList.remove("dashboard__item__img--rented");
      botao.textContent = "Alugar";
      botao.classList.remove("dashboard__item__button--return");
    }
  } else {
    imagem.classList.add("dashboard__item__img--rented");
    botao.textContent = "Devolver";
    botao.classList.add("dashboard__item__button--return");
  }

  jogosAlugados();
}

jogosAlugados();

function jogosAlugados() {
  let totalAlugados = 0;
  let botoes = document.getElementsByClassName("dashboard__item__button");
  console.log(botoes.length);

  valorProcurado = "Devolver";
  for (let i = 0; i < botoes.length; i++) {
    if (botoes[i].textContent == valorProcurado) {
      totalAlugados++;
    }
  }
  console.log(`Total de jogos alugados: ${totalAlugados}`);

  // também poderia ser utilizado o comando abaixo:
  jogosAlugadosVar = document.querySelectorAll(
    ".dashboard__item__img--rented"
  ).length;
  console.log(`Total de jogos alugados (outra lógica): ${jogosAlugadosVar}`);
}
