class Medico < ActiveRecord::Base
	include MultiTenancy, Scopes
	
	belongs_to :clinica
  
  validates_length_of   	:nome, :minimum => 1, :maximum => 150, :message => 'é obrigatório e deve possuir no máximo 150 caracteres.'
  								   
  validates_length_of			:crm, :minimum => 1, :maximum => 50, :message => 'inválido.'
  validates_uniqueness_of	:crm, :message => 'já cadastrado anteriormente.'
  								
  validates_associated		:clinica

	validates_length_of			:email, :minimum => 3, :maximum => 100, :message => 'é obrigatório e deve possuir no máximo 100 caracteres.'
  validates_uniqueness_of	:email, :message => 'já cadastrado anteriormente.'
 	validates_format_of			:email, :with => /^([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})$/i, :message => 'inválido.'

  def inativa!
    self.ativo = false
  end
  
  def ativa!
    self.ativo = true
  end                  

end
