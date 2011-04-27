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
|Mauricio Aniche| (11) 3729-0433 | (11) 8717-7145 | (11) 1234-5678 | 23/01/1986  | 43.942.694-7  | 330.591.998-17 | Desenvolvedor de Software | Solteiro | mauricioaniche@gmail.com | Particular |             | 01538-001 | Av. Lins de Vasconcelos, 1480 - Casa 3 | Cambuci | São Paulo | SP     | criador do sw |
|Marcelo Aniche | (11) 3729-0433 |                |                | 12/01/1989  |               | 330.591.998-17 | Estudante de Medicina     | Casado   | marceloaniche@gmail.com  | Amil       |             | 01538-001 | Av. Lins de Vasconcelos, 1480 - Casa 3 | Cambuci | São Paulo | SP     | meu irmao     |

Cenario: Editar Paciente
	Dado que estou logado como owner
	E que um paciente "Mauricio" padrao esta cadastrado
	Quando clico no link "Edição"
	E preencho "Mauricio 2" em "patient.name"
    E preencho "(11) 8877-7777" em "patient.telephone"
    E preencho "(11) 6666-6666" em "patient.cellphone"
    E preencho "(11) 5555-5555" em "patient.commercialPhone"
    E preencho "11/01/1970" em "patient.birthDate"
    E preencho "123.4" em "patient.rg"
    E preencho "231.444.052-80" em "patient.cpf"
    E preencho "nova prof" em "patient.profession"
    E seleciono "Casado" em "patient.maritalStatus"
    E preencho "novoEmail" em "patient.email"
    E seleciono "Amil" em "patient.healthCarePlan.id"
    E preencho "123-44" em "patient.healthCarePlanCode"
    E preencho "11223-445" em "patient.address.postalCode"
    E preencho "novo end" em "patient.address.street"
    E preencho "novo bairro" em "patient.address.neighborhood"
    E preencho "nova cidade" em "patient.address.city"
    E preencho "RJ" em "patient.address.state"
    E preencho "nova obs" em "patient.observations"
	E pressiono "Salvar"
	Entao devo ver "Paciente atualizado com sucesso!"
	E devo ver "Mauricio 2"
	E devo ver "(11) 8877-7777"
	E devo ver "(11) 6666-6666"
	E devo ver "(11) 5555-5555"
	E devo ver "11/01/1970"
	E devo ver "123.4"
	E devo ver "231.444.052-80"
	E devo ver "nova prof"
	E devo ver "Casado"
	E devo ver "novoEmail"
	E devo ver "Amil"
	E devo ver "123-44"
	E devo ver "11223-445"
	E devo ver "novo end"
	E devo ver "novo bairro"
	E devo ver "nova cidade"
	E devo ver "RJ"
	E devo ver "nova obs"
	
Cenario: Deve validar campos obrigatorios
	Dado que estou logado como owner
	E que estou na tela de novo paciente
	Quando pressiono "btnSalvar"
	Entao devo ver "campo obrigatório"
