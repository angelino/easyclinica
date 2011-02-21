package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.Position;
import br.com.easyclinica.domain.repositories.AllEmployees;
import br.com.easyclinica.domain.validators.EmployeeValidator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.Messages;
import br.com.easyclinica.view.paginator.Paginator;

@Resource
public class UsersController extends BaseController {

	private final Validator validator;
	private final ErrorTranslator translator;
	private final AllEmployees allEmployees;
	private final EmployeeValidator employeeValidator;

	public UsersController(AllEmployees allEmployees, 
			Result result, Validator validator, EmployeeValidator employeeValidator, 
			ErrorTranslator translator, Paginator paginator) {
		super(result);
		this.allEmployees = allEmployees;
		this.result = result;
		this.validator = validator;
		this.employeeValidator = employeeValidator;
		this.translator = translator;
	}
	
	@Get
	@Path("/usuarios")
	public void index() {
		result.include("employees", allEmployees.get());
	}

	@Get
	@Path("/usuarios/novo")
	public void newForm() {
		Employee emptyEmployee = Employee.empty();
		result.include("positions", Position.values());
		include(emptyEmployee);
	}
	
	@Post
	@Path("/usuarios")
	public void save(final Employee employee) {
		translator.translate(employeeValidator.validate(employee));
		validator.onErrorUse(Results.logic()).forwardTo(UsersController.class).newForm();
		
		allEmployees.add(employee);
		
		successMsg(Messages.EMPLOYEE_ADDED);
		result.redirectTo(UsersController.class).index();
	}

	@Post
	@Path("/usuarios/_show")
	public void _show(int id) {
		Employee loadedEmployee = allEmployees.getById(id);
		include(loadedEmployee);
	}
	
	@Get
	@Path("/usuarios/{id}/editar")
	public void edit(int id) {
		Employee employeeToBeEdited = allEmployees.getById(id);
		result.include("positions", Position.values());
		include(employeeToBeEdited);
	}

	@Put
	@Path("usuarios/{employee.id}")
	public void update(final Employee employee) {
		translator.translate(employeeValidator.validate(employee));
		validator.onErrorUse(Results.logic()).forwardTo(UsersController.class).edit(employee.getId());
		
		Employee current = allEmployees.getById(employee.getId());
		if(employee.getPassword().length() == 0) {
			employee.setPassword(current.getPassword());
		}
		
		allEmployees.update(employee);
		
		successMsg(Messages.EMPLOYEE_UPDATED);
		result.redirectTo(UsersController.class).index();
	}

	private void include(Employee employee) {
		result.include("employee", employee);
	}

	@Get
	@Path("usuarios/{id}/desativar")
	public void deactivate(int id) {
		Employee employee = allEmployees.getById(id);
		
		employee.deactive();
		allEmployees.update(employee);
		
		successMsg(Messages.EMPLOYEE_DEACTIVATED);
		result.redirectTo(UsersController.class).index();
	}
	
	@Get
	@Path("usuarios/{id}/ativar")
	public void activate(int id) {
		Employee employee = allEmployees.getById(id);
		
		employee.active();
		allEmployees.update(employee);
		
		successMsg(Messages.EMPLOYEE_ACTIVATED);
		result.redirectTo(UsersController.class).index();
	}
}
