require 'spec_helper'

describe Excecaodematerial do
  before(:each) do 
    @tabela = Tabela.new :nome => 'tabela'; @tabela.save!
    @material = Material.new :nome => 'nome', :ch => 10, :codigo => '123', :tabela => @tabela; @material.save!

    @convenio = Convenio.new :nome => 'nome', :telefone => '123', :tabela => @tabela ; @convenio.save!
  end
  
  it 'deve ser valido' do
    excecao = Excecaodematerial.new
    excecao.should_not be_valid
    
    excecao.material = @material
    excecao.convenio = @convenio
    excecao.valor_ch = 0.1
    
    excecao.should be_valid
  end
  
  it 'nao deve repetir no mesmo convenio' do
    excecao = Excecaodematerial.new :convenio => @convenio, :material => @material
    excecao.save.should == true
    
    excecao_igual = Excecaodematerial.new :convenio => @convenio, :material => @material
    excecao_igual.save.should == false
  end
end
