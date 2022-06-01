package com.mari.marilog.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Receiver {
	
	@Column(name = "receiver_name")
	private String name;
	
	@Column(name = "receiver_locale")
	private String locale;
	
	@Column(name = "receiver_number")
	private String number;
	
	@Column(name = "receiver_complement")
	private String complement;
	
	@Column(name = "receiver_district")
	private String district;
}
