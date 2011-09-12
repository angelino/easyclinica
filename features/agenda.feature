#language: pt

Funcionalidade: Agenda

@javascript
Cenario: Compromisso com um paciente
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	E que estou na tela de agenda
	Quando seleciono a agenda do doutor "Dr. Roberto Aniche"
	E na data "11/09/2011"
	E carrego os compromissos
	E comeco a adicionar um compromisso no 3o da lista
	E preencho e seleciono o compromisso "Mauricio"
	E confirmo o compromisso dando enter
	Entao devo ver "Mauricio (Amil)"
	
@javascript
Cenario: Compromisso nao vinculado a um paciente
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	E que estou na tela de agenda
	Quando seleciono a agenda do doutor "Dr. Roberto Aniche"
	E na data "11/09/2011"
	E carrego os compromissos
	E comeco a adicionar um compromisso no 3o da lista
	E preencho o compromisso "Festa da empresa"
	E confirmo o compromisso dando enter
	Entao devo ver "Festa da empresa"
	
@javascript
Cenario: Compromissos no mesmo horario
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	E que um paciente "Marcelo" do convenio "Amil" esta cadastrado
	E que estou na tela de agenda
	Quando seleciono a agenda do doutor "Dr. Roberto Aniche"
	E na data "11/09/2011"
	E carrego os compromissos
	E comeco a adicionar um compromisso no 5o da lista
	E preencho e seleciono o compromisso "Mauricio"
	E confirmo o compromisso dando enter
	E comeco a adicionar um compromisso no 5o da lista
	E preencho e seleciono o compromisso "Marcelo"
	E confirmo o compromisso dando enter
	Entao devo ver "Mauricio (Amil)"
	Entao devo ver "Marcelo (Amil)"

@javascript
Cenario: Varios compromissos
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	E que um paciente "Marcelo" do convenio "Amil" esta cadastrado
	E que estou na tela de agenda
	Quando seleciono a agenda do doutor "Dr. Roberto Aniche"
	E na data "11/09/2011"
	E carrego os compromissos
	E comeco a adicionar um compromisso no 1o da lista
	E preencho e seleciono o compromisso "Mauricio"
	E confirmo o compromisso dando enter
	E comeco a adicionar um compromisso no 2o da lista
	E preencho e seleciono o compromisso "Marcelo"
	E confirmo o compromisso dando enter
	E comeco a adicionar um compromisso no 3o da lista
	E preencho o compromisso "Bla"
	E confirmo o compromisso dando enter
	E comeco a adicionar um compromisso no 4o da lista
	E preencho o compromisso "Kablam"
	E confirmo o compromisso dando enter
	E comeco a adicionar um compromisso no 5o da lista
	E preencho o compromisso "Ble"
	E confirmo o compromisso dando enter
	E comeco a adicionar um compromisso no 6o da lista
	E preencho o compromisso "Bli"
	E confirmo o compromisso dando enter
	E comeco a adicionar um compromisso no 7o da lista
	E preencho o compromisso "Blo"
	E confirmo o compromisso dando enter
	E comeco a adicionar um compromisso no 8o da lista
	E preencho o compromisso "Blu"
	E confirmo o compromisso dando enter
	Entao devo ver "Mauricio (Amil)"
	Entao devo ver "Marcelo (Amil)"
	Entao devo ver "Bla"
	Entao devo ver "Ble"
	Entao devo ver "Bli"
	Entao devo ver "Blo"
	Entao devo ver "Blu"
	Entao devo ver "Kablam"

@javascript
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
	E carrego os compromissos
	E comeco a adicionar um compromisso no 5o da lista
	E preencho e seleciono o compromisso "Mauricio"
	E confirmo o compromisso dando enter
	Entao devo ver "Mauricio (Amil)"
	Quando seleciono a agenda do doutor "Dr. Joaquim Grava"
	E carrego os compromissos
	E comeco a adicionar um compromisso no 5o da lista
	E preencho e seleciono o compromisso "Marcelo"
	E confirmo o compromisso dando enter
	Entao devo ver "Marcelo (Amil)"
	Quando seleciono a agenda do doutor "Dr. Roberto Aniche"
	E carrego os compromissos
	Entao devo ver "Mauricio (Amil)"

@javascript
Cenario: Deletar um compromisso
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	E que estou na tela de agenda
	Quando seleciono a agenda do doutor "Dr. Roberto Aniche"
	E na data "11/09/2011"
	E carrego os compromissos
	E comeco a adicionar um compromisso no 3o da lista
	E preencho e seleciono o compromisso "Mauricio"
	E confirmo o compromisso dando enter
	E removo o 3o compromisso
	E que estou na tela de agenda
	Quando seleciono a agenda do doutor "Dr. Roberto Aniche"
	E na data "11/09/2011"
	E carrego os compromissos
	Entao nao devo ver "Mauricio (Amil)"

@javascript
Cenario: Gravar horario de entrada um compromisso
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	E que estou na tela de agenda
	Quando seleciono a agenda do doutor "Dr. Roberto Aniche"
	E na data "11/09/2011"
	E carrego os compromissos
	E comeco a adicionar um compromisso no 3o da lista
	E preencho e seleciono o compromisso "Mauricio"
	E confirmo o compromisso dando enter
	E marco a hora de chegada como "09:45"
	E carrego os compromissos
	Entao devo ver "09:45"	

