package br.com.easyclinica.infra.vraptor.upload;

import br.com.caelum.vraptor.interceptor.multipart.DefaultMultipartConfig;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class CustomMultipartConfig extends DefaultMultipartConfig {

	public long getSizeLimit() {
		return 10 * mb();
	}

	private int mb() {
		return 1024 * 1024;
	}
}
