class Excecaodematerial < ActiveRecord::Base
  belongs_to :convenio
  belongs_to :material
  
  scope :do, lambda { |convenio|  { :conditions => ["convenio_id = ?", convenio] }}
  
  validates_presence_of :convenio, :material
  validates_associated :convenio, :material
  validates_uniqueness_of :material_id, :scope=>:convenio_id, :message => 'já cadastrado'
end
