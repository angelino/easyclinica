require 'spec_helper'

describe Material do
  it 'deve ser valido' do
    material = Material.new
    material.should_not be_valid
    
    material.nome = 'material'
    material.ch = 10
    material.codigo = '123'
    material.should be_valid
  end
end
