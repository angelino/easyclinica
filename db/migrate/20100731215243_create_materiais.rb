class CreateMateriais < ActiveRecord::Migration
  def self.up
    create_table :materiais do |t|
      t.column :nome, :string, :limit => 100, :null => false
      t.column :codigo, :string, :limit => 50, :null => false
      t.column :ch, :decimal, :null => false, :default => 0.0, :precision => 10, :scale => 2
      t.timestamps
    end
  end

  def self.down
    drop_table :materiais
  end
end
