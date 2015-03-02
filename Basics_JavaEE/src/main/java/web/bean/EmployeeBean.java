package web.bean;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import ejb.EmployeeService;
import entity.Certificate;
import entity.Employee;

@Named
@RequestScoped
public class EmployeeBean {

	private static final Logger LOGGER = Logger.getLogger(EmployeeBean.class.getName());

	@EJB
	EmployeeService employeeService;

	private Employee emp1;
	private Certificate cert1;
	private Certificate cert2;
	private int pageID;

	public EmployeeBean() {
		LOGGER.log(Level.INFO, "Initializing EmployeeBean");
	}

	@PostConstruct
	public void initialize() {
		
		emp1 = new Employee("EMP_0227_01", "01", 100);
		
		cert1 = new Certificate("CERT_0227_01");
		cert2 = new Certificate("CERT_0227_02");
		
		employeeService.assignCertificateToEmployee(cert1, emp1);
		employeeService.assignCertificateToEmployee(cert2, emp1);
		
		pageID = 1;
	}

	public String persist() {
		LOGGER.log(Level.INFO, "EmployeeBean.persist()");
		employeeService.persistEmployee(emp1);
		
	    FacesContext.getCurrentInstance().addMessage(null, 
	            new FacesMessage(FacesMessage.SEVERITY_INFO, "Done.", null));

		return "response";
	}

	public int getPageID() {
		return pageID;
	}

	public void setPageID(int pageID) {
		this.pageID = pageID;
	}



}





