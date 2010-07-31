module DefaultScopes
  def self.included(base)
    base.class_eval {
	    scope :ordenado_por_ativo, :order => "ativo DESC"
    }
  end
end