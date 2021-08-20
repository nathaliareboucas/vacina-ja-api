# vacina-ja-api
API Vacinação COVID-19

## Descricão
Sistema de cadastro para vacinação contra o COVID-19, onde ao se cadastrar o usuário entrará em uma fila de vacinação ordenada 
por data de cadastro. O projeto foi desenvolvido utilizando as seguintes tecnologias:

* Spring boot - backend

* ReactJS - frontend

* Integração com Keycloak - SSO

(Para fins de teste as aplicações utilizam o banco em memória H2)

## build
Clone este projeto e a aplicação frontend <a href="https://github.com/nathaliareboucas/vacina-ja">vacina-ja</a>.

Em seguida execute o comando

`docker run -p 8080:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:14.0.0`

para subir um container Keycloak (necessário ter o docker instalado).

Acesse o painel administrativo do keycloak com usuario : `admin` e senha `admin`.

Na raíz do projeto vacina-ja-api foi disponibilizado o arquivo de configuração do Keycloak, basta importá-lo pelo painel administrativo.

Foram criados dois usuários:

* usuário: `teste1@teste.com` | senha: `123456`
* usuário: `bla@teste.com` | senha: `123456`

Para configrar as notificações de agendamento é necessário adicionar o remetente em `EmailService.java` e em `application.properties`. Caso deseje alterar a quantidade de usuários que serão agendados, bem como o horário do envio de email de notificação, realize as alterações em `AgendamentoTask.java`.

Baixados os projetos e feita a configuração de envio de email e do Keycloak, vamos executar a aplicação.

Na raíz do projeto <b>vacina-ja-api</b> execute os seguinte comandos:

`mvn clean package` e em seguida `java -jar target/vacina-ja-api-0.0.1-SNAPSHOT.jar`.

Agora na raiz do projeto <b>vacina-ja</b> execute o seguinte comando:

`ỳarn start`

Realize o login com um dos dois usuários disponibilizados acima ou acesse o painel administrativo do keycloak e crie mais usuários.

