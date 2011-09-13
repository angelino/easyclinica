E /^que o procedimento "([^"]*)" custe "([^"]*)" CHs$/ do |codigo, valor|
  sql = "insert into precifiedprocedure (ch, fixedamount, roomTaxAmount, healthCarePlan_id, procedure_id) select #{valor}, 0, 0, id, (select id from medical_procedures where ambCode = '#{codigo}') from healthcareplan"
  con = Mysql.connect(DB_HOST, DB_USER, DB_PWD, DB_SCHEMA)
  con.query(sql)
  con.close
end
