package security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.sql.DataSource;

import org.jboss.security.auth.spi.DatabaseServerLoginModule;

public class MKLoginModule extends DatabaseServerLoginModule {

	private static final Logger LOGGER = Logger.getLogger(MKLoginModule.class
			.getName());

	/** The sql query to obtain the user password */
	protected String attemptsQuery = ""; //"SELECT LOGINATTEMPTS FROM USERS WHERE USERNAME=?";

	private static final String ATTEMPTS_QUERY = "attemptsQuery";

	private static final String[] ALL_VALID_OPTIONS = { ATTEMPTS_QUERY };

	/** Max allowed login attempts */
	protected int maxRetries = 10;

	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map sharedState, Map options) {

		addValidOptions(ALL_VALID_OPTIONS);
		super.initialize(subject, callbackHandler, sharedState, options);
		LOGGER.log(Level.INFO, "MKLoginModule: initialize()");

		try {

			attemptsQuery = (String) options.get("attemptsQuery");
			LOGGER.log(Level.INFO, "MKLoginModule: attemptsQuery=" + attemptsQuery);

		} catch (Throwable e) {
			// _log.error("Error initializing", e);
		}
		// _log.debug("LoginModule initialized");
	}

	public boolean login() throws LoginException {

		LOGGER.log(Level.INFO, "MKLoginModule: login()");

		super.login();

		int loginAttempts = getCounter();
		LOGGER.log(Level.INFO, "MKLoginModule: loginAttempts is"
				+ loginAttempts);

		if (loginAttempts > maxRetries) {
			super.loginOk = false;
			throw new FailedLoginException("Account Locked");
		}

		return true;

	}

	private int getCounter() {

		LOGGER.log(Level.INFO, "MKLoginModule: getCounter()");

		String username = super.getUsername();
		LOGGER.log(Level.INFO, "MKLoginModule: username is " + username);

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup(dsJndiName);
			con = ds.getConnection();
			ps = con.prepareStatement(attemptsQuery);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				int counter = rs.getInt(1);
				LOGGER.log(Level.INFO, "MKLoginModule: getCounter() value is "
						+ counter);
				return counter;
			} else {
				return Integer.MAX_VALUE;
			}
		} catch (NamingException e) {
			// _log.error("Unexpected error", e);
			return Integer.MAX_VALUE;
		} catch (SQLException e) {
			// _log.error("Query failed", e);
			return Integer.MAX_VALUE;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Throwable e) {
				// _log.error("Error closing connection", e);
			}
		}
	}
}
