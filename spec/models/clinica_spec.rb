require 'spec_helper'

describe Clinica do
  before(:each) do
    
  end
  
  it 'deve validar dados obrigatorios' do
    clinica = Clinica.new
    clinica.should_not be_valid
    
    clinica.nome = 'nome da cl�nica'
    clinica.login = 'login'
    clinica.should be_valid
  end
  
  it 'n�o deve permitir o cadastro de dois Logins iguais' do
  	clinica1 = Clinica.new :nome => 'cl�nica 1', :login => 'login'
    clinica1.save.should == true
    
    clinica2 = Clinica.new :nome => 'cl�nica 2', :login => 'login'
    clinica2.save.should == false    
  end
end
