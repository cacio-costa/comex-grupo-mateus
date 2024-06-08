create table cliente (
    id bigint not null auto_increment,
    cpf varchar(14),
    nome varchar(255),
    email varchar(255),
    telefone varchar(20),
    logradouro varchar(100),
    bairro varchar(100),
    cidade varchar(50),
    uf char(2),
    cep char(9),

    primary key(id)
);

create table categoria (
    id bigint not null auto_increment,
    nome varchar(100),

    primary key(id)
);

create table produto (
    id bigint not null auto_increment,
    nome varchar(100),
    preco decimal(10,2),

    primary key (id)
);

create table categoria_produto (
    id bigint not null auto_increment,
    categoria_id bigint not null,
    produto_id bigint not null,

    primary key (id),
    foreign key (categoria_id) references categoria (id),
    foreign key (produto_id) references produto (id)
)