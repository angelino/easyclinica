package br.com.easyclinica.infra.vraptor.interceptors;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.core.MethodInfo;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.interceptor.ParametersInstantiatorInterceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.AppointmentMaterial;
import br.com.easyclinica.domain.entities.AppointmentMedicine;
import br.com.easyclinica.domain.entities.AppointmentProcedure;

@Intercepts(after = ParametersInstantiatorInterceptor.class)
@RequestScoped
public class AppointmentParentsSetterInterceptor implements Interceptor {

	private final MethodInfo info;
	private int position;

	public AppointmentParentsSetterInterceptor(MethodInfo info) {
		this.info = info;
	}

	public boolean accepts(ResourceMethod method) {
		for (int i = 0; i < method.getMethod().getParameterTypes().length; i++) {
			Class<?> clazz = method.getMethod().getParameterTypes()[i];

			if (clazz.isAssignableFrom(Appointment.class)) {
				position = i;
				return true;
			}
		}

		return false;
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object instance) throws InterceptionException {

		Appointment appointment = (Appointment) info.getParameters()[position];
		appointment.recalculate();
		setAllParents(appointment);

		stack.next(method, instance);
	}

	private void setAllParents(Appointment appointment) {
		for (AppointmentProcedure procedure : appointment.getProcedures()) {
			procedure.setAppointment(appointment);

			for (AppointmentMaterial material : procedure.getMaterials()) {
				material.setProcedure(procedure);
			}

			for (AppointmentMedicine medicine : procedure.getMedicines()) {
				medicine.setProcedure(procedure);
			}
		}
	}

}
