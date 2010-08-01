class Excecaodeservico < ActiveRecord::Base
  belongs_to :convenio
  belongs_to :servico
end
