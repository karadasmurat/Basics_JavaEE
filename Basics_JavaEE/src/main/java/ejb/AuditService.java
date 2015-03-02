package ejb;

import javax.ejb.Local;

@Local
public interface AuditService {
	
	public void log(String logText);

}
