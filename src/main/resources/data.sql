create database nativa;

create table usuario
(
    id  varchar  not null
        constraint pk_usuario
            primary key,
    name varchar not null,
    email varchar unique  not null ,
    password varchar not null
);

create  table  marca
(
  id varchar not null constraint pk_marca primary key ,
  name varchar not null unique
);

create table  patrimonio
(
    id varchar not null constraint pk_patrimonio primary key ,
    marcaid varchar constraint fk_marca references marca,
    description varchar,
    ntombo integer
)