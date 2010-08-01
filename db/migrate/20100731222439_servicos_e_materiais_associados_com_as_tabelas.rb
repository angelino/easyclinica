class ServicosEMateriaisAssociadosComAsTabelas < ActiveRecord::Migration
  def self.up
    add_column :materiais, :tabelas_id, :integer, :null => :false
    add_column :servicos, :tabelas_id, :integer, :null => :false
    
    add_index :materiais, :tabelas_id
    add_index :servicos, :tabelas_id
  end

  def self.down
    remove_index :materiais, :tabelas_id
    remove_index :servicos, :tabelas_id
    
    remove_column :materiais, :tabelas_id
    remove_column :servicos, :tabelas_id
  end
end
