require 'spec_helper'

describe Convenio do
  before(:each) do
    @clinica = Clinica.new :login => 'teste'
  end
  
  it 'deve validar dados obrigatorios' do
    amil = Convenio.new
    amil.should_not be_valid
    
    amil.nome = 'Amil'
    amil.telefone = '11 1234-5678'
    amil.clinica = @clinica
    amil.should be_valid
  end
end
