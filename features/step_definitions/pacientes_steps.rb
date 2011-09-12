Dado /^que um paciente "([^"]*)" padrao esta cadastrado$/ do |nome|
  visit path_to('tela de novo paciente')
  fill_in 'patient.name', :with => nome
  fill_in 'patient.telephone', :with => '(11) 1234-5678'
  fill_in 'patient.birthDate', :with => '10/10/1970'
  fill_in 'patient.cpf', :with => '330.591.998-17'
  click_button 'btnSalvar'
end

Dado /^que um paciente "([^"]*)" do convenio "([^"]*)" esta cadastrado$/ do |nome, convenio|
  visit path_to('tela de novo paciente')
  fill_in 'patient.name', :with => nome
  fill_in 'patient.telephone', :with => '(11) 1234-5678'
  fill_in 'patient.birthDate', :with => '10/10/1970'
  fill_in 'patient.cpf', :with => '330.591.998-17'
  select convenio,:from=>"patient.healthCarePlan.id"
  
  click_button 'btnSalvar'
end

Quando /^busco o paciente "([^"]*)"$/ do |nome|
  fill_in 'patient.textobusca', :with => nome
end

