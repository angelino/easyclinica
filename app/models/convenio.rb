class Convenio < ActiveRecord::Base
  belongs_to :clinica
  
  validates_presence_of :nome, :telefone, :clinica
end
