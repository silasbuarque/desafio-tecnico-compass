create table TBG_PAUTA (
   id bigint not null auto_increment,
   title varchar(255) not null,
   description text not null,

    primary key(id)
) engine=InnoDB default charset=utf8;


create table TBG_VOTING_SESSION (
    id bigint not null auto_increment,
    pauta_id bigint unique not null,
    start timestamp not null default CURRENT_TIMESTAMP,
    end timestamp,

    primary key(id),
    constraint fk_voting_session_pauta foreign key (pauta_id) references TBG_PAUTA(id) on delete cascade
) engine=InnoDB default charset=utf8;

create table TBG_VOTING (
    id bigint not null auto_increment,
    associate_document varchar(14) not null,
    voting varchar(3) check (voting in ('YES', 'NO')) not null,
    pauta_id bigint not null,

    primary key(id),
    constraint fk_voting_pauta foreign key (pauta_id) references TBG_PAUTA(id) on delete cascade,
    constraint unq_document_pauta unique (associate_document, pauta_id)
) engine=InnoDB default charset=utf8;