require 'spec_helper'

describe Medico do
  before(:each) do
    @clinica = Clinica.new :login => 'teste', :nome => 'clinica'
    @clinica.save!
  end
  
  it 'deve validar dados obrigatorios' do
    doutor = Medico.new
    doutor.should_not be_valid
    
    doutor.nome = 'nome do médico'
    doutor.crm = '55.555'
    doutor.email = 'doutor@email.com.br'
    doutor.clinica = @clinica
    doutor.should be_valid
  end
  
  it 'deve ser inativo' do
    doutor = Medico.new
    doutor.should be_ativo
    
    doutor.inativa!
    doutor.should_not be_ativo
  end
  
  it 'deve ser ativo' do
    doutor = Medico.new
    doutor.inativa!
    doutor.should_not be_ativo
    
    doutor.ativa!
    doutor.should be_ativo
  end
  
  it 'não deve permitir o cadastro de dois CRMs iguais' do
  	doutor1 = Medico.new :nome => 'nome do medico 1', :crm => '55.555', :email => 'doutor1@email.com.br', :clinica => @clinica
    doutor1.save.should == true
    
    doutor2 = Medico.new :nome => 'nome do medico 2', :crm => '55.555', :email => 'doutor2@email.com.br', :clinica => @clinica
    doutor2.save.should == false
    
  end
  
end
