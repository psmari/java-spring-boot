create table delivery (
	id bigint not null auto_increment,
	client_id bigint not null,
    tax decimal(10,2) not null,
    status varchar(20) not null,
	date_send datetime not null,
	date_finished datetime,
	
	receiver_name varchar(60) not null,
	receiver_locale varchar(255) not null,
	receiver_number varchar(30) not null,
	receiver_complement varchar(60) not null,
	receiver_district varchar(30) not null,
    
    primary key (id)
);

alter table delivery add constraint fk_delivery_client foreign key (client_id) references client (id);