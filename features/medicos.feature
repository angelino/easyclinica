#language: pt

Funcionalidade: Cadastro de medicos

Esquema do Cenario: Novo medico
  Dado que estou logado como owner
  E que estou na tela de novo medico
  Quando preencho "<nome>" em "doctor.name"
  E preencho "<crm>" em "doctor.crm"
  E preencho "<telefone>" em "doctor.telephone"
  E preencho "<email>" em "doctor.email"
  E preencho "<observacoes>" em "doctor.observations"
  E seleciono "<especialidade>" em "doctor.specialty.id"
  E pressiono "Salvar"
  Entao devo ver "Médico adicionado com sucesso!"
  E devo ver "<nome>"
  E devo ver "<crm>"
  E devo ver "<telefone>"
  E devo ver "<email>"
  E devo ver "<especialidade>"
  E devo ver "<observacoes>"
  
Exemplos:

|nome           | crm    | telefone        | email            | especialidade | observacoes  |
|Doctor House   | 12.345 | (11) 1122-3344  | doctor@house.com | Ortopedia     | super medico |
|Spock          | 123-A  |                 |                  | Pediatria     | vulcaniano   |

Cenario: Editar Medico
	Dado que estou logado como owner
	E que um medico "Dr. Seboso" padrao esta cadastrado
	Quando clico no link "Alterar este médico"
	E preencho "Dr. Seboso 2" em "doctor.name"
	E pressiono "Salvar"
	Entao devo ver "Médico alterado com sucesso!"
	E devo ver "Dr. Seboso 2"
	
Cenario: Deve validar campos obrigatorios
	Dado que estou logado como owner
	E que estou na tela de novo medico
	Quando pressiono "Salvar"
	Entao devo ver "campo obrigatório"
