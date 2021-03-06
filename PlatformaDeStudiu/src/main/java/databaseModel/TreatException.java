package databaseModel;

import java.sql.SQLException;

import databaseView.PanelFeedback;

public class TreatException {
	
	public static void printSQLException(SQLException ex) 
	{
		for (Throwable e : ex) 
		{
			if (e instanceof SQLException) 
			{
				if (ignoreSQLException(((SQLException) e).getSQLState()) == false) 
				{
					//e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					PanelFeedback.feedbackMessage = e.getMessage();
					Throwable t = ex.getCause();
					while (t != null) 
					{
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}
	}

	public static boolean ignoreSQLException(String sqlState) 
	{
		if (sqlState == null) 
		{
			System.out.println("The SQL state is not defined!");
			return false;
		}
		// X0Y32: Jar file already exists in schema
		if (sqlState.equalsIgnoreCase("X0Y32"))
			return true;
		// 42Y55: Table already exists in schema
		if (sqlState.equalsIgnoreCase("42Y55"))
			return true;
		//if (sqlState.equalsIgnoreCase("42000"))
		//	return true;
		//if (sqlState.equalsIgnoreCase("23000"))
				//	return true;
		return false;
	}
}
