#language: pt

Funcionalidade: Cadastro de anamneses

Cenario: Nova Anamnese simples
  Dado que estou logado como owner
  E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
  E que um paciente "Mauricio" padrao esta cadastrado
  Quando clico no link "Anamnese"
  E clico no link "Nova anamnese"
  E preencho "10/10/2010" em "anamnese.date"
  E preencho "dor nas costas" em "anamnese.complaintAndDuration"
  E seleciono "Dr. Roberto Aniche" em "anamnese.doctor.id"
  E pressiono "Salvar"
  Entao devo ver "Anamnese adicionada com sucesso!"
  E devo ver "dor nas costas"
  E devo ver "10/10/2010"
  E devo ver "Dr. Roberto"

Cenario: Editar Anamnese
	Dado que estou logado como owner
    E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" padrao esta cadastrado
	E que uma anamnese no dia "10/10/2010" falando "paciente com dor nas costas" esta cadastrada
	Quando clico no link "Editar anamnese"
	E preencho "paciente com dor nas costas e nos rins" em "anamnese.complaintAndDuration"
	E preencho "11/11/2010" em "anamnese.date"
	E pressiono "Salvar"
	Entao devo ver "Anamnese alterada com sucesso!"
	E devo ver "paciente com dor nas costas e nos rins"
	E devo ver "11/11/2010"
	
Cenario: Deve validar campos requeridos
	Dado que estou logado como owner
	E que um paciente "Mauricio" padrao esta cadastrado
	Quando clico no link "Anamnese"
	E clico no link "Nova anamnese"
	E pressiono "Salvar"
	Entao devo ver "campo obrigatório"
