class ConveniosController < ApplicationController
  
  def index
    @convenios = Convenio.da(@clinica).find(:all)
  end
  
  def edit
    @convenio = Convenio.da(@clinica).find(params[:id])
  end
  
  def show
    @convenio = Convenio.da(@clinica).find(params[:id])
  end
  
  def update
    @convenio = Convenio.da(@clinica).find(params[:id])
    
    respond_to do |resposta|
      if @convenio.update_attributes(params[:convenio])
        flash[:aviso] = 'Convênio alterado com sucesso.'
        resposta.html { redirect_to(@convenio) }
      else
        resposta.html { render :action => 'edit' }
      end
    end
  end

end
