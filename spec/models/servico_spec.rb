require 'spec_helper'

describe Servico do
  it 'deve ser valido' do
    servico = Servico.new
    servico.should_not be_valid
    
    servico.nome = 'servico'
    servico.ch = 10
    servico.codigo = '123'
    servico.should be_valid
  end
end
