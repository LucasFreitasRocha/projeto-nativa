# projeto-teste-empresa

   Esse repositório é  uma api e um desafio logico para um processo seletivo desenvolvido com framework spring boot (JAVA), foi implementado spring security para autenticação com jwt (Bearer token).
   Para rodar a aplicação é só escolher a ide (intellij, sts , eclipse e etc), o spring boot já tem o servidor tomcat embutido e por padrão o servidor é levantado na porta 8080
   http://localhost:8080/
   
## Testar as rotas 
   utilizei o [insominia](https://github.com/LucasFreitasRocha/projeto-nativa/blob/master/Insomnia_nativa.json) (pode utilizar o postman também) ou swagger
   
## Banco de dados
   foi feito com postgres e o script DDL para criação do banco de dados está no src/main/resources/data.sql ou clique [aqui](https://github.com/LucasFreitasRocha/projeto-nativa/blob/master/src/main/resources/data.sql)
   credenciais para o acesso ao banco está no src/main/resources/application.proprities ou clique [aqui](https://github.com/LucasFreitasRocha/projeto-nativa/blob/master/src/main/resources/application.properties)
   
            spring.jpa.hibernate.ddl-auto=update
            spring.datasource.url=jdbc:postgresql://localhost:5432/nativa
            spring.datasource.username=root
            spring.datasource.password=root   
## Swagger

   para facilitar a interação com a api foi incluído  o swagger na aplicação. O Swagger é uma linguagem de descrição de interface para descrever APIs RESTful expressas usando JSON.
   O swagger é acessível na rota /swagger-ui.html - http://localhost:8080/swagger-ui.html#/

![alt text](https://github.com/LucasFreitasRocha/projeto-nativa/blob/master/img/Swagger.PNG)

## desafio lógico 
   o desafio é dado um numero inteiro n e retornar o maior numero da família n.
   
   a logica para esse desafio é só enviar um numero inteiro n para a rota /desafio-logico?numero= e resource chama um serviço onde está logica
   
     public int retornarMaiorDaFamiliaN(Integer numero) {
        if(numero < 0){
            throw  new BadRequestException("Informe o numero inteiro positivo");
        }else{
            List<String> numeros = Arrays.asList(numero.toString().split(""));
            Collections.sort( numeros,  Collections.reverseOrder());
            StringBuilder s = new StringBuilder();
            numeros.forEach( n -> s.append(n));
            return  (Integer.parseInt(s.toString()) >= 100000000) ? -1 : Integer.parseInt(s.toString())  ;

        }
    }
   o serviço recebe e verifica se é o número é um inteiro positivo, caso seja eu transformo o integer para string e utilizo o 
   - split : separa a string de acordo com caractere indicado, como eu coloco uma string sem espaço separa todos os numeros e transformo em uma lista
   - sort: faço o método de ordenação sort e como segundo parâmetro é utilizado para inverter a ordem da lista para transformar uma lista de ordenada decrescente
   - forEach: faço um forEach na lista e adiciono em um stringBuilder
   - if ternario: se for maior que 100000000 retorna -1 de acordo com o desafio , caso não retorna o maior da família do inteiro n
   
   

# API

## Teste
   Os teste se encontram em /src/teste/java/com/nativa/ ou clique [aqui](https://github.com/LucasFreitasRocha/projeto-nativa/tree/master/src/test/java/com/nativa)

## estrutura
   A estrutura é composta do Controller(resource) -> Service -> que acessa o model, faz as logicas devidas e chama o -> repository que sava no banco de dados utilizando o 
   a orm JPA hibernate.
   Os request e response da api eu utilizei o padrão DTO para fazer a comunicação e nesses dtos estou utilizando @valid para validar dados como email que eu criei um validador
   propio.
   senha no model user:  senha tem que ser de 8 a 20
   Id do models: utilizei o uudi para mais segurança e gerado em um metodo que está na classe generate que também é responsavel de encripitar a senha antes de salvar um usuario no banco, a classe generete esta no package utils
      ![alt text](https://github.com/LucasFreitasRocha/projeto-nativa/blob/master/img/estrutura.png)


## Autorização
   A maioria das rotas são protegidas e para acessar é necessário  adicionar um token no header "Bearer token" pois a api não está guardando estado(sessão)
   
   ![alt text](https://github.com/LucasFreitasRocha/projeto-nativa/blob/master/img/headerToken.png)

   Mas tem rotas que não precisa está autenticado, para configurar foi criado uma classe SecurityConfiguration  no package com.nativa.settings 
   
   ![alt text](https://github.com/LucasFreitasRocha/projeto-nativa/blob/master/img/rotasprotegidas.png)
   
   Rotas que estão livre:
   - metodo post /user:  que é a rota de criação de usuario
   - metodo post /auth: para se autenticar
   - metodo get /desafio-logico é a rota que é feito o desafio logico
   
   para configurar rota livre como o modulo é só utilizar o metodo permitALL passando o a rota e o metodo
    no antMatchers como está na foto,  (ex: http.authorizeRequests().antMatchers(HttpMethod.POST, "/user").permitAll())
    
   Para os outras rotas é necessário o token como foi explicado anteriomente, e para validar o token foi criado middleware(filter) -  AutenticacaoViaTokenFilter:
   ![alt text](https://github.com/LucasFreitasRocha/projeto-nativa/blob/master/img/middleware.png)


## Autenticação
   Para se autenticar é enviado um post para rota /atuh com body contendo email e senha
  ![alt text](https://github.com/LucasFreitasRocha/projeto-nativa/blob/master/img/auth.png)
  
 ## Paginação
   Todas as rotas index dos controllers(resources) recebe um objeto pageable para fazer paginação trazer quantidade de item colocando parametro size na rota, para selecionar a pagina é só colocar page
   ![alt text](https://github.com/LucasFreitasRocha/projeto-nativa/blob/master/img/pageable.png)
   
 ## Rotas
    Todas as rotas
 - UsuarioResource
   - index - metodo: get - path: /user - param: email(required= false):string, name(required= false):string, pageable(size,page) - header: token
   - createUser - Metodo: post  - path: /user - body: 
         
         {
            "email": "string",
            "name": "string",
             "password": "string"
         }

   - showUser - metodo: get = path: /user/{id} - header: token
   - updateUser - metodo: put - path: /user/{id} - header: token - body: 

         {
            "email": "string",
            "name": "string",
            "password": "string" - senha tem que ser de 8 a 20
         }
   - deleteUser - metodo: delete - path:  /user/{id} - header: token
   - alterarSenha - metodo: patch - path: /user/alterar-senha - header: token - body:

         {
            "password": "string" - senha tem que ser de 8 a 20
         }
       Panho o id do usuario através do token
 - MarcaResource
   - index - metodo: get - path: /marca - param: email(required= false):string, name(required= false):string, pageable(size,page) - header: token
   - createMarca - metodo: post - path: /marca - header: token - body: 

         {
            "name": "string"
         }
   - showMarca - metodo: get - path: /marca/{id} - header: token
   - updateMarca - metodo: put - path: /marca/{id} - header: token - body:

         {
         "name": "string"
         }
   - deleteMarca - metodo: post -  path: /marca/{id} - header: token
- PatrimonioResource
   - index - metodo: get - path: /patrimonio - param: ntombo(required= false):intenger, pageable(size,page) - header: token
   - createPatrimonio - metodo: post - path /patrimonio - header: token - body:

         {
             "description": "string",
             "marcaId": "string",
             "name": "string"
         }
         
   - showPatrimonio - metodo: get - path: /patrimonio/{id} - header: token
   - updatePatrimonio - metodo: put - path: /patrimonio/{id} - header: token - body:
         
         {
            "description": "string",
            "marcaId": "string",
            "name": "string"
         }
  - deletePatrimonio - metodo: delete - path: /patrimonio/{id} - header: token

- AuthResource
   - authenticate - metodo: post - path: /auth - body: 
     
         {
             "email": "string",
             "password": "string"
         }
         
  - Para mais detalhes acesse via swagger, na rota /swagger-ui.hmtl -  http://localhost:8080/swagger-ui.html#/
