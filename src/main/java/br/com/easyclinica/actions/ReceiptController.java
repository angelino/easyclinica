package br.com.easyclinica.actions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.view.Results;
import br.com.easyclinica.domain.entities.Kinship;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.Receipt;
import br.com.easyclinica.domain.repositories.AllPatients;
import br.com.easyclinica.domain.repositories.AllReceipts;
import br.com.easyclinica.domain.validators.ReceiptValidator;
import br.com.easyclinica.infra.multitenancy.LoggedUser;
import br.com.easyclinica.infra.reports.JasperMaker;
import br.com.easyclinica.infra.vraptor.validators.ErrorTranslator;
import br.com.easyclinica.view.Messages;

@Resource
public class ReceiptController extends BaseController {

	private AllPatients allPatients;
	private AllReceipts allReceipts;
	private final Validator validator;
	private final ErrorTranslator translator;
	private final ReceiptValidator receiptValidator;
	private final JasperMaker jasperMaker;
	private final LoggedUser loggedUser;
	
	public ReceiptController(Result result, AllPatients allPatients, AllReceipts allReceipts, 
			ReceiptValidator receiptValidator, Validator validator, ErrorTranslator translator,
			JasperMaker jasperMaker, LoggedUser loggedUser) {
		super(result);
		
		this.allPatients = allPatients;
		this.allReceipts = allReceipts;
		this.validator = validator;
		this.translator = translator;
		this.receiptValidator = receiptValidator;
		this.jasperMaker = jasperMaker;
		this.loggedUser = loggedUser;
	}

	@Get
	@Path("/pacientes/{patient_id}/recibos")
	public void list(int patient_id) {
		result.include("patient", allPatients.getById(patient_id));
	}
	
	@Get
	@Path("/pacientes/{patient_id}/recibos/novo")
	public void newForm(int patient_id, Receipt receipt) {
		Receipt emptyReceipt = (receipt == null ? Receipt.empty() : receipt);
		
		emptyReceipt.setPatient(allPatients.getById(patient_id));
		
		include(emptyReceipt);
		result.include("kinships", Kinship.values());
	}
	
	@Post
	@Path("/pacientes/{patient_id}/recibos")
	public void save(int patient_id, final Receipt receipt) {
		translator.translate(receiptValidator.validate(receipt));
		validator.onErrorUse(Results.logic())
				.forwardTo(ReceiptController.class)
				.newForm(patient_id, receipt);

		allReceipts.add(receipt);

		successMsg(Messages.RECEIPT_ADDED);
		result.redirectTo(ReceiptController.class).list(patient_id);
	}
	
	@Get
	@Path("/pacientes/{patient_id}/recibos/{id}/editar")
	public void edit(int patient_id, int id) {
		Receipt receiptToBeEdited = allReceipts.getById(id);
		include(receiptToBeEdited);

		result.include("kinships", Kinship.values());
	}

	@Put
	@Path("/pacientes/{patient_id}/recibos/{id}")
	public void update(int patient_id, final Receipt receipt) {
		translator.translate(receiptValidator.validate(receipt));
		validator.onErrorUse(Results.logic())
				.forwardTo(ReceiptController.class)
				.newForm(patient_id, receipt);


		allReceipts.update(receipt);

		successMsg(Messages.RECEIPT_UPDATED);
		result.redirectTo(ReceiptController.class).list(patient_id);
	}

	@Post
	public void _show(int id) {
		Receipt receipt = allReceipts.getById(id);
		include(receipt);
	}
	
	@Delete
	@Path("/pacientes/{patient_id}/recibos/{id}")
	public void delete(int patient_id, int id) {
		Receipt receipt = allReceipts.getById(id);
		
		allReceipts.delete(receipt);
		
		successMsg(Messages.RECEIPT_DELETED);
		result.redirectTo(ReceiptController.class).list(patient_id);
	}
	
	@Get
	@Path("/pacientes/{patient_id}/recibos/{id}")
	public Download receipt(int patient_id, int id) {
		Patient patient = allPatients.getById(patient_id);
		Receipt receipt = allReceipts.getById(id);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("receipt", receipt);
		params.put("clinic", loggedUser.getClinic());
		
		return jasperMaker.makePdf(  
	               "receipt",  
	               Collections.singletonList(patient),   
	               "recibo.pdf",   
	               true,   
	               params);
	}
	
	private void include(Receipt receipt) {
		result.include("receipt", receipt);
	}
}
