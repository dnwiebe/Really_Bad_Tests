
public class SomethingWithAFactory {

	ThingFactory factory = new ThingFactory ();
	Thing thing;

	public void init (int oneParam) {
		thing = factory.make (oneParam, "anotherParam");
		// ...use thing to do stuff...
	}

	// ...more stuff...
}











// ------

public class SomethingWithAFactoryTest {

	@Mock private ThingFactory factory;
	@Mock private Thing thing;
	@InjectMocks private SomethingWithAFactory subject;

	@Before
	public void setup () {
		injectMocks (this);

		when (factory.make (42, "anotherParam")).thenReturn (thing);
	}

	@Test
	public void initInstantiatesThing () { // oversimplified
		subject.init (42);

		assertSame (thing, subject.thing);
	}
}



//------

public class SomethingWithAFactory {

	ThingFactory factory = new ThingFactory ();
	Thing thing;

	public void init (int oneParam) {
		// nothing
	}

	// ...more stuff...
}












//------

public class SomethingWithAFactoryTest {

	@Mock private ThingFactory factory;
	@InjectMocks private SomethingWithAFactory subject;

	private Thing thing; // sigh...

	@Before
	public void setup () {
		injectMocks (this);
		thing = mock (Thing.class); // sigh...
		when (factory.make (42, "anotherParam")).thenReturn (thing);
	}

	@Test
	public void initInstantiatesThing () {
		subject.init (42);

		assertSame (thing, subject.thing);
	}
}


//------
/*

Morals:

1. Always watch your test fail before you make it pass.

2. Never use @Mock for something that's initialized later than the constructor.



















*/