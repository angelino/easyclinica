#language: pt

Funcionalidade: Consultas 

Cenario: Consulta com 1 procedimento 
	Dado que estou logado como owner
	E que um paciente "Mauricio" padrao esta cadastrado
	Quando clico no link "Nova consulta"
	E preencho "10/10/2010" em "appointment.appointmentDate"
	E seleciono "Dr. Roberto Aniche" em "appointment.doctor.id"
	E adiciono o procedimento "procedimento 1"
	E salvo a consulta
	Entao devo ver "R$ 800,00"
	E devo ver "R$ 250,00"
	E devo ver "R$ 0,00"
	E devo ver "R$ 1050,00"
	E devo ver "procedimento 1"
	E devo ver "material 1"
	E devo ver "remedio 1"
	E devo ver "NÃO"
	E devo ver "Ortopedia"
	E devo ver "Dr. Roberto Aniche"
	E devo ver "10/10/2010"
	
	
