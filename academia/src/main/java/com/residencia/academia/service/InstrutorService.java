package com.residencia.academia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.dto.InstrutorDTO;
import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.InstrutorRepository;

@Service
public class InstrutorService {
	@Autowired
	InstrutorRepository instrutorRepository;

	@Autowired
	TurmaService turmaService;

	public List<Instrutor> findAllInstrutor() {
		return instrutorRepository.findAll();

	}

	public Instrutor findInstrutorById(Integer id) {

		return instrutorRepository.findById(id).isPresent() ? instrutorRepository.findById(id).get() : null;

	}

	public InstrutorDTO findInstrutorDTOById(Integer id) {
		Instrutor instrutor = instrutorRepository.findById(id).isPresent() ? instrutorRepository.findById(id).get()
				: null;

		InstrutorDTO instrutorDTO = new InstrutorDTO();
		if (null != instrutor) {
			instrutorDTO = converterEntidadeParaDto(instrutor);
		}
		return instrutorDTO;

	}

	public Instrutor saveInstrutor(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}

	public Instrutor saveInstrutorDTO(InstrutorDTO instrutorDTO) {

		Instrutor instrutor = convertDTOToEntidade(instrutorDTO);
		return instrutorRepository.save(instrutor);
	}

	public Instrutor updateInstrutor(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}

	public void deleteInstrutor(Integer id) {
		Instrutor inst = instrutorRepository.findById(id).get();
		instrutorRepository.delete(inst);
	}

	public void deleteInstrutor(Instrutor instrutor) {
		instrutorRepository.delete(instrutor);
	}

	private Instrutor convertDTOToEntidade(InstrutorDTO instrutorDTO) {
		Instrutor instrutor = new Instrutor();

		instrutor.setIdInstrutor(instrutorDTO.getIdInstrutor());
		instrutor.setRg(instrutorDTO.getIdInstrutor());
		instrutor.setNomeInstrutor(instrutorDTO.getNomeInstrutor());
		instrutor.setDataNascimento(instrutorDTO.getDataNascimento());
		instrutor.setTitulacaoInstrutor(instrutorDTO.getTitulacaoInstrutor());

		List<Turma> listTurma = new ArrayList<>();
		if (null != instrutorDTO.getTurmaDTOlist()) {
			for (TurmaDTO turmaDTO : instrutorDTO.getTurmaDTOlist()) {
			
				if (null != turmaDTO.getIdTurma()) {
					Turma turma = turmaService.findTurmaById(turmaDTO.getIdTurma());
					if (null != turma) {
						// turma.setDataFim(turmaDTO.getDataFim());
						// turma.setDataInicio(turmaDTO.getDataInicio());
						// turma.setDuracaoTurma(turmaDTO.getDuracaoTurma());
						// turma.setHorarioTurma(turmaDTO.getHorarioTurma());
						// turma.setIdTurma(turmaDTO.getIdTurma());

						// if(null != instrutorDTO.getIdInstrutor()) {
						// Instrutor instrutorBD = findInstrutorById(instrutorDTO.getIdInstrutor());
						// turma.setInstrutor(instrutorBD);
						// }

						listTurma.add(turma);
					}
				}

			}
			instrutor.setTurmaList(listTurma);
		}

		return instrutor;
	}

	private InstrutorDTO converterEntidadeParaDto(Instrutor instrutor) {
		InstrutorDTO instrutorDTO = new InstrutorDTO();
		instrutorDTO.setDataNascimento(instrutor.getDataNascimento());
		instrutorDTO.setIdInstrutor(instrutor.getIdInstrutor());
		instrutorDTO.setNomeInstrutor(instrutor.getNomeInstrutor());
		instrutorDTO.setIdInstrutor(instrutor.getRg());
		instrutorDTO.setTitulacaoInstrutor(instrutor.getTitulacaoInstrutor());

		List<TurmaDTO> listTurmaDTO = new ArrayList<>();
		
		if (null != instrutor.getTurmaList()) {
			for (Turma turma : instrutor.getTurmaList()) {
				TurmaDTO turmaDTO = new TurmaDTO();
				turmaDTO.setDataFim(turma.getDataFim());
				turmaDTO.setDataInicio(turma.getDataInicio());
				turmaDTO.setDuracaoTurma(turma.getDuracaoTurma());
				turmaDTO.setHorarioTurma(turma.getHorarioTurma());
				turmaDTO.setIdTurma(turma.getIdTurma());

				listTurmaDTO.add(turmaDTO);
			}
			instrutorDTO.setTurmaDTOlist(listTurmaDTO);
		}

		return instrutorDTO;
	}

}
