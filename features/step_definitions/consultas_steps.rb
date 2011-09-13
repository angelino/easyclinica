Quando /^adiciono o procedimento "(.*)"$/ do |procedimento|
  fill_in 'txt_search_procedure', :with => procedimento
  find("li:contains('#{procedimento}')").click
  click_link 'btn_search_procedure'
  sleep 2
end

Quando /^salvo a consulta$/ do
	click_button 'btnSalvar'
  page.execute_script("var e = jQuery.Event('click');$('#btnConfirmar').trigger(e);");
end

Quando /^altero o preco do (.*)o procedimento para "(.*)"$/ do |procedimento, valor|
	fill_in "appointment.procedures[#{procedimento.to_i - 1}].amount", :with => valor
end

Quando /^altero a quantidade do (.*)o material do (.*)o procedimento para "(.*)"$/ do |material, procedimento, valor|
	fill_in "appointment.procedures[#{procedimento.to_i - 1}].materials[#{material.to_i - 1}].qty", :with => valor
end

Quando /^altero o preco do (.*)o material do (.*)o procedimento para "(.*)"$/ do |material, procedimento, valor|
	fill_in "appointment.procedures[#{procedimento.to_i - 1}].materials[#{material.to_i - 1}].unitAmount", :with => valor
end

Quando /^altero a quantidade do ([0-9]*)o medicamento do ([0-9]*)o procedimento para "(.*)"$/ do |medicamento, procedimento, valor|
	fill_in "appointment.procedures[#{procedimento.to_i - 1}].medicines[#{medicamento.to_i - 1}].qty", :with => valor
end

Quando /^altero o preco do ([0-9]*)o medicamento do ([0-9]*)o procedimento para "(.*)"$/ do |medicamento, procedimento, valor|
	fill_in "appointment.procedures[#{procedimento.to_i - 1}].medicines[#{medicamento.to_i - 1}].unitAmount", :with => valor
end

Quando /^seleciono o doutor "(.*)"$/ do |medico|
	select medico,:from=>'appointment.doctor.id'
	sleep 1
end

Quando /^marco a consulta para o dia "([^"]*)"$/ do |data|
	fill_in 'appointment.appointmentDate', :with => data
end

Quando /^altero a taxa de sala para "(.*)"$/ do |valor|
	fill_in 'appointment.roomRateAmount', :with => valor
end

Quando /^excluo o ([0-9]*)o material do ([0-9]*)o procedimento$/ do |material, procedimento|
	accept_js_confirm
	click_link "lnkDeleteMaterial#{material.to_i-1}Procedure#{procedimento.to_i-1}"
end

Quando /^excluo o ([0-9]*)o medicamento do ([0-9]*)o procedimento$/ do |medicamento, procedimento|
	accept_js_confirm
	click_link "lnkDeleteMedicine#{medicamento.to_i-1}Procedure#{procedimento.to_i-1}"
end

Quando /^excluo o ([0-9]*)o procedimento$/ do |procedimento|
	accept_js_confirm
	click_link "lnkDeleteProcedure#{procedimento.to_i - 1}"
end

Quando /^seleciono a especialidade "([^"]*)"$/ do |especialidade|
  select especialidade,:from=>'appointment.specialty.id'
  sleep 2
end