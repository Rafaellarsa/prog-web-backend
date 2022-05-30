# prog-web-backend

## Equipe:
* 479221 - BRUNO CARVALHO LIMA
* 473928 - ELLEN PESSOA FEITOSA RODRIGUES
* 469887 - IVNA ALMEIDA FILGUEIRAS
* 469886 - RAFAELLA SAMPAIO DE ALENCAR
* 473196 - REBECCA C MARA CAVALCANTE
* 476389 - VANESKA KAREN DE SOUSA SILVA

## Objetivo:

Criar um sistema básico de e-commerce usando Java para web e servelet, implementando o padrão de projeto MVC

## Melhorias identificadas

* No mer - Preço do produto tem q ser double ou float. 
* Mudar a data de timestamp para string/varchar no MER OU tratar a conversão de timestamp para date em model ou em control ao receber do usuário. 
* Refatorar os model DAO para que nao seja cadastrado manualmente o id de cada classe (usuario, venda e produto) e sim seja usado o autoincrement do postgree
