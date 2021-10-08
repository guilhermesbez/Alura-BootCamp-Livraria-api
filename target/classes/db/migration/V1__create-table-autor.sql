create table autor(
	id bigint not null auto_increment,
	nome varchar(144) not null,
	email varchar(144) not null,
	data_nascimento date not null,
	mini_curriculo varchar(240),
	primary key (id)
);

