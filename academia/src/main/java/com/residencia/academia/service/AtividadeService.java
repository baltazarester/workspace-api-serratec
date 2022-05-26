package com.residencia.academia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.dto.AtividadeDTO;
import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Atividade;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.AtividadeRepository;

@Service
public class AtividadeService {

	@Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private TurmaService turmaService;

    public List<Atividade> findAll() {
        return atividadeRepository.findAll();
    }

    public Atividade findById(Integer id) {
        return atividadeRepository.findById(id).isPresent() ? atividadeRepository.findById(id).get() : null;
    }

    public Atividade save(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public Atividade update(Atividade atividade) {
        return atividadeRepository.save(atividade);

    }

    public void delete(Integer id) {
        Atividade atividade = atividadeRepository.findById(id).get();
        atividadeRepository.delete(atividade);
    }


    public AtividadeDTO findAtividadeDTOById(Integer id) {
        return atividadeRepository.findById(id).isPresent() ? converterParaDTO(atividadeRepository.findById(id).get())
                : null;
    }

    public Atividade saveDTO(AtividadeDTO atividadeDTO) {
        Atividade atividade = converterParaAtividade(atividadeDTO);
        return atividadeRepository.save(atividade);
    }


    public AtividadeDTO converterParaDTO(Atividade atividade) {
        AtividadeDTO atividadeDTO = new AtividadeDTO();
        atividadeDTO.setId(atividade.getIdAtividade());
        atividadeDTO.setNome(atividade.getNome());
        List<TurmaDTO> listaTurmaDTO = new ArrayList<>();

        for (Turma turma : atividade.getTurmaList()) {
            TurmaDTO turmaDTO = turmaService.converterParaDTO(turma);
            listaTurmaDTO.add(turmaDTO);
        }

        atividadeDTO.setTurmaDTOList(listaTurmaDTO);

        return atividadeDTO;
    }

    public Atividade converterParaAtividade(AtividadeDTO atividadeDTO) {
        Atividade atividade = new Atividade();
        atividade.setIdAtividade(atividadeDTO.getId());
        atividade.setNome(atividadeDTO.getNome());

        return atividade;
    }
}
