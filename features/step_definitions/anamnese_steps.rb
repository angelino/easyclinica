Dado /^que uma anamnese no dia "([^"]*)" falando "([^"]*)" esta cadastrada$/ do |dia, texto|
    click_link "Anamneses"
    fill_in 'anamnese.date', :with => dia
    fill_in 'anamnese.text', :with => texto
    click_button 'Salvar'
end
