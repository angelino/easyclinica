class Excecaodeservico < ActiveRecord::Base
  belongs_to :convenio
  belongs_to :servico
  
  validates_presence_of :convenio, :servico
  validates_associated :convenio, :servico
end
