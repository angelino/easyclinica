class Convenio < ActiveRecord::Base
  include MultiTenancy, Scopes
  
  belongs_to :clinica
  belongs_to :tabela
  
  has_many :excecoesdeservicos
  has_many :excecoesdemateriais
  
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
