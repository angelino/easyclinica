require 'spec_helper'

describe Tabela do
  it 'contem servicos' do
    tabela = Tabela.new :nome => 'tabela'
    tabela.servicos.build :nome => 'servico', :ch => '10', :codigo => '123'
    
    tabela.save!.should == true
    tabela.reload
    
    tabela.servicos[0].nome.should == 'servico'
    tabela.servicos[0].ch.should == 10
    tabela.servicos[0].codigo.should == '123'    
  end
  
  it 'contem materiais' do
    tabela = Tabela.new :nome => 'tabela'
    tabela.materiais.build :nome => 'material', :ch => '10', :codigo => '123'
    
    tabela.save!.should == true
    tabela.reload
    
    tabela.materiais[0].nome.should == 'material'
    tabela.materiais[0].ch.should == 10
    tabela.materiais[0].codigo.should == '123'    
  end
end
