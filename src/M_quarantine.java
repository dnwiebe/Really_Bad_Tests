
public class MyThing extends Thing {

	@Override
	public void onEvent (Parameter param) {
		doOneThing (param, true);
		doAnotherThing (param, false);
	}
}















//------

@Test
public void handlesEvent () {
	Parameter param = mock (Parameter.class);
	MyThing subject = new MyThing ();
	MyThing subjectSpy = spy (subject);

	subjectSpy.onEvent (param);

	verify (subjectSpy).doOneThing (param, true);
	verify (subjectSpy).doAnotherThing (param, false);
}












//------

public class MyThingQtn extends Thing {
	MyThingImpl impl = new MyThingImpl ();

	@Override
	public void onEvent (Parameter param) {
		impl.onEvent (param);
	}
}

public class MyThingImpl {

	public void onEvent (Parameter param, MyThingQtn qtn) {
		qtn.doOneThing (param, true);
		qtn.doAnotherThing (param, false);
	}
}







//------

public class MyThingQtnTest {
	@Test
	public void onEventDelegates () {
		Parameter param = mock (Parameter.class);
		MyThingQtn subject = new MyThingQtn ();
		subject.impl = mock (MyThingImpl.class);

		subject.onEvent (param);

		verify (subject.impl).onEvent (param);
	}
}

public class MyThingImplTest {
	@Test
	public void handlesEvent () {
		Parameter param = mock (Parameter.class);
		MyThingQtn qtn = mock (MyThingQtn.class);
		MyThingImpl subject = new MyThingImpl ();

		subject.onEvent (param, qtn);

		verify (qtn).doOneThing (param, true);
		verify (qtn).doAnotherThing (param, false);
	}
}

//------
/*

Morals:

1. Split a difficult class down the middle; then one hand washes the other.

2. Who noticed the test we missed?













*/