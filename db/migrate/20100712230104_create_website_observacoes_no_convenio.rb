class CreateWebsiteObservacoesNoConvenio < ActiveRecord::Migration
  def self.up
    add_column :convenios, :observacoes, :text
    add_column :convenios, :website, :string, :limit => 100
  end

  def self.down
    remove_columm :convenios, :observacoes
    remove_column :convenios, :website
  end
end
