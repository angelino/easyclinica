#language: pt

Funcionalidade: Consultas 

@javascript
Cenario: Consulta com 1 procedimento simples cobrado pela CH
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que o procedimento "10014" custe "30" CHs
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E adiciono o procedimento "ou preestabelecido"
	E salvo a consulta
	E devo ver "R$ 4,50"
	Entao devo ver "ou preestabelecido"
	E devo ver "NÃO"
	E devo ver "ORTOPEDIA E TRAUMATOLOGIA"
	E devo ver "Dr. Roberto Aniche"
	E devo ver "10/10/2010"

@javascript
Cenario: Consulta com 1 procedimento com valor alterado 
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que a especialidade "ORTOPEDIA E TRAUMATOLOGIA" custe R$ "30.00"
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E adiciono o procedimento "ou preestabelecido"
	E altero o preco do 1o procedimento para "246,90"
	E salvo a consulta
	Entao devo ver "R$ 246,90"
	E devo ver "R$ 30,00"
	E devo ver "R$ 276,90"

@javascript
Cenario: Varios procedimentos
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E adiciono o procedimento "ou preestabelecido"
	E altero o preco do 1o procedimento para "2,00"
	E adiciono o procedimento "Abdome agudo"
	E altero o preco do 2o procedimento para "4,00"
	E adiciono o procedimento "Coloboma - com pl"
	E altero o preco do 3o procedimento para "8,00"
	E adiciono o procedimento "Vasectomia unilateral"
	E altero o preco do 4o procedimento para "16,00"
	E adiciono o procedimento "Curativo de ouvido (cada)"
	E altero o preco do 5o procedimento para "32,00"
	E salvo a consulta
	Entao devo ver "R$ 62,00"

@javascript
Cenario: Remover procedimento
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E adiciono o procedimento "ou preestabelecido"
	E adiciono o procedimento "Curativo de ouvido (cada)"
	E excluo o 1o procedimento
	E altero o preco do 2o procedimento para "212,45"
	E salvo a consulta
	Entao devo ver "R$ 212,45"

@javascript
Cenario: Outra especialidade
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que a especialidade "ORTOPEDIA E TRAUMATOLOGIA" custe R$ "30.00"
	E que a especialidade "PEDIATRIA" custe R$ "150.00"
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E seleciono a especialidade "PEDIATRIA"
	E salvo a consulta
	Entao devo ver "R$ 150,00"
	E devo ver "PEDIATRIA"
	E devo ver "Dr. Roberto Aniche"

@javascript
Cenario: Taxa de sala
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que a especialidade "ORTOPEDIA E TRAUMATOLOGIA" custe R$ "30.00"
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E altero a taxa de sala para "112,45"
	E salvo a consulta
	Entao devo ver "R$ 112,45"
	E devo ver "R$ 30,00"
	E devo ver "R$ 142,45"

@javascript
Cenario: Sugestao de taxa de sala
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que a especialidade "ORTOPEDIA E TRAUMATOLOGIA" custe R$ "30.00"
	E que o procedimento "10014" custe "30" CHs e tenha "10.00" de taxa de sala
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E adiciono o procedimento "ou preestabelecido"
	Entao devo ver "A taxa de sala sugerida para essa consulta é de R$ 10,00"

@javascript
Cenario: Aviso de retorno
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E salvo a consulta
	E clico no link "Nova consulta"
	E marco a consulta para o dia "20/10/2010"
	Entao devo ver "Essa consulta pode ser um retorno."

@javascript
Cenario: Procedimentos e taxa de sala
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que a especialidade "ORTOPEDIA E TRAUMATOLOGIA" custe R$ "100.00"
	E que o procedimento "10014" custe "30" CHs
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E adiciono o procedimento "ou preestabelecido"
	E adiciono o procedimento "Curativo de ouvido (cada)"
	E altero o preco do 2o procedimento para "45,67"
	E altero a taxa de sala para "12,34"
	E salvo a consulta
	Entao devo ver "R$ 12,34"
	E devo ver "R$ 162,51"

@javascript @bla
Cenario: Material e procedimento adicionados
	Dado que estou logado como owner
	E que um convenio "Amil" padrao esta cadastrado
	E que um medico "Dr. Roberto Aniche" padrao esta cadastrado
	E que um paciente "Mauricio" do convenio "Amil" esta cadastrado
	Quando clico no link "Nova consulta"
	E marco a consulta para o dia "10/10/2010"
	E seleciono o doutor "Dr. Roberto Aniche"
	E adiciono o procedimento "ou preestabelecido"
	E adiciono o remedio "PROZAC CX. 2 BLIS. X 7 CAPS." no 1o procedimento
	E adiciono o material "Gaze" no 1o procedimento
	E altero a quantidade do 1o material do 1o procedimento para "2,0"
	E altero o preco do 1o material do 1o procedimento para "123,45"
	E altero a quantidade do 1o medicamento do 1o procedimento para "3,0"
	E altero o preco do 1o medicamento do 1o procedimento para "78,90"
	E salvo a consulta
	Entao devo ver "R$ 483,60"
	E devo ver "R$ 246,90"
	E devo ver "R$ 236,70"
	
# cenario: mudar todos valores e ver atualizacao
