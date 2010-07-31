require 'lib/multitenant'

class Convenio < ActiveRecord::Base
  include MultiTenancy
  include DefaultScopes
  
  belongs_to :clinica
  
  validates_length_of   	:nome, :minimum => 1, :maximum => 150, :message => '� obrigat�rio e deve possuir no m�ximo 150 caracteres.'
 								
  validates_associated		:clinica

	validates_presence_of		:telefone, :message => '� obrigat�rio'
	 
  def inativa!
    self.ativo = false
  end
  
  def ativa!
    self.ativo = true
  end
end
