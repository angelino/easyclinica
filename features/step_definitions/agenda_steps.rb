Quando /^seleciono a agenda do doutor "([^"]*)"$/ do |medico|
  select medico, :from => 'schedule.doctor.id'
end

Quando /^na data "([^"]*)"$/ do |data|
  fill_in 'schedule.startTime', :with => data
end

Quando /^comeco a adicionar um compromisso no (\d+)o da lista$/ do |posicao|
  click_link "add_event_#{posicao}"
  @posicao = posicao
end

E /^preencho e seleciono o compromisso "([^"]*)"$/ do |compromisso|
  fill_in "schedule_subject_#{@posicao}", :with => compromisso
  sleep 1
  find("li:contains('#{compromisso}')").click
end

E /^preencho o compromisso "([^"]*)"$/ do |compromisso|
  fill_in "schedule_subject_#{@posicao}", :with => compromisso
  sleep 1
end

E /^removo o (\d+)o compromisso$/ do |numero|
  accept_js_confirm  
  click_link "delete_#{numero}_1"
  sleep 2
end

E /^marco a hora de chegada como "([^"]*)"$/ do |hora|
  fill_in "arrivalTime_#{@posicao}_1", :with => hora
  click_link "save_arrivalTime_#{@posicao}_1"
end

E /^confirmo o compromisso dando enter$/ do
  page.execute_script("var e = jQuery.Event('keyup');e.keyCode = 13;$('#schedule_subject_#{@posicao}').trigger(e);");
  sleep 2
end

E /^carrego os compromissos$/ do
  click_button 'Carregar Compromissos'
  sleep 2
end
