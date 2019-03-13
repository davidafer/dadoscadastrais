# Dados Cadastrais 
Essa API possibilita a consulta e alteração dos dados cadastrais de um cliente.


# Tecnologias
As tecnologias/frameworks utilizados foram:

    Java 8
    Spring Boot
    JUnit
    Mockito
    RESTFull
    Database H2
    JPA
    SLF4J
    Gson


# Execução
```
$ mvn clean install spring-boot:run
```


# Massa de Testes
Para testes, foram cadastrados os seguintes CPF's: 
    
    10020030088
    20367882388
    89837721073
    27219187530
    74812553261


# Banco de Dados
Para a aplicação, foi utilizado um banco de dados h2 em memória. Pode-se acessá-lo através do browser pela url descrita a seguir ou através de algum gerenciador para banco de dados h2:

    http://localhost:8080/h2-console/
    
    JDBC url: jdbc:h2:mem:testdb
    Username: sa
    Password: <deixar em branco>


# Consulta Cliente
Uma das funções da API é a consulta de um cliente cadastrado. Para efetuá-la, deve-se fazer uma requisição GET para o endereço a seguir passando o cpf como parâmetro:
    
    GET Request: http://localhost:8080/cliente/{cpf}


# Consulta CEP
A API também possibilita a consulta de qualquer endereço existente pelo CEP. Para efetuá-la, deve-se fazer uma requisição GET para a url a seguir passando o cep desejado como parâmetro:
    
    GET Request: http://localhost:8080/cep/{cep}
 

 # Consulta Estados
A API disponibiliza a consulta dos Estados do Brasil. Para efetuá-la, deve-se fazer uma requisição GET para o endereço a seguir:
    
    GET Request: http://localhost:8080/estados


 # Consulta Municípios
Também é possível consultar os Municípios de um determinado Estado, informando como parâmetro da url o id do Estado desejado (retornado na Consulta de Estados).  Para efetuá-la, deve-se fazer uma requisição GET conforme a seguir:
    
    GET Request: http://localhost:8080/municipios/{idEstado}


# Alteração Endereço
Através da API é possível também realizar a alteração do endereço de um determinado cliente. Para efetuá-la, deve-se enviar para a url a seguir um json contendo as alterações de Endereço no Body da requisição PATCH e informar o cpf do cliente como parâmetro na url. Pode-se enviar apenas as informações de endereço que deseja-se alteração ou todas as informações do endereço, pois a aplicação gerenciará as mudanças.

    PATCH Request: http://localhost:8080/cliente/{cpf}
    
    Header: Content-Type:application/json
    
    Body:
    
    {
        "id": 1,
        "rua": "Rua XPTO",
        "numero": "100",
        "complemento": "Apt 123",
        "bairro": "Bela Vista",
        "cep": "01310940",
        "municipio": {
          "id": 1,
          "nome": "São Paulo",
          "estado": {
            "id": 1,
            "nome": "São Paulo"
          }
        },
        "estado": {
          "id": 1,
          "nome": "São Paulo"
        }
    }

    
    
