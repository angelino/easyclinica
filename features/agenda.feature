#language: pt

Funcionalidade: Agenda

Cenario: Compromisso com um paciente
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	E que estou na tela de agenda
	Quando seleciono a agenda do doutor "Dr. Roberto Aniche"
	E na data "11/09/2011"
	E pressiono "Carregar Compromissos"
	E comeco a adicionar um compromisso no 3o da lista
	E preencho e seleciono o compromisso "Mauricio"
	E dou enter e espero
	Entao devo ver "Mauricio (Amil)"
	

Cenario: Compromisso nao vinculado a um paciente
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	E que estou na tela de agenda
	Quando seleciono a agenda do doutor "Dr. Roberto Aniche"
	E na data "11/09/2011"
	E pressiono "Carregar Compromissos"
	E comeco a adicionar um compromisso no 3o da lista
	E preencho o compromisso "Festa da empresa"
	E dou enter e espero
	Entao devo ver "Festa da empresa"
	
Cenario: Compromissos no mesmo horario
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	E que um paciente "Marcelo" do convenio "Amil" esta cadastrado
	E que estou na tela de agenda
	Quando seleciono a agenda do doutor "Dr. Roberto Aniche"
	E na data "11/09/2011"
	E pressiono "Carregar Compromissos"
	E comeco a adicionar um compromisso no 5o da lista
	E preencho e seleciono o compromisso "Mauricio"
	E dou enter e espero
	E comeco a adicionar um compromisso no 5o da lista
	E preencho e seleciono o compromisso "Marcelo"
	E dou enter e espero
	Entao devo ver "Mauricio (Amil)"
	Entao devo ver "Marcelo (Amil)"

Cenario: Varios compromissos
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	E que um paciente "Marcelo" do convenio "Amil" esta cadastrado
	E que estou na tela de agenda
	Quando seleciono a agenda do doutor "Dr. Roberto Aniche"
	E na data "11/09/2011"
	E pressiono "Carregar Compromissos"
	E comeco a adicionar um compromisso no 1o da lista
	E preencho e seleciono o compromisso "Mauricio"
	E dou enter e espero
	E comeco a adicionar um compromisso no 2o da lista
	E preencho e seleciono o compromisso "Marcelo"
	E dou enter e espero
	E comeco a adicionar um compromisso no 3o da lista
	E preencho o compromisso "Bla"
	E dou enter e espero
	E comeco a adicionar um compromisso no 4o da lista
	E preencho o compromisso "Kablam"
	E dou enter e espero
	E comeco a adicionar um compromisso no 5o da lista
	E preencho o compromisso "Ble"
	E dou enter e espero
	E comeco a adicionar um compromisso no 6o da lista
	E preencho o compromisso "Bli"
	E dou enter e espero
	E comeco a adicionar um compromisso no 7o da lista
	E preencho o compromisso "Blo"
	E dou enter e espero
	E comeco a adicionar um compromisso no 8o da lista
	E preencho o compromisso "Blu"
	E dou enter e espero
	Entao devo ver "Mauricio (Amil)"
	Entao devo ver "Marcelo (Amil)"
	Entao devo ver "Bla"
	Entao devo ver "Ble"
	Entao devo ver "Bli"
	Entao devo ver "Blo"
	Entao devo ver "Blu"
	Entao devo ver "Kablam"

Cenario: Compromissos de medicos diferentes
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um medico "Dr. Joaquim Grava" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	E que um paciente "Marcelo" do convenio "Amil" esta cadastrado
	E que estou na tela de agenda
	Quando seleciono a agenda do doutor "Dr. Roberto Aniche"
	E na data "11/09/2011"
	E pressiono "Carregar Compromissos"
	E comeco a adicionar um compromisso no 5o da lista
	E preencho e seleciono o compromisso "Mauricio"
	E dou enter e espero
	Entao devo ver "Mauricio (Amil)"
	Quando seleciono a agenda do doutor "Dr. Joaquim Grava"
	E pressiono "Carregar Compromissos"
	E comeco a adicionar um compromisso no 5o da lista
	E preencho e seleciono o compromisso "Marcelo"
	E dou enter e espero
	Entao devo ver "Marcelo (Amil)"
	Quando seleciono a agenda do doutor "Dr. Roberto Aniche"
	E pressiono "Carregar Compromissos"
	Entao devo ver "Mauricio (Amil)"
	
Cenario: Deletar um compromisso
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	E que estou na tela de agenda
	Quando seleciono a agenda do doutor "Dr. Roberto Aniche"
	E na data "11/09/2011"
	E pressiono "Carregar Compromissos"
	E comeco a adicionar um compromisso no 3o da lista
	E preencho e seleciono o compromisso "Mauricio"
	E dou enter e espero
	E removo o 3o compromisso
	Entao nao devo ver "Mauricio (Amil)"

Cenario: Gravar horario de entrada um compromisso
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	E que estou na tela de agenda
	Quando seleciono a agenda do doutor "Dr. Roberto Aniche"
	E na data "11/09/2011"
	E pressiono "Carregar Compromissos"
	E comeco a adicionar um compromisso no 3o da lista
	E preencho e seleciono o compromisso "Mauricio"
	E dou enter e espero
	E marco a hora de chegada como "09:45"
	E pressiono "Carregar Compromissos"
	Entao devo ver "09:45"	

