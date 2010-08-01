class Excecaodematerial < ActiveRecord::Base
  belongs_to :convenio
  belongs_to :material
  
  validates_presence_of :convenio, :material
  validates_associated :convenio, :material
end
