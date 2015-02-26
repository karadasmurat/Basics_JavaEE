package ejb;

import javax.ejb.Local;

import entity.Employee;

@Local
public interface EmployeeService {
	
	public void persistEmployee(Employee employee);
	
}
