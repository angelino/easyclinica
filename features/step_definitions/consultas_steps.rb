Quando /^adiciono o procedimento "(.*)"$/ do |procedimento|
  fill_in 'txt_search_procedure', :with => procedimento
  find("li:contains('#{procedimento}')").click
  click_link 'btn_search_procedure'
  sleep 2
end

Quando /^salvo a consulta$/ do
	click_button 'btnSalvar'
end
