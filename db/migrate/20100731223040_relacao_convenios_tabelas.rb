class RelacaoConveniosTabelas < ActiveRecord::Migration
  def self.up
    add_column :convenios, :tabelas_id, :integer, :null => false, :default => 0
  end

  def self.down
    remove_column :convenios, :tabelas_id
  end
end
