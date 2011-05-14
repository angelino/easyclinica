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
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.repositories.AllEmployees;
import br.com.easyclinica.domain.validators.EmployeeValidator;
import br.com.easyclinica.infra.md5.MD5Calculator;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.Messages;
import br.com.easyclinica.view.paginator.Paginator;

@Resource
public class UsersController extends BaseController {

	private final Validator validator;
	private final ErrorTranslator translator;
	private final AllEmployees allEmployees;
	private final EmployeeValidator employeeValidator;
	private final AllDoctors doctors;
	private final MD5Calculator md5;

	public UsersController(AllEmployees allEmployees, 
			Result result, Validator validator, EmployeeValidator employeeValidator, 
			ErrorTranslator translator, Paginator paginator, AllDoctors doctors, MD5Calculator md5) {
		super(result);
		this.allEmployees = allEmployees;
		this.result = result;
		this.validator = validator;
		this.employeeValidator = employeeValidator;
		this.translator = translator;
		this.doctors = doctors;
		this.md5 = md5;
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
		result.include("doctors", doctors.get());
		result.include("positions", Position.values());
		include(emptyEmployee);
	}
	
	@Post
	@Path("/usuarios")
	public void save(final Employee employee) {
		translator.translate(employeeValidator.validate(employee));
		validator.onErrorUse(Results.logic()).forwardTo(UsersController.class).newForm();
		
		setDoctor(employee);
		encryptPassword(employee);
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
	@Path("/usuarios/{id}")
	public void show(int id) {
		include(allEmployees.getById(id));
	}
	
	@Get
	@Path("/usuarios/{id}/editar")
	public void edit(int id) {
		Employee employeeToBeEdited = allEmployees.getById(id);
		result.include("positions", Position.values());
		result.include("doctors", doctors.get());
		include(employeeToBeEdited);
	}

	@Put
	@Path("usuarios/{employee.id}")
	public void update(final Employee employee) {
		translator.translate(employeeValidator.validateProfileUpdate(employee));
		validator.onErrorUse(Results.logic()).forwardTo(UsersController.class).edit(employee.getId());
		
		Employee current = allEmployees.getById(employee.getId());
		
		matchOldPassword(employee, current);		
		setDoctor(employee);
		
		allEmployees.update(employee);
		
		successMsg(Messages.EMPLOYEE_UPDATED);
		result.redirectTo(UsersController.class).index();
	}

	private void setDoctor(final Employee employee) {
		if(employee.getDoctor()!=null && employee.getDoctor().getId() > 0) {
			employee.setDoctor(doctors.getById(employee.getDoctor().getId()));
		}
		else {
			employee.setDoctor(null);
		}
	}

	private void matchOldPassword(final Employee employee, Employee current) {
		if(employee.getPassword().length() == 0) {
			employee.setPassword(current.getPassword());
		}
		else {
			encryptPassword(employee);
		}
	}

	private void encryptPassword(final Employee employee) {
		employee.setPassword(md5.calculate(employee.getPassword()));
	}

	private void include(Employee employee) {
		result.include("employee", employee);
	}

	@Put
	@Path("usuarios/{id}/desativar")
	public void deactivate(int id) {
		Employee employee = allEmployees.getById(id);
		
		employee.deactive();
		allEmployees.update(employee);
		
		successMsg(Messages.EMPLOYEE_DEACTIVATED);
		result.redirectTo(UsersController.class).index();
	}
	
	@Put
	@Path("usuarios/{id}/ativar")
	public void activate(int id) {
		Employee employee = allEmployees.getById(id);
		
		employee.active();
		allEmployees.update(employee);
		
		successMsg(Messages.EMPLOYEE_ACTIVATED);
		result.redirectTo(UsersController.class).index();
	}
}
