Dado /^que um medico "([^"]*)" padrao esta cadastrado$/ do |nome|
  visit path_to('tela de novo medico')
  fill_in 'doctor.name', :with => nome
  fill_in 'doctor.crm', :with => '123.456'
  select 'Ortopedia',:from=>'doctor.specialty.id'
  click_button 'Salvar'
end
