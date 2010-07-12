require 'spec_helper'

describe ConveniosController do
  
  before(:each) do
    @clinica = Clinica.new :nome => 'clinica', :login => 'www' ; @clinica.save!
    Clinica.should_receive(:find_by_login!).with(nil).and_return(@clinica)

    @amil = Convenio.new :nome => 'amil', :telefone => '1234', :clinica => @clinica ; @amil.save!
    @blue_life = Convenio.new :nome => 'blue life', :telefone => '1234', :clinica => @clinica ; @blue_life.save!
  end
  
  it 'deve retornar a lista de convenios' do
    get :index
    
    assigns(:convenios).size.should == 2
  end
  
  it 'deve retornar o convenio passado para edicao' do
    get :edit, :id => @amil.id.to_s
    
    assigns(:convenio).should == @amil
  end

  it 'deve exibir o convenio' do
    get :show, :id => @amil.id.to_s
    
    assigns(:convenio).should == @amil
  end
  
  context 'no momento da atualizacao' do
    it 'deve atualizar caso os dados sejam validos' do
      put :update, :id => @amil.id, :convenio => {:nome => 'amil alterado'}
    
      @amil.reload
      @amil.nome.should == 'amil alterado'
    end
  
    it 'deve exibir erros de validacao caso os dados sejam invalidos' do
      put :update, :id => @amil.id, :convenio => {:nome => ''}
    
      response.should render_template(:edit)
    end
  end
end
