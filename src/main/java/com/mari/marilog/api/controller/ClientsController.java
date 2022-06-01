package com.mari.marilog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mari.marilog.domain.model.Client;
import com.mari.marilog.domain.repository.ClientRepository;
import com.mari.marilog.domain.service.CatalogClientService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientsController {

	// @Autowired //	Essa notação cria uma "instancia" da interface, dificulta testes :/
	// private ClientRepository clientRepository;
	private ClientRepository clientRepository;
	private CatalogClientService catalogClientService;
	//	public ClientsController(ClientRepository clientRepository) {
	//		super();
	//		this.clientRepository = clientRepository;
	//	}

	@GetMapping("")
	public List<Client> list() {
		return clientRepository.findAll();
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<Client> getById(@PathVariable Long clientId) {
		return clientRepository.findById(clientId)
//				.map(client -> ResponseEntity.ok(client))
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());	
		
//		Optional<Client> client = clientRepository.findById(clientId);
//		if (client.isPresent()) {
//			return ResponseEntity.ok(client.get());
//		}
//		
//		return ResponseEntity.notFound().build();	
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Client insert(@Valid @RequestBody Client client) {
		return catalogClientService.save(client);
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<Client> update(@Valid @PathVariable Long clientId, @RequestBody Client client) {
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		client.setId(clientId);
		client = catalogClientService.save(client);
		
		return ResponseEntity.ok(client);
	}
	
	@DeleteMapping	("/{clientId}")
	public ResponseEntity<Void> delete(@PathVariable Long clientId) {
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		catalogClientService.delete(clientId);
		
		return ResponseEntity.noContent().build();
	}
}
