package com.residencia.academia.dto;

import java.util.List;

public class AtividadeDTO {
	
	private Integer id;
    private String nome;
    private List<TurmaDTO> turmaDTOList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<TurmaDTO> getTurmaDTOList() {
        return turmaDTOList;
    }

    public void setTurmaDTOList(List<TurmaDTO> turmaDTOList) {
        this.turmaDTOList = turmaDTOList;
    }
}
