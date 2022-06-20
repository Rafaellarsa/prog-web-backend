-- Database: e_smd

-- DROP DATABASE IF EXISTS e_smd
    
-- Table: public.categoria

-- DROP TABLE IF EXISTS public.categoria;

CREATE SEQUENCE categoria_id_categoria_seq AS integer;

CREATE TABLE IF NOT EXISTS public.categoria
(
    id_categoria integer NOT NULL DEFAULT nextval('categoria_id_categoria_seq'::regclass),
    descricao_categoria character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT categoria_pkey PRIMARY KEY (id_categoria)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.categoria
    OWNER to postgres;
    
-- Table: public.produto

-- DROP TABLE IF EXISTS public.produto;

CREATE SEQUENCE produto_id_produto_seq AS integer;
CREATE SEQUENCE produto_id_categoria_produto_seq AS integer;

CREATE TABLE IF NOT EXISTS public.produto
(
    id_produto integer NOT NULL DEFAULT nextval('produto_id_produto_seq'::regclass),
    nome_produto character varying(50) COLLATE pg_catalog."default" NOT NULL,
    descricao_produto character varying(50) COLLATE pg_catalog."default" NOT NULL,
    preco_produto integer NOT NULL,
    foto_produto character varying(2000) COLLATE pg_catalog."default" NOT NULL,
    quantidade_produto integer NOT NULL,
    id_categoria_produto integer NOT NULL DEFAULT nextval('produto_id_categoria_produto_seq'::regclass),
    CONSTRAINT produto_pkey PRIMARY KEY (id_produto),
    CONSTRAINT fk_id_categoria FOREIGN KEY (id_categoria_produto)
        REFERENCES public.categoria (id_categoria) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.produto
    OWNER to postgres;
    
    
-- Table: public.usuario

-- DROP TABLE IF EXISTS public.usuario;

CREATE SEQUENCE usuario_id_usuario_seq AS integer;

CREATE TABLE IF NOT EXISTS public.usuario
(
    id_usuario integer NOT NULL DEFAULT nextval('usuario_id_usuario_seq'::regclass),
    adm boolean NOT NULL,
    nome_usuario character varying(50) COLLATE pg_catalog."default" NOT NULL,
    endereco_usuario character varying(60) COLLATE pg_catalog."default" NOT NULL,
    email_usuario character varying(50) COLLATE pg_catalog."default" NOT NULL,
    login_usuario character varying(50) COLLATE pg_catalog."default" NOT NULL,
    senha_usuario character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario),
    CONSTRAINT login_usuario_uk UNIQUE (login_usuario)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.usuario
    OWNER to postgres;
    
-- Table: public.venda

-- DROP TABLE IF EXISTS public.venda;

CREATE SEQUENCE venda_id_venda_seq AS integer;
CREATE SEQUENCE venda_id_cliente_venda_seq AS integer;
CREATE SEQUENCE venda_id_produto_venda_seq AS integer;

CREATE TABLE IF NOT EXISTS public.venda
(
    id_venda integer NOT NULL DEFAULT nextval('venda_id_venda_seq'::regclass),
    data_hora_venda time with time zone NOT NULL,
    id_cliente_venda integer NOT NULL DEFAULT nextval('venda_id_cliente_venda_seq'::regclass),
    id_produto_venda integer NOT NULL DEFAULT nextval('venda_id_produto_venda_seq'::regclass),
    CONSTRAINT venda_pkey PRIMARY KEY (id_venda),
    CONSTRAINT fk_idcliente_venda FOREIGN KEY (id_cliente_venda)
        REFERENCES public.usuario (id_usuario) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_idprod_venda FOREIGN KEY (id_produto_venda)
        REFERENCES public.produto (id_produto) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.venda
    OWNER to postgres;
    
-- Table: public.venda_produto

-- DROP TABLE IF EXISTS public.venda_produto;

CREATE SEQUENCE venda_produto_id_venda_seq AS integer;
CREATE SEQUENCE venda_produto_id_produto_seq AS integer;

CREATE TABLE IF NOT EXISTS public.venda_produto
(
    id_venda integer NOT NULL DEFAULT nextval('venda_produto_id_venda_seq'::regclass),
    id_produto integer NOT NULL DEFAULT nextval('venda_produto_id_produto_seq'::regclass),
    quantidade integer NOT NULL,
    CONSTRAINT venda_produto_pkey PRIMARY KEY (id_venda, id_produto),
    CONSTRAINT fk_id_produto FOREIGN KEY (id_produto)
        REFERENCES public.produto (id_produto) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_id_venda FOREIGN KEY (id_venda)
        REFERENCES public.venda (id_venda) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.venda_produto
    OWNER to postgres;