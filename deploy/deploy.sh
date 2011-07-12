DOMAIN = "demonstracao"
HOST = ""

p "criando tabelas"
command = "mysql -u ec-#{DOMAIN} -p3@sycl1n1c@_rtf -h #{HOST} ec-#{DOMAIN} < 01-tabelas.sql"

p 


