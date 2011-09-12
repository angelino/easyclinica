#language: pt

Funcionalidade: Consultas 

Cenario: Consulta com 1 procedimento simples cobrado pela CH
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que o procedimento "10014" custe "30" CHs
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E adiciono o procedimento "Em consult"
	E salvo a consulta
	E devo ver "R$ 4,50"
	Entao devo ver "Em consultório (no horário normal ou preestabelecido)"
	E devo ver "NÃO"
	E devo ver "ORTOPEDIA E TRAUMATOLOGIA"
	E devo ver "Dr. Roberto Aniche"
	E devo ver "10/10/2010"
