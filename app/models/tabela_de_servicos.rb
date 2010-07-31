class TabelaDeServicos < ActiveRecord::Base
  has_many :servicos
  has_many :materiais
end
