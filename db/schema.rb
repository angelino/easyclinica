# This file is auto-generated from the current state of the database. Instead of editing this file, 
# please use the migrations feature of Active Record to incrementally modify your database, and
# then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your database schema. If you need
# to create the application database on another system, you should be using db:schema:load, not running
# all the migrations from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended to check this file into your version control system.

ActiveRecord::Schema.define(:version => 20100712215953) do

  create_table "clinicas", :force => true do |t|
    t.string   "nome",       :limit => 100, :null => false
    t.string   "login",      :limit => 25,  :null => false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "clinicas", ["login"], :name => "index_clinicas_on_login", :unique => true

  create_table "convenios", :force => true do |t|
    t.string   "nome",                 :limit => 150, :null => false
    t.string   "telefone",             :limit => 20,  :null => false
    t.string   "endereco",             :limit => 200
    t.string   "bairro",               :limit => 100
    t.string   "cidade",               :limit => 100
    t.string   "estado",               :limit => 2
    t.string   "cep",                  :limit => 9
    t.string   "complemento",          :limit => 20
    t.string   "responsavel",          :limit => 20
    t.string   "telefone_responsavel", :limit => 20
    t.integer  "clinica_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

end