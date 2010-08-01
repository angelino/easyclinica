class Medico < ActiveRecord::Base
	include MultiTenancy, Scopes
	
	belongs_to :clinica
  
  validates_length_of   	:nome, :minimum => 1, :maximum => 150, :message => '� obrigat�rio e deve possuir no m�ximo 150 caracteres.'
  								   
  validates_length_of			:crm, :minimum => 1, :maximum => 50, :message => 'inv�lido.'
  validates_uniqueness_of	:crm, :message => 'j� cadastrado anteriormente.'
  								
  validates_associated		:clinica

	validates_length_of			:email, :minimum => 3, :maximum => 100, :message => '� obrigat�rio e deve possuir no m�ximo 100 caracteres.'
  validates_uniqueness_of	:email, :message => 'j� cadastrado anteriormente.'
 	validates_format_of			:email, :with => /^([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})$/i, :message => 'inv�lido.'

  def inativa!
    self.ativo = false
  end
  
  def ativa!
    self.ativo = true
  end                  

end
