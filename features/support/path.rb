module Path

  def path_to(nome)
    case nome
    when "tela de login"
      '/login'
    when "tela de novo paciente"
      '/pacientes/novo'
    when "tela de listagem de pacientes"
      '/pacientes'
    when "tela de listagem de medicos"
      '/medicos'
    when "tela de novo medico"
      '/medicos/novo'
    when "tela de novo convenio"
      '/convenios/novo'
    when "tela de agenda"
      '/agenda'
    else
      raise 'Nao sei aonde eh essa URL!'
    end
  end
  
end

World(Path)