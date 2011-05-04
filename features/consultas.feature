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
	Entao devo ver "R$ 338,50"
	E devo ver "R$ 250,00"
	E devo ver "R$ 0,00"
	E devo ver "R$ 588,50"
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
	Entao devo ver "R$ 485,40"
	E devo ver "R$ 250,00"
	E devo ver "R$ 735,40"
	
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
	Entao devo ver "R$ 1472,15"
	E devo ver "R$ 1722,15"

Cenario: Varios procedimentos
	Dado que estou logado como owner
	E que um paciente "Mauricio" padrao esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E adiciono o procedimento "procedimento 1"
	E adiciono o procedimento "procedimento 2"
	E adiciono o procedimento "procedimento 3"
	E adiciono o procedimento "procedimento 4"
	E adiciono o procedimento "procedimento 5"
	E salvo a consulta
	Entao devo ver "R$ 423,90"
	E devo ver "R$ 673,90"
	
Cenario: Remover material do procedimento
	Dado que estou logado como owner
	E que um paciente "Mauricio" padrao esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E adiciono o procedimento "procedimento 1"
	E excluo o 1o material do 1o procedimento
	E salvo a consulta
	Entao devo ver "R$ 238,50"
	E devo ver "R$ 488,50"
	
Cenario: Remover medicamento do procedimento
	Dado que estou logado como owner
	E que um paciente "Mauricio" padrao esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E adiciono o procedimento "procedimento 1"
	E excluo o 1o medicamento do 1o procedimento
	E salvo a consulta
	Entao devo ver "R$ 138,50"
	E devo ver "R$ 388,50"
	
Cenario: Remover procedimento
	Dado que estou logado como owner
	E que um paciente "Mauricio" padrao esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E adiciono o procedimento "procedimento 1"
	E adiciono o procedimento "procedimento 2"
	E excluo o 1o procedimento
	E salvo a consulta
	Entao devo ver "R$ 14,00"
	E devo ver "R$ 264,00"
	
Cenario: Outra especialidade
	Dado que estou logado como owner
	E que um paciente "Mauricio" padrao esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E seleciono a especialidade "Pediatria"
	E salvo a consulta
	Entao devo ver "R$ 100,00"
	E devo ver "Pediatria"
	E devo ver "Dr. Roberto Aniche"
	
# escrever teste para testar atualizacoes de valores

Cenario: Taxa de sala
	Dado que estou logado como owner
	E que um paciente "Mauricio" padrao esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E altero a taxa de sala para "112,45"
	E salvo a consulta
	Entao devo ver "R$ 112,45"
	E devo ver "R$ 362,45"

Cenario: Sugestao de taxa de sala
	Dado que estou logado como owner
	E que um paciente "Mauricio" padrao esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E adiciono o procedimento "procedimento 1"
	Entao devo ver "A taxa de sala sugerida para essa consulta é de R$ 10,00"
	
Cenario: Procedimentos e taxa de sala
	Dado que estou logado como owner
	E que um paciente "Mauricio" padrao esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E adiciono o procedimento "procedimento 1"
	E adiciono o procedimento "procedimento 2"
	E altero a taxa de sala para "12,34"
	E salvo a consulta
	Entao devo ver "R$ 352,50"
	E devo ver "R$ 614,84"

Cenario: Aviso de retorno
	Dado que estou logado como owner
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E salvo a consulta
	E clico no link "Nova consulta"
	E marco a consulta para o dia "20/10/2010"
	Entao devo ver "Essa consulta pode ser um retorno."
	
	