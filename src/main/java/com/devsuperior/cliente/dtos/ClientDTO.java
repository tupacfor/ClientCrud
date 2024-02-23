package com.devsuperior.cliente.dtos;

import java.time.LocalDate;

import com.devsuperior.cliente.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class ClientDTO {
	private Long id;
	@NotBlank(message = "Required field")
	@Size(min = 3, max = 80, message = "Name has to have between 3 and 80 characters")
	private String name;
	@NotBlank(message = "Required field")
	@Size(min = 11, max = 15, message = "Cpf has to have 11 characters with no punctuation and 15 characters with punctuation")
	private String cpf;
	@PositiveOrZero(message = "Income must be 0 or more")
	private Double income;
	@PastOrPresent(message = "The birth date can not be in the future")
	private LocalDate birthDate;
	@PositiveOrZero(message = "Must be 0 or more")
	private Integer children;
	
	
	public ClientDTO() {
		super();
	}

	public ClientDTO(Client client) {
		id = client.getId();
		name = client.getName();
		cpf = client.getCpf();
		income = client.getIncome();
		birthDate = client.getBirthDate();
		children = client.getChildren();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
}
