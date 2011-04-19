#language: pt

Funcionalidade: Cadastro de pacientes

Esquema do Cenario: Novo paciente
  Dado que estou logado como owner
  E que estou na tela de novo paciente
  Quando preencho "<nome>" em "patient.name"
  E preencho "<telefone>" em "patient.telephone"
  E preencho "<celular>" em "patient.cellphone"
  E preencho "<comercial>" em "patient.commercialPhone"
  E preencho "<nascimento>" em "patient.birthDate"
  E preencho "<rg>" em "patient.rg"
  E preencho "<cpf>" em "patient.cpf"
  E preencho "<profissao>" em "patient.profession"
  E seleciono "<civil>" em "patient.maritalStatus"
  E preencho "<email>" em "patient.email"
  E seleciono "<convenio>" em "patient.healthCarePlan.id"
  E preencho "<carteirinha>" em "patient.healthCarePlanCode"
  E preencho "<cep>" em "patient.address.postalCode"
  E preencho "<endereco>" em "patient.address.street"
  E preencho "<bairro>" em "patient.address.neighborhood"
  E preencho "<cidade>" em "patient.address.city"
  E preencho "<estado>" em "patient.address.state"
  E preencho "<observacoes>" em "patient.observations"
  E pressiono "btnSalvar"
  Entao devo ver "Paciente adicionado com sucesso!"
  E devo ver "<nome>"
  E devo ver "<telefone>"
  E devo ver "<celular>"
  E devo ver "<comercial>"
  E devo ver "<nascimento>"
  E devo ver "<rg>"
  E devo ver "<cpf>"
  E devo ver "<profissao>"
  E devo ver "<civil>"
  E devo ver "<email>"
  E devo ver "<convenio>"
  E devo ver "<carteirinha>"
  E devo ver "<cep>"
  E devo ver "<endereco>"
  E devo ver "<bairro>"
  E devo ver "<cidade>"
  E devo ver "<estado>"
  E devo ver "<observacoes>"
  
Exemplos:

|nome           | telefone       | celular        | comercial      | nascimento  | rg            | cpf            | profissao                 | civil    |email                     | convenio   | carteirinha | cep       | endereco                               | bairro  | cidade    | estado | observacoes   |
|Mauricio Aniche| (11) 3729-0433 | (11) 8717-7145 | (11) 1234-5678 | 23/01/1986  | 43.942.694-7 | 330.591.998-17 | Desenvolvedor de Software | Solteiro | mauricioaniche@gmail.com | Particular |             | 01538-001 | Av. Lins de Vasconcelos, 1480 - Casa 3 | Cambuci | São Paulo | SP     | criador do sw |
