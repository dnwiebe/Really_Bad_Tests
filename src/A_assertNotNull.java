
class DatabaseThingy {
	public Connection makeDatabaseConnection (String username, 
			String password) {
		DriverManager.registerDriver (new com.awesome.db.JDBCDriver ());
		String url = BASE_JDBC_URL + 
			";username=" + username + ";password=" + password;
		return DriverManager.getConnection (url);	
	}
}














// ------

@Test
public void configuresConnectionWithUsernameAndPassword () {
	DatabaseThingy subject = new DatabaseThingy ();

	Connection result = subject.makeDatabaseConnection ("billy", 
		"moonwalk");

	assertNotNull (result);
}














// ------

class DatabaseThingy {
	public Connection makeDatabaseConnection (String username, 
			String password) {
		DriverManager.registerDriver (new org.apache.derby.jdbc.EmbeddedDriver ());
		return DriverManager.getConnection ("jdbc:derby:memory:test");	
	}
}
















// ------

class DriverManagerWrapper {
	public void registerDriver (Driver driver) {
		// This line is untestable
		DriverManager.registerDriver (driver);
	}

	public Connection getConnection (String url) {
		// This line is untestable
		return DriverManager.getConnection (url);
	}
}












// ------

@Test
public void configuresConnectionWithUsernameAndPassword () {
	String expectedUrl = BASE_JDBC_URL + 
		";username=billy;password=moonwalk";
	Connection connection = mock (Connection.class);
	DriverManagerWrapper driverManagerWrapper = mock (DriverManagerWrapper.class);
	when (driverManagerWrapper.getConnection (expectedUrl))
		.thenReturn (connection);
	DatabaseThingy subject = new DatabaseThingy ();
	subject.driverManagerWrapper = driverManagerWrapper;

	Connection result = subject.makeDatabaseConnection ("billy", 
		"moonwalk");

	ArgumentCaptor<JDBCDriver> captor = ArgumentCaptor.forClass (JDBCDriver.class);
	verify (driverManagerWrapper).registerDriver (captor);
	assertSame (com.awesome.db.JDBCDriver.class, captor.getValue ().getClass ());
	assertSame (connection, result);
}




// ------

class DatabaseThingy {
	DriverManagerWrapper driverManagerWrapper = 
		new DriverManagerWrapper ();

	public Connection makeDatabaseConnection (String username, 
			String password) {
		driverManagerWrapper
			.registerDriver (new com.awesome.db.JDBCDriver ());
		String url = BASE_JDBC_URL + 
			";username=" + username + ";password=" + password;
		return driverManagerWrapper.getConnection (url);	
	}	
}










//------

class DatabaseThingy {
	DriverManagerWrapper driverManagerWrapper = null;

	public Connection makeDatabaseConnection (String username, 
			String password) {
		driverManagerWrapper
			.registerDriver (new com.awesome.db.JDBCDriver ());
		String url = BASE_JDBC_URL + 
			";username=" + username + ";password=" + password;
		return driverManagerWrapper.getConnection (url);	
	}	
}











//------

@Test
public void defaultsCollaborators () {
	DatabaseThingy subject = new DatabaseThingy ();

	assertSame (DriverManagerWrapper.class, 
		subject.driverManagerWrapper.getClass ());
}
















//------
/*

Morals:

1. Write your tests before, not after.

2. Prefer no test to a Really Bad Test.

3. Don't end a test with assertNotNull.

4. Use an instantiable, mockable wrapper to test uses of static methods.

5. Remember to drive your subject to default its collaborators properly.













*/