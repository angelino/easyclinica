class ServicosEMateriaisAssociadosComAsTabelas < ActiveRecord::Migration
  def self.up
    add_column :materiais, :tabela_id, :integer, :null => :false
    add_column :servicos, :tabela_id, :integer, :null => :false
    
    add_index :materiais, :tabela_id
    add_index :servicos, :tabela_id
  end

  def self.down
    remove_index :materiais, :tabela_id
    remove_index :servicos, :tabela_id
    
    remove_column :materiais, :tabela_id
    remove_column :servicos, :tabela_id
  end
end
