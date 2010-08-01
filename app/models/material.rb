class Material < ActiveRecord::Base
  belongs_to :tabela
  
  validates_presence_of :nome, :ch, :codigo
end
