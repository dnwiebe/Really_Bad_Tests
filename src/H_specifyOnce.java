
public class Converter {
	public HighLevelDto convert (LowLevelDto input) {
		return new HighLevelDto (
			input.getSecondValue (),
			input.getFirstValue ()
		)
	}
}















//------

@Test
public void convertConverts () {
	LowLevelDto input = mock (LowLevelDto.class);
	input.setFirstValue ("first input value");
	input.setSecondValue ("second input value");
	Converter subject = new Converter ();

	HighLevelDto result = subject.convert (input);

	assertEquals (input.getSecondValue (), result.getFirstValue ());
	assertEquals (input.getFirstValue (), result.getSecondValue ());
}











//------

public class Converter {
	public HighLevelDto convert (LowLevelDto input) {
		return new HighLevelDto (null, null);
	}
}


















//------

@Test
public void convertConverts () {
	LowLevelDto input = mock (LowLevelDto.class);
	input.setFirstValue ("first input value");
	input.setSecondValue ("second input value");
	Converter subject = new Converter ();

	HighLevelDto result = subject.convert (input);

	assertEquals ("second input value", result.getFirstValue ());
	assertEquals ("first input value", result.getSecondValue ());
}











//------

@Test
public void convertConverts () {
	LowLevelDto input = new LowLevelDto ();
	input.setFirstValue ("first input value");
	input.setSecondValue ("second input value");
	Converter subject = new Converter ();

	HighLevelDto result = subject.convert (input);

	assertEquals ("second input value", result.getFirstValue ());
	assertEquals ("first input value", result.getSecondValue ());
}











//------
/*

Morals:

1. Always watch your test fail before you make it pass.

2. Assert on the data that goes into your inputs, not on data that
	comes out of them.
















*/