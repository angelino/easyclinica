class Servico < ActiveRecord::Base
  has_and_belongs_to_many :materiais
  belongs_to :tabela
  
  validates_presence_of :nome, :ch, :codigo
end
