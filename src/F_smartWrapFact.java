
class IntegrationObject {

	public IntegrationObject (String name, String value) {
		// stuff
	}

	// more stuff
}















//------

class Example {

	public static final SimpleDateFormat FMT =
			new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");

	public void method (Date date) {
		String minimumDate = FMT.format (date);
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
			new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");

	IntegrationObjectFactory factory = new IntegrationObjectFactory ();

	public void method (Date date) {
		String minimumDate = FMT.format (date);
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
	IntegrationObjectFactory factory = mock (IntegrationObjectFactory.class);
	when (subject.factory.make (anyString (), anyString ()))
		.thenReturn (mock (IntegrationObject.class));
	Example subject = new Example ();
	subject.factory = factory;

	subject.method (FMT.parse ("1941/12/07 06:28:57"));

	verify (factory).make ("notBefore", "1941/12/07 06:28:57");
}




//------

class IntegrationObjectFactory {

	private static final SimpleDateFormat FMT =
			new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");

	public IntegrationObject make (Date date) {
		String minimumDate = FMT.format (date);
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
	IntegrationObjectFactory factory = mock (IntegrationObjectFactory.class);
	when (factory.make (any (Date.class)))
		.thenReturn (mock (IntegrationObject.class));
	Example subject = new Example ();
	subject.factory = factory;

	subject.method (date);

	verify (factory).make (date);
}




//------

class IntegrationObjectFactory {

	public IntegrationObject make (String name, String value) {
		// This line is untestable.
		return new IntegrationObject (name, value);
	}
}

class IntegrationObjectFactoryFacade {

	static final SimpleDateFormat FMT =
			new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");

	IntegrationObjectFactory factory =
			new IntegrationObjectFactory ();

	public IntegrationObject make (Date date) {
		return factory.make ("notBefore", FMT.format (date));
	}
}



//------

@Test
public void defaultsCollaborators () {
	subject = new IntegrationObjectFactoryFacade ();

	assertSame (IntegrationObjectFactory.class, subject.factory);
}

@Test
public void factoryFactoryTranslates () {
	IntegrationObjectFactory factory = mock (IntegrationObjectFactory.class);
	IntegrationObject expectedResult = mock (IntegrationObject.class);
	when (factory.make ("notBefore", "1941/12/07 06:28:57"))
		.thenReturn (expectedResult);
	IntegrationObjectFactoryFacade subject = 
			new IntegrationObjectFactoryFacade ();
	subject.factory = factory;

	IntegrationObject result = subject
		.make (FMT.parse ("1941/12/07 06:28:57"));

	assertSame (expectedResult, result);
}

//------

@Test
public void defaultsCollaborators () {
	Example subject = new Example ();

	assertSame (IntegrationObjectFactoryFacade.class, 
		subject.factory);
}

@Test
public void methodMakesCorrectIntegrationObject () throws Exception {
	IntegrationObjectFactoryFacade facade = 
		mock (IntegrationObjectFactoryFacade.class);
	when (facade.make (any (Date.class)))
		.thenReturn (mock (IntegrationObject.class));
	Example subject = new Example ();
	subject.facade = facade;

	subject.method (date);

	verify (facade).make (date);
}


//------
/*

Morals:

1. Don't combine the responsibility of isolating untestable code with any other.



















*/