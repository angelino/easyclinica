class CreateConvenios < ActiveRecord::Migration
  def self.up
    create_table :convenios do |t|
      t.column :nome, :string, :limit => 150, :null => false
      t.column :telefone, :string, :limit => 20, :null => false
      t.column :endereco, :string, :limit => 200
      t.column :bairro, :string, :limit => 100
      t.column :cidade, :string, :limit => 100
      t.column :estado, :string, :limit => 2
      t.column :cep, :string, :limit => 9
      t.column :complemento, :string, :limit => 20
      t.column :responsavel, :string, :limit => 20
      t.column :telefone_responsavel, :string, :limit => 20
      
      t.references :clinica
      t.timestamps
    end
  end

  def self.down
    drop_table :convenios
  end
end
