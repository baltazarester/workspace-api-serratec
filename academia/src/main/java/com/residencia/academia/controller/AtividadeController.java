package com.residencia.academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.entity.Atividade;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.service.AtividadeService;
import com.residencia.academia.service.TurmaService;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {
	@Autowired
	private AtividadeService atividadeService;

	@GetMapping
	public ResponseEntity<List<Atividade>> findAllTurma() {
		return new ResponseEntity<>(atividadeService.findAll(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Atividade> findTurmaById(@PathVariable Integer id) {
		Atividade atividade = atividadeService.findById(id);
		if (null == atividade)
			throw new NoSuchElementFoundException("Não foi encontrada Turma com o id " + id);
		else
			return new ResponseEntity<>(atividadeService.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Atividade> save(@RequestBody Atividade atividade) {
		return new ResponseEntity<>(atividadeService.save(atividade), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Atividade> update(@RequestBody Atividade atividade) {
		return new ResponseEntity<>(atividadeService.update(atividade), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {

		atividadeService.delete(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}