module DefaultScopes
  def self.included(base)
    base.class_eval {
	    scope :ordenado_por_ativo, :order => "ativo DESC"
	    scope :ordenado_por_nome, :order => "nome ASC"
    }
  end
end