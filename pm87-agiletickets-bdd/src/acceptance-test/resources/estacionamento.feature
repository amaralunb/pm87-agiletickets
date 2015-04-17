# language: pt

Funcionalidade: Cadastro de Estacionamento
  Para oferecer serviços de táxi da minha outra empresa perto de onde é inconveniente ir de carro
  Como dono também de uma frota de táxis
  Quero poder cadastrar se um estabelecimento tem estacinamento 

  Cenário: há estacionamento
    Dado que o usuário abre a lista de estabelecimentos
    Quando o usuário marca que há estacionamento
    E clica em "Adicionar"
    Então a última linha da lista mostra que há estacionamento
    
  Cenário: não há estacionamento
    Dado que o usuário abre a lista de estabelecimentos
    Quando o usuário marca que não há estacionamento
    E clica em "Adicionar"
    Então a última linha da lista mostra que não há estacionamento