package br.com.easyclinica.domain.auth;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class AuthSequenceBuilder {

	public AuthSequence build() {
		return new AuthSequence(new AppointmentsNotToDoctors(),
				new ReportsOnlyToFinancial(), new UsersOnlyToOwners(),
				new ClinicInfoOnlyToOwners(), new MyScheduleOnlyToDoctors(),
				new ScheduleOnlyToOwnersAndAttendants());
	}
}
