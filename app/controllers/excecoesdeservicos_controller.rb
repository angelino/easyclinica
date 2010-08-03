class ExcecoesdeservicosController < ApplicationController

  def index 
    @convenio = Convenio.da(@clinica).find(params[:convenio_id]) 
    @excecao = Excecaodeservico.new
  end
  
  def create
    @convenio = Convenio.da(@clinica).find(params[:convenio_id])
    @excecao = @convenio.excecoesdeservicos.build params[:excecaodeservico]

    if @excecao.save then
      flash[:aviso] = 'Exceção de serviço criada com sucesso!'
    end
    
    render :action => 'index'
	end
	
  def destroy
    @convenio = Convenio.da(@clinica).find(params[:convenio_id])
    @excecao = Excecaodeservico.do(@convenio).find(params[:id])
    
    if @excecao.destroy
      flash[:aviso] = 'Exceção excluída com sucesso.'
    else
      flash[:aviso] = 'Ocorreu um problema ao excluir a exceção. Por favor, tente novamente!'
    end

    respond_to do |resposta|
      resposta.html { redirect_to :action => 'index', :controller => 'excecoesdeservicos' }
    end
  end
	
end
