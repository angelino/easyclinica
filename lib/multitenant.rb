module MultiTenancy
  def self.included(base)
    base.class_eval {
      scope :da, lambda { |clinica|  { :conditions => ["clinica_id = ?", clinica] }}
    }
  end
end