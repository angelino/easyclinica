require 'lib/config'

class MedicosController < ApplicationController

	def index
    @medicos = Medico.da(@clinica).all(:order => "ativo DESC").paginate :page => params[:page], :per_page => Config::QTD_POR_PAGINA
  end
  
  def edit
    @medico = Medico.da(@clinica).find(params[:id])
  end
  
  def show
    @medico = Medico.da(@clinica).find(params[:id])
  end
  
  def update
    @medico = Medico.da(@clinica).find(params[:id])
    
    respond_to do |resposta|
      if @medico.update_attributes(params[:medico])
        flash[:aviso] = 'Médico alterado com sucesso.'
        resposta.html { redirect_to(@medico) }
      else
        resposta.html { render :action => 'edit' }
      end
    end
  end
  
  def new
    @medico = Medico.new
  end
  
  def create
    @medico = @clinica.medicos.build params[:medico]
    
    respond_to do |resposta|
      if @medico.save
        flash[:aviso] = 'Médico criado com sucesso.'
        resposta.html { redirect_to(@medico) }
      else
        resposta.html { render :action => 'new' }
      end
    end
  end

  def destroy
    @medico = Medico.da(@clinica).find(params[:id])
    @medico.inativa!
    
    if @medico.save
      flash[:aviso] = 'Médico inativado com sucesso.'
    else
      flash[:aviso] = 'Ocorreu um problema ao inativar o médico. Por favor, tente novamente!'
    end

    respond_to do |resposta|
      resposta.html { redirect_to :action => 'index', :controller => 'medicos' }
    end
  end

end
