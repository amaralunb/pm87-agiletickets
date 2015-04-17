# language: pt

Funcionalidade: Cadastro de Estabelecimento

  Cenário: cadastro básico
    Dado que o usuário abre a lista de estabelecimentos
    Quando o usuário informa o nome "Caelum"
    E informa o endereço "R. Vergueiro, 3185"
    E clica em "Adicionar"
    Então a última linha da lista mostra "Caelum" e "R. Vergueiro, 3185"
    
  Cenário: nome é obrigatório
    Dado que o usuário abre a lista de estabelecimentos
    Quando o usuário não informa o nome
    Mas informa o endereço "R. Vergueiro, 3185"
    E clica em "Adicionar"
    Então deve ser mostrada a mensagem "O nome não pode ser vazio"
    
  Cenário: endereço é obrigatório
    Dado que o usuário abre a lista de estabelecimentos
    Quando o usuário informa o nome "Caelum"
    Mas não informa o endereço
    E clica em "Adicionar"
    Então deve ser mostrada a mensagem "O endereco não pode ser vazio"