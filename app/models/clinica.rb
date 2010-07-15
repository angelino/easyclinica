class Clinica < ActiveRecord::Base
  has_many :convenios
  has_many :medicos
  
  validates :nome, :presence => true,
  								 :length => {:minimum => 1, :maximum => 100}
  
  validates :login, :presence => true,
  									:uniqueness => true,
  									:length => {:minimum => 1, :maximum => 25}
  
end
