<b>API REST desenvolvida utilizando Spring Boot e Docker.<br><br></b>

Este é um projeto pessoal, não demonstra todo o meu conhecimento, é apenas um simples CRUD de Usuário que sumariza parte do meu entendimento de Java Spring.<br><br>

Operações CRUD de Usuário - OK <br>
Confirmar email do Usuário - OK <br>
Dockerizar a aplicação - OK <br>
Documentação APIDOC e SwaggerUI - OK <br> <br>

Pendente: <br>
Testes Unitários <br>
Token de Autenticação <br>
Criar alguma função além de cadastrar usuário e fazer login (a aplicação pode virar qualquer coisa a partir desse esqueleto) <br>

Instruções para subir um ambiente de desenvolvimento:<br>
Sem docker:<br>
Editar o arquivo resource/application.properties com os dados do banco de dados que tiver instalado em sua máquina, rodar o método main na classe principal do projeto.<br>

Com docker:<br>
Na raiz do projeto, rodar o comando: docker-compose up -d.<br>
Irá fazer o download das imagens necessárias (cerca de 1.4GB ao total)<br>
A partir da segunda vez, rodar o projeto será bem mais rápido! <br>
Documentação formato APIDOC:<br>
http://127.0.0.1:6868/docs/apidoc<br>

Documentação SwaggerUI:<br>
http://127.0.0.1:6868/docs/swagger-ui <br>
Através daqui é possível utilizar todos os endpoints do sistema.<br>

Banco de dados (se utilizar docker): <br>
http://127.0.0.1:8080/ <br>
Username: root<br>
Password: 123456<br>
Database: runeworld_dev<br>

SOS - Apagar tudo criado pelo docker:<br>
docker system prune -a -f<br>
docker image prune -a -f<br>
docker volume prune -f<br>