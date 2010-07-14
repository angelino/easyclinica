require 'lib/multitenant'

class Medico < ActiveRecord::Base
	include MultiTenancy
  
  belongs_to :clinica
  
  validates :nome, :presence => true,
  								 :length => {:minimum => 1, :maximum => 150}
  
  validates :crm, :presence => true,
  								:uniqueness => true,
  								:length => {:minimum => 1, :maximum => 50}
  								
  validates	:clinica, :presence => true
  
  validates :email, :presence => true, 
                    :length => {:minimum => 3, :maximum => 100},
                    :uniqueness => true,
                    :format => {:with => /^([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})$/i}
  
  def inativa!
    self.ativo = false
  end
  
  def ativa!
    self.ativo = true
  end                  

end
