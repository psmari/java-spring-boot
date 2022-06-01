package com.mari.marilog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mari.marilog.domain.exception.BusinessException;
import com.mari.marilog.domain.model.Client;
import com.mari.marilog.domain.repository.ClientRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogClientService {
	private ClientRepository clientRepository;
	
	public Client getClient (Long id) {
		return clientRepository.findById(id).orElseThrow(() -> new BusinessException("Cliente não encontrado"));
	}
	
	@Transactional // tudo que estiver sendo feito aqui só vai funcionar se der certo, ou seja, ou tudo ou nada. 
	public Client save(Client client) {
		boolean emailExists = clientRepository.findByEmail(client.getEmail()).stream().anyMatch(clientExists -> !clientExists.equals(client));
		
		if(emailExists) {
			throw new BusinessException("Já existe um e-mail cadastrado com esse e-mail.");
		}
		
		return clientRepository.save(client);
	}
	
	@Transactional 
	public void delete(Long clientId) {
		clientRepository.deleteById(clientId);
	}
	
}
