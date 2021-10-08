alter table livro add column autor_id bigint not null;
alter table livro add foreign key (autor_id) references autor(id);