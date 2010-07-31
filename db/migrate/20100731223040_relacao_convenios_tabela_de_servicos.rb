class RelacaoConveniosTabelaDeServicos < ActiveRecord::Migration
  def self.up
    add_column :convenios, :tabela_de_servicos_fk, :integer, :null => false, :default => 0
  end

  def self.down
    remove_column :convenios, :tabela_de_servicos_fk
  end
end
