package br.com.springboot.sampleproject.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="sq_usuario", sequenceName="sq_usuario", allocationSize=1, initialValue=1)
public class Usuario implements Serializable{

	private static final long serialVersionUID = -7132588059555911928L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sq_usuario")
	private Long id;
	
	private String nome;
	
	private Date dataNascimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
