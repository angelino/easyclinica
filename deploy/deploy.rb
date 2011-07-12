DOMAIN = "demonstracao"
HOST = "ec2-174-129-9-255.compute-1.amazonaws.com"

p "criando tabelas"
command = "mysql -u ec-#{DOMAIN} -p3@sycl1n1c@_rtf -h #{HOST} ec-#{DOMAIN} < 01-tabelas.sql"
system(command)

p "criando plano particular"
command = "mysql -u ec-#{DOMAIN} -p3@sycl1n1c@_rtf -h #{HOST} ec-#{DOMAIN} < 02-particular.sql"
system(command)

p "criando dados da clinica"
command = "mysql -u ec-#{DOMAIN} -p3@sycl1n1c@_rtf -h #{HOST} ec-#{DOMAIN} < 03-clinica.sql"
system(command)

p "criando usuario owner"
command = "mysql -u ec-#{DOMAIN} -p3@sycl1n1c@_rtf -h #{HOST} ec-#{DOMAIN} < 04-usuario.sql"
system(command)

p "criando especialidades"
command = "mysql -u ec-#{DOMAIN} -p3@sycl1n1c@_rtf -h #{HOST} ec-#{DOMAIN} < 05-especialidades.sql"
system(command)

p "criando cid"
command = "mysql -u ec-#{DOMAIN} -p3@sycl1n1c@_rtf -h #{HOST} ec-#{DOMAIN} < 06-cid.sql"
system(command)

p "criando materiais"
command = "mysql -u ec-#{DOMAIN} -p3@sycl1n1c@_rtf -h #{HOST} ec-#{DOMAIN} < 07-materiais.sql"
system(command)

p "criando remedios"
command = "mysql -u ec-#{DOMAIN} -p3@sycl1n1c@_rtf -h #{HOST} ec-#{DOMAIN} < 08-remedios.sql"
system(command)

p "criando procedimentos"
command = "mysql -u ec-#{DOMAIN} -p3@sycl1n1c@_rtf -h #{HOST} ec-#{DOMAIN} < 09-procedimentos.sql"
system(command)
