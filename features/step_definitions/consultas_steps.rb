Quando /^adiciono o procedimento "(.*)"$/ do |procedimento|
  fill_in 'txt_search_procedure', :with => procedimento
  find("li:contains('#{procedimento}')").click
  click_link 'btn_search_procedure'
  sleep 2
end

Quando /^salvo a consulta$/ do
	click_button 'btnSalvar'
end

Quando /^altero a quantidade do (.*)o material do (.*)o procedimento para "(.*)"$/ do |material, procedimento, valor|
	fill_in "appointment.procedures[#{procedimento.to_i - 1}].materials[#{material.to_i - 1}].qty", :with => valor
end

Quando /^altero o preco do (.*)o material do (.*)o procedimento para "(.*)"$/ do |material, procedimento, valor|
	fill_in "appointment.procedures[#{procedimento.to_i - 1}].materials[#{material.to_i - 1}].unitAmount", :with => valor
end
