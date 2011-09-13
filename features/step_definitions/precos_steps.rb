E /^que o procedimento "([^"]*)" custe "([^"]*)" CHs$/ do |codigo, valor|
  sql = "insert into precifiedprocedure (ch, fixedamount, roomTaxAmount, healthCarePlan_id, procedure_id) select #{valor}, 0, 0, id, (select id from medical_procedures where ambCode = '#{codigo}') from healthcareplan"
  con = Mysql.connect(DB_HOST, DB_USER, DB_PWD, DB_SCHEMA)
  con.query(sql)
  con.close
end

E /^que a especialidade "([^"]*)" custe R\$ "([^"]*)"$/ do |nome, valor|
  sql = "insert into precifiedspecialty (amount, healthCarePlan_id, specialty_id) select #{valor}, id, (select id from specialty where name = '#{nome}') from healthcareplan"
  con = Mysql.connect(DB_HOST, DB_USER, DB_PWD, DB_SCHEMA)
  con.query(sql)
  con.close

end

E /^que o procedimento "([^"]*)" custe "([^"]*)" CHs e tenha "([^"]*)" de taxa de sala$/ do |codigo, valor, taxa|
  sql = "insert into precifiedprocedure (ch, fixedamount, roomTaxAmount, healthCarePlan_id, procedure_id) select #{valor}, 0, #{taxa}, id, (select id from medical_procedures where ambCode = '#{codigo}') from healthcareplan"
  con = Mysql.connect(DB_HOST, DB_USER, DB_PWD, DB_SCHEMA)
  con.query(sql)
  con.close
end
