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
  locate("li:contains('#{compromisso}')").click
end

E /^preencho o compromisso "([^"]*)"$/ do |compromisso|
  fill_in "schedule_subject_#{@posicao}", :with => compromisso
end

E /^removo o (\d+)o compromisso$/ do |numero|
  accept_js_confirm  
  click_link "delete_#{numero}_1"
end

E /^marco a hora de chegada como "([^"]*)"$/ do |hora|
  fill_in "arrivalTime_#{@posicao}_1", :with => hora
  click_link "save_arrivalTime_#{@posicao}_1"
end