Dado /^que um convenio "([^"]*)" padrao esta cadastrado$/ do |nome|
  visit path_to('tela de novo convenio')
  fill_in 'healthCarePlan.name', :with => nome
  fill_in 'healthCarePlan.telephone', :with => '(11) 1234-5678'
  fill_in 'healthCarePlan.ch', :with => '0,15'
  click_button 'Salvar'
end