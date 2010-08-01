require 'spec_helper'

describe Convenio do
  before(:each) do
    @clinica = Clinica.new :login => 'teste', :nome => 'clinica'
    @clinica.save!
  end
  
  it 'deve validar dados obrigatorios' do
    amil = Convenio.new
    amil.should_not be_valid
    
    amil.nome = 'Amil'
    amil.telefone = '11 1234-5678'
    amil.clinica = @clinica
    amil.should be_valid
  end
  
  it 'deve ser inativo' do
    amil = Convenio.new
    amil.should be_ativo
    
    amil.inativa!
    amil.should_not be_ativo
  end
  
  it 'deve ser ativo' do
    amil = Convenio.new
    amil.inativa!
    amil.should_not be_ativo
    
    amil.ativa!
    amil.should be_ativo
  end
end
