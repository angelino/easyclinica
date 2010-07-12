class ConveniosController < ApplicationController
  
  def index
    @convenios = Convenio.da(@clinica).find(:all)
  end
  
  def edit
    @convenio = Convenio.da(@clinica).find(params[:id])
  end
end
