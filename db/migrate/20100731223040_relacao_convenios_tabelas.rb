class RelacaoConveniosTabelas < ActiveRecord::Migration
  def self.up
    add_column :convenios, :tabela_id, :integer, :null => false, :default => 0
  end

  def self.down
    remove_column :convenios, :tabela_id
  end
end
