require 'lib/config'

class ConveniosController < ApplicationController

  def index 
  	@convenios = Convenio.da(@clinica).ordenado_por_ativo.paginate :page => params[:page],  :per_page => Config::QTD_POR_PAGINA
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

  def new
    @convenio = Convenio.new 
  end

  def create
    @convenio = @clinica.convenios.build params[:convenio]

    respond_to do |resposta|
    	if @convenio.save 
    		flash[:aviso] = 'Convênio criado com sucesso.' 
    		resposta.html { redirect_to(@convenio) } 
    	else 
  			resposta.html { render :action => 'new' }
			end
		end
	end

  def destroy
    @convenio = Convenio.da(@clinica).find(params[:id])
    @convenio.inativa!
    
    if @convenio.save
      flash[:aviso] = 'Convênio inativado com sucesso.'
    else
      flash[:aviso] = 'Ocorreu um problema ao inativar o convênio. Por favor, tente novamente!'
    end

    respond_to do |resposta|
      resposta.html { redirect_to :action => 'index', :controller => 'convenios' }
    end
  end

end
