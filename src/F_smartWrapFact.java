
class IntegrationObject {

	public IntegrationObject (String name, String value) {
		// stuff
	}

	// more stuff
}















//------

class Example {

	public static final SimpleDateFormat FMT =
			new SimpleDateFormat ("yyyy/dd/MM HH:mm:ss");

	public void method (Date date) {
		Date minimumDate = FMT.format (date);
		IntegrationObject integrationObject = 
			new IntegrationObject ("notBefore", minimumDate);
		// ...use integrationObject...
	}
}











//------

class IntegrationObjectFactory {

	public IntegrationObject make (String name, String value) {
		// This line is untestable
		return new IntegrationObject (name, value);
	}
}

class Example {

	public static final SimpleDateFormat FMT =
			new SimpleDateFormat ("yyyy/dd/MM HH:mm:ss");

	IntegrationObjectFactory factory = new IntegrationObjectFactory ();

	public void method (Date date) {
		Date minimumDate = FMT.format (date);
		IntegrationObject integrationObject = 
			factory.make ("notBefore", minimumDate);
		// use integrationObject
	}
}

//------

@Test
public void defaultsCollaborators () {
	Example subject = new Example ();

	assertSame (IntegrationObjectFactory.class, subject.factory.getClass ());
}

@Test
public void methodMakesCorrectIntegrationObject () throws Exception {
	Example subject = new Example ();
	subject.factory = mock (IntegrationObjectFactory.class);

	subject.method (FMT.parse ("1941/12/07 06:28:57"));

	verify (subject.factory).make ("notBefore", "1941/12/07 06:28:57");
}







//------

class IntegrationObjectFactory {

	private static final SimpleDateFormat FMT =
			new SimpleDateFormat ("yyyy/dd/MM HH:mm:ss");

	public IntegrationObject make (Date date) {
		Date minimumDate = FMT.format (date);
		// This line is untestable
		return new IntegrationObject ("notBefore", minimumDate);
	}
}

class Example {

	IntegrationObjectFactory factory = new IntegrationObjectFactory ();

	public void method (Date date) {
		IntegrationObject integrationObject = 
			factory.make (date);
		// use integrationObject
	}
}

//------

@Test
public void defaultsCollaborators () {
	Example subject = new Example ();

	assertSame (IntegrationObjectFactory.class, subject.factory);
}

@Test
public void methodMakesCorrectIntegrationObject () throws Exception {
	Example subject = new Example ();
	subject.factory = mock (IntegrationObjectFactory.class);

	subject.method (date);

	verify (subject.factory).make (date);
}







//------

class IntegrationObjectFactory {

	public IntegrationObject make (String name, String value) {
		// This line is untestable.
		return new IntegrationObject (name, value);
	}
}

class IntegrationObjectFactoryFactory {

	static final SimpleDateFormat FMT =
			new SimpleDateFormat ("yyyy/dd/MM HH:mm:ss");

	IntegrationObjectFactory factory =
			new IntegrationObjectFactory ();

	public IntegrationObject make (Date date) {
		return factory.make ("notBefore", FMT.format (date));
	}
}



//------

@Test
public void defaultsCollaborators () {
	subject = new IntegrationObjectFactoryFactory ();

	assertSame (IntegrationObjectFactory.class, subject.factory);
}

@Test
public void factoryFactoryTranslates () {
	IntegrationObjectFactoryFactory subject = 
			new IntegrationObjectFactoryFactory ();
	subject.factory = mock (IntegrationObjectFactory.class);
	IntegrationObject expectedResult = mock (IntegrationObject.class);
	when (subject.factory.make ("notBefore", "1941/12/07 06:28:57"))
		.thenReturn (expectedResult);

	IntegrationObject result = subject
		.make (FMT.parse ("1941/12/07 06:28:57"));

	assertSame (expectedResult, result);
}


//------

@Test
public void defaultsCollaborators () {
	Example subject = new Example ();

	assertSame (IntegrationObjectFactoryFactory.class, 
		subject.factory);
}

@Test
public void methodMakesCorrectIntegrationObject () throws Exception {
	Example subject = new Example ();
	subject.factory = mock (IntegrationObjectFactoryFactory.class);

	subject.method (date);

	verify (subject.factory).make (date);
}






//------
/*

Morals:

1. Don't combine the responsibility of isolating untestable code with any other.



















*/