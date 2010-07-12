class ConveniosController < ApplicationController
  
  def index
    @convenios = Convenio.da(@clinica).find(:all)
  end
end
