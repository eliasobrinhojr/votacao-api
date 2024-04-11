create schema if not exists db-coop;

create table if not exists db-coop.associado (
    id SERIAL primary key,
    cpf VARCHAR(11) not null unique,
    nome VARCHAR(200) not null,
    data_create TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

create table if not exists db-coop.pauta (
    id SERIAL primary key,
    titulo VARCHAR(100) not null,
    descricao VARCHAR(255) not null,
    resultado VARCHAR(3),
    data_create TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

create table if not exists db-coop.sessao (
    id SERIAL primary key,
    pauta_id INTEGER not null,
    duration INTEGER not null,
    data_create TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint fk_pauta foreign key(pauta_id) references db-coop.pauta(id)
);

create table if not exists db-coop.votos (
    id SERIAL primary key,
    sessao_id INTEGER not null,
    associado_id INTEGER not null,
    voto BOOLEAN NOT NULL,
    data_create TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint fk_sessao foreign key(sessao_id) references db-coop.sessao(id),
	constraint fk_associado foreign key(associado_id) references db-coop.associado(id)
);
