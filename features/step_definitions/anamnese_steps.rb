Dado /^que uma anamnese no dia "([^"]*)" falando "([^"]*)" esta cadastrada$/ do |dia, texto|
    click_link "Anamnese"
    click_link "Nova anamnese"
    fill_in 'anamnese.date', :with => dia
    fill_in 'anamnese.text', :with => texto
    select 'Dr. Roberto',:from=>'anamnese.doctor.id'
    click_button 'Salvar'
end
