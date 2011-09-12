Dado /^que estou logado como owner$/ do
  visit path_to('tela de login')
  fill_in 'login', :with => 'homolog'
  fill_in 'password', :with => '1234'
  click_button('btnlogin');  
end
