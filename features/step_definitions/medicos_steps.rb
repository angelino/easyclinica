Dado /^que um medico "([^"]*)" padrao esta cadastrado$/ do |nome|
  visit path_to('tela de novo medico')
  fill_in 'doctor.name', :with => nome
  fill_in 'doctor.crm', :with => '123.456'
  fill_in 'doctor.intervalBetweenAppointments', :with => '15'
  select 'ORTOPEDIA E TRAUMATOLOGIA',:from=>'doctor.specialty.id'
  click_button 'Salvar'
end

E /^busco o medico "([^"]*)"$/ do |medico|
  fill_in "doctor.textobusca", :with => medico
end