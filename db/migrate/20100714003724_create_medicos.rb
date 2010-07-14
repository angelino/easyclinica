class CreateMedicos < ActiveRecord::Migration
  def self.up
    create_table :medicos do |t|
			t.column :nome, :string, :limit => 150, :null => false
			t.column :crm, :string, :limit => 50, :null => false
			t.column :especialidade, :string, :limit => 150
			t.column :telefone, :string, :limit => 20
			t.column :email, :string, :limit => 100
			t.column :observacoes, :text
			t.column :ativo, :boolean, :null => false, :default => true
			
			t.references :clinica
      t.timestamps
    end
    
    add_index :medicos, :crm, :unique => true    
  end

  def self.down
  	remove_index :medicos, :crm
    drop_table :medicos
  end
end
