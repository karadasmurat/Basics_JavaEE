package web.bean;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
		
		emp1 = new Employee("EMP", "01", 100);
		
		cert1 = new Certificate("CERT1");
		cert2 = new Certificate("CERT2");
		
		emp1.addCertificate(cert1);
		emp1.addCertificate(cert2);
		
		pageID = 1;
	}

	public String persist() {
		LOGGER.log(Level.INFO, "EmployeeBean.persist()");
		employeeService.persistEmployee(emp1);
		return "response";
	}

	public int getPageID() {
		return pageID;
	}

	public void setPageID(int pageID) {
		this.pageID = pageID;
	}



}





