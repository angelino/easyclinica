class ExcecoesdemateriaisController < ApplicationController
  
  def index 
    @convenio = Convenio.da(@clinica).find(params[:convenio_id]) 
    @excecao = Excecaodematerial.new
  end
  
  def create
    @convenio = Convenio.da(@clinica).find(params[:convenio_id])
    @excecao = @convenio.excecoesdemateriais.build params[:excecaodematerial]

    if @excecao.save then
      flash[:aviso] = 'Exceção de material criada com sucesso!'
    end
    
    render :action => 'index'
	end
	
  def destroy
    @convenio = Convenio.da(@clinica).find(params[:convenio_id])
    @excecao = Excecaodematerial.do(@convenio).find(params[:id])
    
    if @excecao.destroy
      flash[:aviso] = 'Exceção excluída com sucesso.'
    else
      flash[:aviso] = 'Ocorreu um problema ao excluir a exceção. Por favor, tente novamente!'
    end

    respond_to do |resposta|
      resposta.html { redirect_to :action => 'index', :controller => 'excecoesdemateriais' }
    end
  end
  
end
