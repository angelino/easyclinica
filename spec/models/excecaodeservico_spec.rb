require 'spec_helper'

describe Excecaodeservico do
  before(:each) do 
    @tabela = Tabela.new :nome => 'tabela'; @tabela.save!
    @servico = Servico.new :nome => 'nome', :ch => 10, :codigo => '123', :tabela => @tabela; @servico.save!

    @convenio = Convenio.new :nome => 'nome', :telefone => '123', :tabela => @tabela ; @convenio.save!
  end
  
  it 'deve ser valido' do
    excecao = Excecaodeservico.new
    excecao.should_not be_valid
    
    excecao.servico = @servico
    excecao.convenio = @convenio
    excecao.valor_ch = 0.1
    
    excecao.should be_valid
  end
  
  it 'nao deve repetir no mesmo convenio' do
    excecao = Excecaodeservico.new :convenio => @convenio, :servico => @servico
    excecao.save.should == true
    
    excecao_igual = Excecaodeservico.new :convenio => @convenio, :servico => @servico
    excecao_igual.save.should == false
  end
end
