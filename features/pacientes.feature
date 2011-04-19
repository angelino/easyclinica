#language: pt

Funcionalidade: Cadastro de pacientes

Cenario: Novo paciente
  Dado que estou logado como owner
  E que estou na tela de novo paciente
  Quando preencho "Fulano da Silva" em "patient.name"
  E preencho "23/01/1986" em "patient.birthDate"
  E preencho "330.591.998-17" em "patient.cpf"
  E preencho "(11) 1234-5678" em "patient.telephone"
  E pressiono "btnSalvar"
  Entao devo ver "Paciente adicionado com sucesso!"
  E devo ver "Fulano da Silva"
  
