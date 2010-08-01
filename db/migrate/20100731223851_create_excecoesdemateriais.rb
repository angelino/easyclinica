class CreateExcecoesdemateriais < ActiveRecord::Migration
  def self.up
    create_table :excecoesdemateriais do |t|
      t.references :material
      t.references :convenio
      
      t.column :valor_ch, :decimal, :null => false, :precision => 10, :scale => 2, :default => 0.0
      t.column :valor_bruto, :decimal, :null => false, :precision => 10, :scale => 2, :default => 0.0
      
      t.timestamps
    end
  end

  def self.down
    drop_table :excecoesdemateriais
  end
end
