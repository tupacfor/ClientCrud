package com.devsuperior.cliente.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.cliente.dtos.ClientDTO;
import com.devsuperior.cliente.entities.Client;
import com.devsuperior.cliente.exceptions.ExceptionResourceNotFound;
import com.devsuperior.cliente.repositories.ClientRepository;

import jakarta.persistence.EntityNotFoundException;
@Service
public class ClientService {
	@Autowired
	ClientRepository repository;
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client client = repository.findById(id).orElseThrow(() -> new ExceptionResourceNotFound("Resource not found"));
		return new ClientDTO(client);
	}
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> clientList = repository.findAll(pageable);
		return clientList.map(x -> new ClientDTO(x));
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client client = new Client();
		client = dtoToClient(dto, client);
		client = repository.save(client);
		return new ClientDTO(client);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
		Client client = repository.getReferenceById(id);
		client = dtoToClient(dto, client);
		repository.save(client);
		return new ClientDTO(client);
		}
		catch (EntityNotFoundException e) {
			throw new ExceptionResourceNotFound("Client do not exists");
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ExceptionResourceNotFound("There is no client with this id to delete");
		}
		repository.deleteById(id);
		
	}
	
	public Client dtoToClient(ClientDTO dto, Client client) {
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
		return client;
	}
	
	
}
