class CreateEmailAndAtivoNoConvenio < ActiveRecord::Migration
  def self.up
    add_column :convenios, :ativo, :boolean, :null => false, :default => true
    add_column :convenios, :email, :string, :limit => 150
  end

  def self.down
    remove_column :convenios, :ativo
    remove_columm :convenios, :email
  end
end
