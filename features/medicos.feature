#language: pt

Funcionalidade: Cadastro de medicos

Cenario: Novo medico
  Dado que estou logado como owner
  E que estou na tela de novo medico
  Quando preencho "Marcelo Aniche" em "doctor.name"
  E preencho "123.456" em "doctor.crm"
  E preencho "(11) 1122-3344" em "doctor.telephone"
  E preencho "marcelo@aniche.com.br" em "doctor.email"
  E preencho "sei la" em "doctor.observations"
  E seleciono "PEDIATRIA" em "doctor.specialty.id"
  E preencho "20" em "doctor.intervalBetweenAppointments"
  E pressiono "Salvar"
  Entao devo ver "Médico adicionado com sucesso!"
  E devo ver "Marcelo Aniche"
  E devo ver "123.456"
  E devo ver "(11) 1122-3344"
  E devo ver "marcelo@aniche.com.br"
  E devo ver "PEDIATRIA"
  E devo ver "sei la"
  E devo ver "20 minutos"
  
Cenario: Editar Medico
	Dado que estou logado como owner
	E que um medico "Dr. Seboso" padrao esta cadastrado
	Quando clico no link "Alterar este médico"
	E preencho "Dr. Seboso 2" em "doctor.name"
	E preencho "11.22" em "doctor.crm"
	E preencho "(11) 8888-7777" em "doctor.telephone"
	E preencho "novo@email.com" em "doctor.email"
	E preencho "nova obs" em "doctor.observations"
	E seleciono "PEDIATRIA" em "doctor.specialty.id"
	E preencho "30" em "doctor.intervalBetweenAppointments"
	E pressiono "Salvar"
	Entao devo ver "Médico alterado com sucesso!"
	E devo ver "Dr. Seboso 2"
	E devo ver "11.22"
	E devo ver "(11) 8888-7777"
	E devo ver "novo@email.com"
	E devo ver "nova obs"
	E devo ver "PEDIATRIA"
	E devo ver "30 minutos"
	
Cenario: Deve validar campos obrigatorios
	Dado que estou logado como owner
	E que estou na tela de novo medico
	Quando pressiono "Salvar"
	Entao devo ver "campo obrigatório"
	
Cenario: Deve buscar medico
	Dado que estou logado como owner
	E que um medico "Roberto Aniche" padrao esta cadastrado
	E que um medico "Marcelo Aniche" padrao esta cadastrado
  	E que estou na tela de listagem de medicos
	E busco o medico "Roberto"
	E clico no link "Buscar"
	Entao devo ver "Roberto Aniche"
	E busco o medico "Marcelo"
	E clico no link "Buscar"
	Entao devo ver "Marcelo Aniche"
	E busco o medico "Aniche"
	E clico no link "Buscar"
	Entao devo ver "Marcelo Aniche"
	E devo ver "Roberto Aniche"
