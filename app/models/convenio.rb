require 'lib/multitenant'

class Convenio < ActiveRecord::Base
  include MultiTenancy
  
  belongs_to :clinica
  
  validates :nome, :presence => true,
  								 :length => {:minimum => 1, :maximum => 150}	
  
  validates :telefone, :presence => true
  								
  validates	:clinica, :presence => true
  
  def inativa!
    self.ativo = false
  end
  
  def ativa!
    self.ativo = true
  end
end
