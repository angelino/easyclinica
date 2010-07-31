class CreateTabelasDeServicos < ActiveRecord::Migration
  def self.up
    create_table :tabelas_de_servicos do |t|
      t.column :nome, :string, :limit => 50, :null => false
      
      t.timestamps
    end
  end

  def self.down
    drop_table :tabelas_de_servicos
  end
end
