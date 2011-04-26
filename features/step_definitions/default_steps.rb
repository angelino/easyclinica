Dado /^que estou na (.*)$/ do |pagina|
  visit path_to(pagina)
end

E /^preencho "(.*)" em "(.*)"$/ do |valor, campo|
  fill_in campo,:with=>valor
end

E /^seleciono "(.*)" em "(.*)"$/ do |valor, campo|
  select valor,:from=>campo
end

Quando /^pressiono "(.*)"$/ do |botao|
  click_button botao
end

Quando /^clico no link "(.*)"$/ do |link|
  click_link link
end

Entao /^devo ver "(.*)"$/ do |texto|
  page.should have_content(texto)
end

E /^seleciono "(.*)" no autocomplete "(.*)"$/ do |opcao, campo|
  fill_in campo, :with => opcao
  locate("li:contains('#{opcao}')").click
end
