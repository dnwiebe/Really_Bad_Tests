
public class GreetingGeneratorTest {
	private Clock clock = mock (Clock.class);

	@Test
	public void handlesEndOfMorning () {
		when (clock.secondsSinceMidnight ())
			.thenReturn (43199); // 11:59:59 AM
		subject = new GreetingGenerator (clock);

		String result = subject.generate ();

		assertEquals ("Good morning", result);
	}

	@Test
	public void handlesBeginningOfAfternoon () {
		when (clock.secondsSinceMidnight ())
			.thenReturn (43200); // 12:00:00 PM
		subject = new GreetingGenerator (clock);

		String result = subject.generate ();

		assertEquals ("Good afternoon", result);
	}
}

//------

public class GreetingGeneratorTest {
	private Clock clock;
	
	@Before
	public void setup () {
		clock = mock (Clock.class);
	}

	@Test
	public void handlesEndOfMorning () {
		when (clock.secondsSinceMidnight ())
			.thenReturn (43199); // 11:59AM
		subject = new GreetingGenerator (clock);

		String result = subject.generate ();

		assertEquals ("Good morning", result);
	}

	@Test
	public void handlesBeginningOfAfternoon () {
		when (clock.secondsSinceMidnight ())
			.thenReturn (43200); // 12:00PM
		subject = new GreetingGenerator (clock);

		String result = subject.generate ();

		assertEquals ("Good afternoon", result);
	}
}

//------
/*

Morals:

1. DECLARE mocks at the class level, if you like.  But always INITIALIZE them 
	at the instance method level.

















*/