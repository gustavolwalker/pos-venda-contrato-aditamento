# Contrato Aditamento

Aplicação para controle de aditamento em contratos.

## Tecnologias

- Linguagem: Java 11
- Framework: Spring boot 2.7.14
- Bibliotecas:
  - Spring Web
  - Spring Validation
  - Spring OpenFeign
  - Spring Test
  - Lombok
  
## Arquitetura

A aplicação foi construída para atender os requisitos de disponibilização de uma api com dois 
endpoints para aditamento de contratos, dado requisitos do teste foram desenvolvidas estruturas 
de configuração para validação de payload de entrada e modificação de saída.

### Controller

- AditamentoController: Controller responsável por receber as requisições para os dois endpoints do sistema.

### Config

- AditamentoHandlerInterceptor: Config para validar a informação da chave header 
'itau-pos-venda-teste' obrigatória para todas as requisições e deve conter um UUID v4 válido;

- AditamentoAdvice: Config para modificação do response (id_contrato, ultimo_digito_contrato) 
podendo assim manter o estado do dado durante a aplicação e ajustando apenas quando necessário 
dado o cenário apresentado no response.

### Exception

- AditamentoException: Exception genérica de execução da aplicação.

- BusinessException: Handler de controle de Exceptions e transformação de response body error, 
apresenta ouvinte para a exception genérica AditamentoException e MethodArgumentNotValidException 
na validação de payload de entrada.

### Service 

- AditamentoService: Serviço de negócio para realização das ações de aditamento de contrato.

- CalculoJurosService: Serviço para conexão a API de cálculo de juros e dado o contexto de ambiente 
não produtivo realiza o cálculo de juros simples.

- ContratoService: Serviço de negócio com as regras da aplicação e "pseudo" controle de 
persistência.

## Extras

- Aditamento.postman_collection.json: Collection Postman com acesso aos endpoints para teste.











