require 'spec_helper'

describe ExcecoesdeservicosController do
  before(:each) do
    @clinica = Clinica.new :nome => 'clinica', :login => 'clinica' ; @clinica.save!
    @tabela = Tabela.new :nome => 'tabela'; @tabela.save!
    @servico = Servico.new :nome => 's1', :ch => 10, :codigo => 'c1', :tabela => @tabela; @servico.save!
    @convenio = Convenio.new :nome => 'convenio', :telefone => '1234', :clinica => @clinica, :tabela => @tabela; @convenio.save!
    
    @request.host = "clinica.easyclinica.com.br"
  end
  
  context 'no momento da criacao de uma nova excecao' do
    it 'deve salvar caso os dados sejam validos' do
      post :create, :convenio_id => @convenio.id, :excecaodeservico => { :servico => @servico, :valor_ch => 10, :valor_bruto => 0 }
    
      nova_excecao = Excecaodeservico.all[0]
      nova_excecao.servico.should == @servico
      nova_excecao.convenio.should == @convenio
      nova_excecao.valor_ch.should == 10
      nova_excecao.valor_bruto.should == 0
      
      flash[:aviso].should contain('sucesso')
    end
    
    it 'deve retornar erro caso a validacao falhe' do
      post :create, :convenio_id => @convenio.id, :excecaodeservico => { :valor_ch => 10, :valor_bruto => 0 }
    
      assigns(:excecao).errors.size.should > 0
    end
    
  end
  
  context 'quando uma excecao eh excluida' do
    it 'eh deletado caso a delecao ocorra com sucesso' do
      excecao = Excecaodeservico.new :valor_ch => 10, :servico => @servico, :convenio => @convenio
      excecao.save.should == true
      
      delete :destroy, :id => excecao.id, :convenio_id => @convenio.id
      
      Excecaodeservico.find(:all, :conditions => {:id => excecao.id }).size.should == 0
    end
    
    it 'ele deve retornar erro caso a operacao falhe' do
      excecao_com_problema = mock("Excecao")
      excecao_com_problema.should_receive(:destroy).and_return(false)
      Excecaodeservico.stub_chain(:do, :find).and_return(excecao_com_problema)
      
      delete :destroy, :id => '999', :convenio_id => @convenio.id
      
      flash.now[:aviso].include?('problema').should == true
    end
  end
end
