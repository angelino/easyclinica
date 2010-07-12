class ApplicationController < ActionController::Base
  protect_from_forgery
  layout 'application'
  
  before_filter :seta_clinica
  
  private
    def seta_clinica
      @clinica = Clinica.find_by_login!(request.subdomains.first)
    end
end
