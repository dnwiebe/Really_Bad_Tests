
class ExampleMaker {

	public PenetrableThing makePenetrable (int oneParam) {
		return new PenetrableThing (oneParam, "anotherParam");
	}

	public ObservableThing makeObservable (int oneParam) {
		return new ObservableThing (oneParam, "anotherParam");
	}

	public ImpenetrableThing makeImpenetrable (int oneParam) {
		return new ImpenetrableThing (oneParam, "anotherParam");
	}
}









//------

@Test
public void makesPenetrableThing () {

	PenetrableThing result = subject.makePenetrable (42);

	assertEquals (42, result.getOneParam ());
	assertEquals ("anotherParam", result.getAnotherParam ());
}















//------

@Test
public void makesObservableThing () {

	ObservableThing result = subject.makeObservable (42);

	assert ("anotherParam - 42", result.behave ());
}
















//------

@Test
public void makesImpenetrableThing () {

	ImpenetrableThing result = subject.makeImpenetrable (42);

	// ...uhhh...

















//------

class ExampleMaker {
	static class ImpenetrableThingFactory {
		public ImpenetrableThing make (int oneParam, String anotherParam) {
			// this line is untestable
			return new ImpenetrableThing (oneParam, anotherParam);
		}
	}

	ImpenetrableThingFactory factory = new ImpenetrableThingFactory ();

	public PenetrableThing makePenetrable (int oneParam) {
		return new PenetrableThing (oneParam, "anotherParam");
	}

	public ObservableThing makeObservable (int oneParam) {
		return new ObservableThing (oneParam, "anotherParam");
	}

	public ImpenetrableThing makeImpenetrable (int oneParam) {
		return factory.make (oneParam, "anotherParam");
	}
}

//------

@Test
public void makesImpenetrableThing () {
	ImpenetrableThingFactory factory = mock (ImpenetrableThingFactory.class);
	ImpenetrableThing impenetrableThing = mock (ImpenetrableThing.class);
	when (factory.make (42, "anotherParam"))
		.thenReturn (impenetrableThing);
	ExampleMaker subject = new ExampleMaker ();
	subject.factory = factory;

	ImpenetrableThing result = subject.makeImpenetrable (42);

	assertSame (impenetrableThing, result);
}

@Test
public void defaultsCollaborators () {
	ExampleMaker subject = new ExampleMaker ();

	assertSame (ImpenetrableThingFactory.class,
		subject.factory.getClass ());
}



//------
/*

Morals:

1. Prefer behavioral testing to implementation testing.

2. Use an instantiable, mockable factory to test constructor calls.

















*/