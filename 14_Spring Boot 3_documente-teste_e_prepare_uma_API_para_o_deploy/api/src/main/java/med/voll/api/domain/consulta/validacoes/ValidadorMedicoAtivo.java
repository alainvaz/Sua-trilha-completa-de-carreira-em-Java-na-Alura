package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados){

        // a escolha do médico é opcional
        if(dados.idMedico() == null){
            return;
        }

        var medicoAtivo = repository.findAtivoById(dados.idMedico());

        if(!medicoAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído");
        }

    }

}
