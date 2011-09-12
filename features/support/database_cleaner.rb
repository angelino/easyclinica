Before do
  con = Mysql.connect(DB_HOST, DB_USER, DB_PWD, DB_SCHEMA)
  con.query('delete from appointmentassistant')
  
  con.query('delete from appointmentmaterial')
  con.query('delete from appointmentmedicine')
  con.query('delete from appointmentprocedure')
  con.query('delete from appointment')

  con.query('delete from anamnese')
  con.query('delete from generalobservations')
  con.query('delete from chatmessage')
  con.query('delete from message')
  con.query('delete from receipt')
  con.query('delete from prescription')
  con.query('delete from reply')
  con.query('delete from schedule')

  con.query('delete from doctor')
  con.query('delete from patient')

  con.query('delete from precifiedmaterial')
  con.query('delete from precifiedspecialty')
  con.query('delete from precifiedprocedure')
  con.query('delete from precifiedmedicine')
    
  con.close
end
