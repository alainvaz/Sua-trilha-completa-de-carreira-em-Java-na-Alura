package br.com.alain.screenmatch_frases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FraseController {

    @Autowired
    private FraseServico servico;

    @GetMapping("/series/frases")
    public FraseDto obterFraseAleatoria(){
        return servico.obterFraseAleatoria();
    }
}
