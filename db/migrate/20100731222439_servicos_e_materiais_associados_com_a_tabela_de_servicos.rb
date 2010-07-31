class ServicosEMateriaisAssociadosComATabelaDeServicos < ActiveRecord::Migration
  def self.up
    add_column :materiais, :tabela_de_servicos_fk, :integer, :null => :false
    add_column :servicos, :tabela_de_servicos_fk, :integer, :null => :false
    
    add_index :materiais, :tabela_de_servicos_fk
    add_index :servicos, :tabela_de_servicos_fk
  end

  def self.down
    remove_index :materiais, :tabela_de_servicos_fk
    remove_index :servicos, :tabela_de_servicos_fk
    
    remove_column :materiais, :tabela_de_servicos_fk
    remove_column :servicos, :tabela_de_servicos_fk
  end
end
