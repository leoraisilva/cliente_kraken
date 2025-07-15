# cliente_kraken
A API do Sistema Kraken responsável pelo gerenciamento do Usuario

## Serviço tem como a função:
Cria, alterar, buscar e deletar os usuarios do banco de dado.

## Ferramentas usado:
Foi criado em Spring Boot e conteirizado com o Docker

## Quem depende?
O serviços do sistema kraken depende ter o cadastro feito, caso não tenha feito não será possível utilizar o serviço

## Como é feito a permissão?
Ele gera a permissão ao acessar ao serviço com as credenciais correta, gerando o token JWT para utilizar no servições que tem permissão de acesso
