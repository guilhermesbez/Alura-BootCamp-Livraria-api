create table livro(
	id bigint not null auto_increment,
	titulo varchar(200) not null,
	data_lancamento date not null,
	numero_paginas int,
	primary key (id)
);

