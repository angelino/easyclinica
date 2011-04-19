package br.com.easyclinica.infra.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.InputStreamDownload;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class JasperMaker {

	private static final Logger LOGGER = LoggerFactory.getLogger(JasperMaker.class);
	private final String jasperDir;
	private final String contextDir;
	
	public JasperMaker(ServletContext servletContext) {
		
		contextDir = servletContext.getRealPath("/");
		String temp = servletContext.getInitParameter("vraptor.jasperMaker");
		temp = temp == null ? "WEB-INF/reports/" : temp.trim();
		if (!temp.endsWith("/"))
			temp = temp.concat("/");
		jasperDir = temp.startsWith("/") ? temp : contextDir.concat(temp);
	}
	
	public Download makePdf(String jasperFile,Collection<?> dataSource, String fileName, boolean doDownload) {
		return makePdf(jasperFile, dataSource, fileName, doDownload, new HashMap<String, Object>());
	}
	
	public Download makePdf(String jasperFile,Collection<?> dataSource, String fileName, boolean doDownload, Map<String,Object> parametros) {
		jasperFile = jasperDir+jasperFile + ".jasper";
		parametros.put("jasperPath", jasperDir);
		parametros.put("contextPath", contextDir);
        try {
            JasperPrint print = JasperFillManager.fillReport(jasperFile, parametros, new JRBeanCollectionDataSource(dataSource));
            
            JRExporter exporter = new JRPdfExporter();

    		ByteArrayOutputStream exported = new ByteArrayOutputStream();
    		
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, exported);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            
            exporter.exportReport();
            
            byte[] content = exported.toByteArray();
            
            return new InputStreamDownload(
            		new ByteArrayInputStream(content),
            		"application/pdf",
            		fileName,
            		doDownload,
            		content.length);
            
		} catch (Exception e) {
			LOGGER.error("PDF Exporter error",e);
			throw new RuntimeException(e);
        }
		
	}
}