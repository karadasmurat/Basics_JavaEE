package ejb;

import javax.ejb.Local;

import entity.Certificate;
import entity.Employee;

@Local
public interface EmployeeService {
	
	public void persistEmployee(Employee employee);
	public void assignCertificateToEmployee(Certificate cert, Employee emp);
	
}
