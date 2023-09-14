package in.fssa.technolibrary.exception;

import java.sql.SQLException;

public class PersistanceException extends Exception {
	
		public PersistanceException(SQLException e) {

			super(e);
 
		}

	}
