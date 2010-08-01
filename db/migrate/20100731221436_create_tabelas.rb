class CreateTabelas < ActiveRecord::Migration
  def self.up
    create_table :tabelas do |t|
      t.column :nome, :string, :limit => 50, :null => false
      
      t.timestamps
    end
  end

  def self.down
    drop_table :tabelas
  end
end
