#language: pt

Funcionalidade: Consultas 

Cenario: Consulta com 1 procedimento 
	Dado que estou logado como owner
	E que um paciente "Mauricio" padrao esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
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
	
Cenario: Consulta com 1 procedimento com valor de material alterado 
	Dado que estou logado como owner
	E que um paciente "Mauricio" padrao esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E adiciono o procedimento "procedimento 1"
	E altero a quantidade do 1o material do 1o procedimento para "2,0"
	E altero o preco do 1o material do 1o procedimento para "123,45"
	E salvo a consulta
	Entao devo ver "R$ 946,90"
	E devo ver "R$ 1196,90"
	E devo ver "R$ 246,90"
	
Cenario: Consulta com 1 procedimento com valor de medicamento alterado 
	Dado que estou logado como owner
	E que um paciente "Mauricio" padrao esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E adiciono o procedimento "procedimento 1"
	E altero a quantidade do 1o medicamento do 1o procedimento para "3,0"
	E altero o preco do 1o medicamento do 1o procedimento para "444,55"
	E salvo a consulta
	Entao devo ver "R$ 2183,65"
	E devo ver "R$ 1933,65"
	E devo ver "R$ 1333,65"
	
	