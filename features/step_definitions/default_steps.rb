Dado /^que estou na (.*)$/ do |pagina|
  visit path_to(pagina)
end

E /^preencho "(.*)" em "(.*)"$/ do |valor, campo|
  fill_in campo,:with=>valor
end

Quando /^pressiono "(.*)"$/ do |botao|
  click_button botao
end

Entao /^devo ver "(.*)"$/ do |texto|
  body.should match(/#{texto}/m)  
end
