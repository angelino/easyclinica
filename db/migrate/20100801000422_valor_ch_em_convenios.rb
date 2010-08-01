class ValorChEmConvenios < ActiveRecord::Migration
  def self.up
    add_column :convenios, :valor_ch, :decimal, :null => false, :precision => 10, :scale => 2, :default => 0.0
  end

  def self.down
    remove_column :convenios, :valor_ch
  end
end
