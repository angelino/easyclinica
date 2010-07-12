require 'lib/multitenant'

class Convenio < ActiveRecord::Base
  include MultiTenancy
  
  belongs_to :clinica
  validates_presence_of :nome, :telefone, :clinica
  
  def inativa!
    self.ativo = false
  end
  
  def ativa!
    self.ativo = true
  end
end
