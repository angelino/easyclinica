require 'spec_helper'

describe MedicosController do

	before(:each) do
    @clinica = Clinica.new :nome => 'clinica', :login => 'www' ; @clinica.save!
    Clinica.should_receive(:find_by_login!).with(nil).and_return(@clinica)

    @medico1 = Medico.new :nome => 'Doutor 1', :crm => '55.555', :email => 'medico1@email.com.br', :clinica => @clinica ; @medico1.save!
    @medico2 = Medico.new :nome => 'Doutor 2', :crm => '66.666', :email => 'medico2@email.com.br', :clinica => @clinica ; @medico2.save!
  end
  
  context 'no momento da exibicao' do
    it 'deve retornar a lista de medicos' do
      get :index
    
      assigns(:medicos).size.should == 2
    end

    it 'deve exibir o médico' do
      get :show, :id => @medico1.id.to_s
    
      assigns(:medico).should == @medico1
    end
  end

  context 'no momento da atualizacao' do  
    it 'deve retornar o médico passado para edicao' do
      get :edit, :id => @medico1.id.to_s
    
      assigns(:medico).should == @medico1
    end

    it 'deve atualizar caso os dados sejam validos' do
      put :update, :id => @medico1.id, :medico => {:nome => 'médico 1 alterado'}
    
      @medico1.reload
      @medico1.nome.should == 'médico 1 alterado'
    end
  
    it 'deve exibir erros de validacao caso os dados sejam invalidos' do
      put :update, :id => @medico1.id, :medico => {:crm => '66.666'}
    
      response.should render_template(:edit)
    end
  end
  
  context 'no momento da criacao de um novo médico' do
    it 'deve salvar caso os dados sejam validos' do
      post :create, :medico => { :nome => 'novo médico', :crm => '77.777', :email => 'medico3@email.com.br'}
      
      novo_medico = Convenio.find_by_nome('novo médico')
      novo_medico.nome.should == 'novo médico'
      novo_medico.clinica.should == @clinica
    end
    
    it 'deve voltar para tela de criacao caso os dados sejam invalidos' do
      post :create, :medico => { :nome => '', :crm => '66.666' }
      
      response.should render_template(:new)
    end
  end
  
  context 'quando um médico é inativado' do
    it 'ele deve ter seu status alterado' do
      delete :destroy, :id => @medico1.id
      
      @amil.reload
      @amil.should_not be_ativo
    end
    
    it 'ele deve retornar erro caso a operacao falhe' do
      medico_com_problema = mock("Medico")
      medico_com_problema.should_receive(:inativa!)
      medico_com_problema.should_receive(:save).and_return(false)
      Medico.stub_chain(:da, :find).and_return(medico_com_problema)
      
      delete :destroy, :id => '999'
      
      flash.now[:aviso].include?('problema').should == true
    end
  end

end