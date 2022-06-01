package com.mari.marilog.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mari.marilog.domain.model.Client;
import com.mari.marilog.domain.model.Delivery;
import com.mari.marilog.domain.model.StatusDelivery;
import com.mari.marilog.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CreateDeliveryService {
	private DeliveryRepository deliveryRepository;
	private CatalogClientService catalogClientService;
	
	public Delivery create(Delivery delivery) {
		Client client = catalogClientService.getClient(delivery.getClient().getId());
		
		delivery.setClient(client);
		delivery.setStatus(StatusDelivery.PENDENTE);
		delivery.setDateSend(LocalDateTime.now());
		
		return deliveryRepository.save(delivery);
	}
}
