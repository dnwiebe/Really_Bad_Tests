
class ServiceContext {

	private String sessionId;
	private String sessionToken;
	private String accountNumber;

	// ...getters and setters...
}















//------

class DistantIsolatedLogoutHandler {

	public void logout (ServiceContext context) {
		context.setSessionId (null);
		context.setSessionToken (null);
		context.setAccountNumber (null);
	}
}















//------

@Test
public void logoutClearsServiceContext () {
	ServiceContext context = new ServiceContext ();
	context.setSessionId ("sessionId");
	context.setSessionToken ("sessionToken");
	context.setAccountNumber ("accountNumber");
	DistantIsolatedLogoutHandler subject =
		new DistantIsolatedLogoutHandler ();

	subject.logout (context);

	assertNull (context.getSessionId ());
	assertNull (context.getSessionToken ());
	assertNull (context.setAccountNumber ());
}








//------

class ServiceContext {

	private String sessionId;
	private String sessionToken;
	private String accountNumber;
	private String encryptionKey; // new

	// ...getters and setters...
}














//------

@Test
public void logoutClearsServiceContext () {
	ServiceContext context = new ServiceContext ();
	for (Field field : context.getClass ().getFields ()) {
		field.setAccessible (true);
		field.set (context, "booga");
	}

	Utils.logout (context);

	for (Field field : context.getClass ().getFields ()) {
		assertNull (field.get (context));
	}
}









//------

class ServiceContext {

	Map<String, String> data = new HashMap<> ();

	public ServiceContext () {
		data.put ("sessionId", null);
		data.put ("sessionToken", null);
		// etc.
	}

	public String getSessionId () {
		return data.get ("sessionId");
	}

	public void setSessionId (String value) {
		data.put ("sessionId", value);
	}

	// etc.
}



// ------

@Test
public void logoutClearsServiceContext () {
	ServiceContext context = new ServiceContext ();
	for (String key : context.data.keySet ()) {
		context.data.put (key, "booga");
	}

	Utils.logout (context);

	for (String key : context.data.keySet ()) {
		assertNull (context.data.get (key));
	}
}










//------
/*

Morals:

1. If you're testing the same thing about a series of fields, find a way to
	loop through them instead of addressing them one by one.

















	
*/