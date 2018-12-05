DROP DATABASE bdlojavirtual;
CREATE DATABASE bdlojavirtual;

DROP TABLE ped_item;
DROP TABLE ped_cab;
DROP TABLE forma_pgto;
DROP TABLE fone;
DROP TABLE pessoa;
DROP TABLE produto;

----------------------



CREATE TABLE produto
(
    pro_id serial,
    pro_nome varchar(60),
    pro_preco numeric(10,2),
    situacao varchar(1),
    CONSTRAINT produto_pkey PRIMARY KEY (pro_id)
);

CREATE TABLE pessoa
(
    pes_id serial,
    pes_nome varchar(60),
    pes_cpf varchar(14),
    pes_rg varchar(20),
    pes_data_nasc date,
    pes_rua varchar(60),
    pes_bairro varchar(30),
    pes_cidade varchar(30),
    pes_uf character(2),
    pes_cep integer,
    pes_email varchar(40),
    pes_senha varchar(32),
    pes_tipo varchar(30),
    CONSTRAINT pessoa_pkey PRIMARY KEY (pes_id)
);

CREATE TABLE fone
(
    fon_id serial,
    pes_id integer,
    fon_numero varchar(20),
    fon_descricao varchar(30),
    CONSTRAINT fone_pkey PRIMARY KEY (fon_id),
    CONSTRAINT rel_pessoa_fone FOREIGN KEY (pes_id)
        REFERENCES pessoa (pes_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE forma_pgto
(
    fpg_id serial,
    fpg_descricao varchar(20),
    fpg_num_max_parc integer,
    fpg_num_padrao_parc integer,
    fpg_intervalo_dias integer,
    fpg_percentual_acres numeric(10,2),
    CONSTRAINT forma_pgto_pk PRIMARY KEY (fpg_id)
);

CREATE TABLE ped_cab
(
    pc_id serial,
    pc_cli integer,
    pc_data date,
    pc_valor numeric(10,2),
    pc_fpg_id integer,
    pc_qtd_parcela integer,
    pc_status varchar(255),
    CONSTRAINT ped_cab_pkey PRIMARY KEY (pc_id),
    CONSTRAINT ped_cab_pessao_fk FOREIGN KEY (pc_cli)
        REFERENCES pessoa (pes_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ped_cab_forma_pgto_fk FOREIGN KEY (pc_fpg_id)
        REFERENCES forma_pgto (fpg_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE ped_item
(
    pi_id serial ,
    pi_ped_id integer,
    pi_pro_numero integer,
    pi_qtd integer,
    pi_valor_unit numeric(10,2),
    pi_valor_total numeric(10,2),
    CONSTRAINT ped_item_pkey PRIMARY KEY (pi_id),
    CONSTRAINT ped_item_produto_fk FOREIGN KEY (pi_pro_numero)
        REFERENCES produto (pro_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ped_item_ped_cab_fk FOREIGN KEY (pi_ped_id)
        REFERENCES ped_cab (pc_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

