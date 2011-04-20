require 'capybara'
require 'capybara/dsl'
require 'capybara/cucumber'

Capybara.default_driver = :selenium
Capybara.app_host = 'http://homologacao.easyclinica.com.br'
Capybara.register_driver :selenium do |app|
  Capybara::Driver::Selenium.new(app, :browser => :firefox)

# This way we are using Selenium-RC
#  Capybara::Driver::Selenium.new(app,
#                                 :browser => :remote,
#                                 :url => "http://127.0.0.1:4444/wd/hub",
#                                 :desired_capabilities => :internet_explorer)

end

World(Capybara)

# requer usuario homolog senha 123
# requer medico Dr. Roberto