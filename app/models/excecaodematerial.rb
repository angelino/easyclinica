class Excecaodematerial < ActiveRecord::Base
  belongs_to :convenio
  belongs_to :material
end
