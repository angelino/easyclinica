class CreateClinicas < ActiveRecord::Migration
  def self.up
    create_table :clinicas do |t|
      t.column :nome, :string, :limit => 100, :null => false
      t.column :login, :string, :limit => 25, :null => false

      t.timestamps
    end
    
    add_index :clinicas, :login, :unique => true
  end

  def self.down
    remove_index :clinicas, :login
    drop_table :clinicas
  end
end
