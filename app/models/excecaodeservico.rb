class Excecaodeservico < ActiveRecord::Base
  belongs_to :convenio
  belongs_to :servico
  
  scope :do, lambda { |convenio|  { :conditions => ["convenio_id = ?", convenio] }}
  
  validates_presence_of :convenio, :servico
  validates_associated :convenio, :servico
  validates_uniqueness_of :servico_id, :scope=>:convenio_id, :message => 'já cadastrado'
end
