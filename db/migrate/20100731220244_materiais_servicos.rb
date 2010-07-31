class MateriaisServicos < ActiveRecord::Migration
  def self.up
    create_table "materiais_servicos", :id => false do |t|
      t.column "material_id", :integer, :null => false
      t.column "servico_id", :integer, :null => false
    end
  end

  def self.down
    drop_table "materiais_servicos"
  end
end
