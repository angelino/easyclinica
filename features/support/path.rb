module Path

  def path_to(nome)
    case nome
    when "tela de login"
      '/login'
    when "tela de novo paciente"
      '/pacientes/novo'
    when "tela de novo medico"
      '/medicos/novo'
    else
      raise 'Nao sei aonde eh!'
    end
  end
  
end

World(Path)