#language: pt

Funcionalidade: Busca de pacientes

Cenario: Buscar paciente pelo primeiro nome
  Dado que estou logado como owner
  E que um paciente "Mauricio Aniche" padrao esta cadastrado
  E que um paciente "Murilo Amendola" padrao esta cadastrado
  E que estou na tela de listagem de pacientes
  Quando busco o paciente "Mauricio"
  E clico em Buscar
  E espero a resposta
  Entao devo ver "Mauricio Aniche"
  E nao devo ver "Murilo Amendola"

Cenario: Buscar paciente pelo sobrenome
  Dado que estou logado como owner
  E que um paciente "Mauricio Aniche" padrao esta cadastrado
  E que um paciente "Murilo Amendola" padrao esta cadastrado
  E que estou na tela de listagem de pacientes
  Quando busco o paciente "Amendola"
  E clico em Buscar
  E espero a resposta
  Entao devo ver "Murilo Amendola"
  E nao devo ver "Mauricio Aniche"

Cenario: Buscar varios pacientes pelo sobrenome
  Dado que estou logado como owner
  E que um paciente "Mauricio Aniche" padrao esta cadastrado
  E que um paciente "Marcelo Aniche" padrao esta cadastrado
  E que um paciente "Roberto Aniche" padrao esta cadastrado
  E que um paciente "Murilo Amendola" padrao esta cadastrado
  E que estou na tela de listagem de pacientes
  Quando busco o paciente "Amendola"
  E clico em Buscar
  E espero a resposta
  Entao devo ver "Mauricio Aniche"
  E devo ver "Marcelo Aniche"
  E devo ver "Roberto Aniche"
  E nao devo ver "Murilo Amendola"

Cenario: Buscar paciente por qualquer parte do nome
  Dado que estou logado como owner
  E que um paciente "Mauricio Aniche" padrao esta cadastrado
  E que um paciente "Murilo Amendola" padrao esta cadastrado
  E que estou na tela de listagem de pacientes
  Quando busco o paciente "Amend"
  E clico em Buscar
  E espero a resposta
  Entao devo ver "Murilo Amendola"
  E nao devo ver "Mauricio Aniche"

Cenario: Paciente inexistente
  Dado que estou logado como owner
  E que um paciente "Mauricio Aniche" padrao esta cadastrado
  E que um paciente "Murilo Amendola" padrao esta cadastrado
  E que estou na tela de listagem de pacientes
  Quando busco o paciente "chocolate"
  E clico em Buscar
  E espero a resposta
  Entao nao devo ver "Murilo Amendola"
  E nao devo ver "Mauricio Aniche"
  E devo ver "Nenhum paciente foi encontrado!"

