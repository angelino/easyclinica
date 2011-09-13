Before do
  con = Mysql.connect(DB_HOST, DB_USER, DB_PWD, DB_SCHEMA)
  con.query('truncate table appointmentassistant')
  
  con.query('truncate table appointmentmaterial')
  con.query('truncate table appointmentmedicine')
  con.query('truncate table appointmentprocedure')
  con.query('truncate table appointment')

  con.query('truncate table anamnese')
  con.query('truncate table generalobservations')
  con.query('truncate table chatmessage')
  con.query('truncate table message')
  con.query('truncate table receipt')
  con.query('truncate table prescription')
  con.query('truncate table reply')
  con.query('truncate table schedule')

  con.query('truncate table doctor')
  con.query('truncate table patient')

  con.query('truncate table precifiedmaterial')
  con.query('truncate table precifiedspecialty')
  con.query('truncate table precifiedprocedure')
  con.query('truncate table precifiedmedicine')
    
  con.close
end
