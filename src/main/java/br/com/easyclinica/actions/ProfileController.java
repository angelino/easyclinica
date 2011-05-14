package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.repositories.AllEmployees;
import br.com.easyclinica.domain.validators.EmployeeValidator;
import br.com.easyclinica.infra.md5.MD5Calculator;
import br.com.easyclinica.infra.multitenancy.LoggedUser;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.Messages;

@Resource
public class ProfileController extends BaseController {

	private final LoggedUser loggedUser;
	private final Validator validator;
	private final EmployeeValidator employeeValidator;
	private final ErrorTranslator translator;
	private final AllEmployees allEmployees;
	private final MD5Calculator md5;

	public ProfileController(Result result, LoggedUser loggedUser,
			Validator validator, EmployeeValidator employeeValidator,
			ErrorTranslator translator, AllEmployees allEmployees, MD5Calculator md5) {
		super(result);

		this.loggedUser = loggedUser;
		this.validator = validator;
		this.employeeValidator = employeeValidator;
		this.translator = translator;
		this.allEmployees = allEmployees;
		this.md5 = md5;
	}

	@Get
	@Path("/perfil")
	public Employee myProfile() {
		return loggedUser.getEmployee();
	}

	@Post
	@Path("/perfil")
	public void update(Employee employee) {
		translator.translate(employeeValidator.validateProfileUpdate(employee));
		validator.onErrorUse(Results.logic())
				.forwardTo(ProfileController.class).myProfile();

		Employee current = allEmployees.getById(loggedUser.getEmployee()
				.getId());

		if (employee.getPassword().length() == 0) {
			employee.setPassword(current.getPassword());
		}
		else {
			employee.setPassword(md5.calculate(employee.getPassword()));
		}

		allEmployees.update(employee);

		updateLoggedUserInfoTo(employee);

		successMsg(Messages.EMPLOYEE_UPDATED);
		result.redirectTo(ProfileController.class).myProfile();
	}

	private void updateLoggedUserInfoTo(Employee employee) {
		loggedUser.set(loggedUser.getClinic(), employee);
	}
}
