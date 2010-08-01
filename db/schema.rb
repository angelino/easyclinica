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

ActiveRecord::Schema.define(:version => 20100801000422) do

  create_table "clinicas", :force => true do |t|
    t.string   "nome",       :limit => 100, :null => false
    t.string   "login",      :limit => 25,  :null => false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "clinicas", ["login"], :name => "index_clinicas_on_login", :unique => true

  create_table "convenios", :force => true do |t|
    t.string   "nome",                 :limit => 150,                                                  :null => false
    t.string   "telefone",             :limit => 20,                                                   :null => false
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
    t.boolean  "ativo",                                                              :default => true, :null => false
    t.string   "email",                :limit => 150
    t.text     "observacoes"
    t.string   "website",              :limit => 100
    t.integer  "tabelas_id",                                                         :default => 0,    :null => false
    t.decimal  "valor_ch",                            :precision => 10, :scale => 2, :default => 0.0,  :null => false
  end

  create_table "excecoesdemateriais", :force => true do |t|
    t.integer  "material_id"
    t.integer  "convenio_id"
    t.decimal  "valor_ch",    :precision => 10, :scale => 2, :default => 0.0, :null => false
    t.decimal  "valor_bruto", :precision => 10, :scale => 2, :default => 0.0, :null => false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "excecoesdeservicos", :force => true do |t|
    t.integer  "servico_id"
    t.integer  "convenio_id"
    t.decimal  "valor_ch",    :precision => 10, :scale => 2, :default => 0.0, :null => false
    t.decimal  "valor_bruto", :precision => 10, :scale => 2, :default => 0.0, :null => false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "materiais", :force => true do |t|
    t.string   "nome",       :limit => 100,                                                 :null => false
    t.string   "codigo",     :limit => 50,                                                  :null => false
    t.decimal  "ch",                        :precision => 10, :scale => 2, :default => 0.0, :null => false
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "tabelas_id"
  end

  add_index "materiais", ["tabelas_id"], :name => "index_materiais_on_tabelas_id"

  create_table "materiais_servicos", :id => false, :force => true do |t|
    t.integer "material_id", :null => false
    t.integer "servico_id",  :null => false
  end

  create_table "medicos", :force => true do |t|
    t.string   "nome",          :limit => 150,                   :null => false
    t.string   "crm",           :limit => 50,                    :null => false
    t.string   "especialidade", :limit => 150
    t.string   "telefone",      :limit => 20
    t.string   "email",         :limit => 100
    t.text     "observacoes"
    t.boolean  "ativo",                        :default => true, :null => false
    t.integer  "clinica_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "medicos", ["crm"], :name => "index_medicos_on_crm", :unique => true

  create_table "servicos", :force => true do |t|
    t.string   "nome",       :limit => 100,                                                 :null => false
    t.string   "codigo",     :limit => 50,                                                  :null => false
    t.decimal  "ch",                        :precision => 10, :scale => 2, :default => 0.0, :null => false
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "tabelas_id"
  end

  add_index "servicos", ["tabelas_id"], :name => "index_servicos_on_tabelas_id"

  create_table "tabelas", :force => true do |t|
    t.string   "nome",       :limit => 50, :null => false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

end
