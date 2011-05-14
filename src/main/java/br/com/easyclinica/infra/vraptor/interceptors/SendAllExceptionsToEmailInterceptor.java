package br.com.easyclinica.infra.vraptor.interceptors;

import java.util.Calendar;

import org.apache.log4j.Logger;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.easyclinica.config.Config;
import br.com.easyclinica.infra.email.SendMail;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@RequestScoped
@Intercepts
public class SendAllExceptionsToEmailInterceptor implements Interceptor {

	private static Logger log = Logger
			.getLogger(SendAllExceptionsToEmailInterceptor.class);
	private final SendMail mailer;
	private final LoggedUser user;
	private final Config config;

	public SendAllExceptionsToEmailInterceptor(SendMail mailer,
			LoggedUser user, Config config) {
		this.mailer = mailer;
		this.user = user;
		this.config = config;
	}

	public boolean accepts(ResourceMethod method) {
		return user.isLogged();
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object instance) throws InterceptionException {

		try {

			stack.next(method, instance);

		} catch (RuntimeException e) {
			log(e, method);
			throw e;
		} catch (Exception e) {
			log(e, method);
			throw new RuntimeException(e);
		}

	}

	private void log(Exception e, ResourceMethod method) {

		log.error("ERROR in method " + method.getMethod().getName() + "(class "
				+ method.getResource().getType().getName() + ")", e);

		if (config.get("env").equals("production")) {
			StringBuilder message = new StringBuilder();
			message.append("Usuário: " + user.getEmployee().getLogin() + "\r\n");
			message.append("Tenant: " + user.getClinic().getDomain() + "\r\n");
			message.append("Data: " + Calendar.getInstance().getTime() + "\r\n");
			message.append("Ação: " + method.getMethod().getName()
					+ " na classe " + method.getResource().getType().getName()
					+ "\r\n");
			message.append("\r\n" + logException(e));

			try {
				mailer.sendMail(config.get("from_email"),
						config.get("error_email").split(";"),
						"[Easy Clínica] Erro em produção", message.toString());
				log.info("sending email to developers...");
			} catch (Exception ex) {
				log.fatal("email to developers not sent!", ex);
			}
		}
	}

	private String logException(Throwable e) {
		if (e == null)
			return "";

		StringBuilder builder = new StringBuilder();
		builder.append("\r\nExceção: " + e.getMessage() + "\r\n");
		for (StackTraceElement trace : e.getStackTrace()) {
			builder.append(trace.getClassName() + ":" + trace.getLineNumber()
					+ " em " + trace.getMethodName() + "\r\n");
		}

		return builder.toString() + logException(e.getCause());
	}

}
