
public class MyThing extends Thing {

	@Override
	public void onEvent (Parameter param) {
		this.doOneThing (param, true);
		this.doAnotherThing (param, false);
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
		impl.onEvent (this, param);
	}
}

public class MyThingImpl {

	public void onEvent (MyThingQtn qtn, Parameter param) {
		qtn.doOneThing (param, true);
		qtn.doAnotherThing (param, false);
	}
}







//------

public class MyThingQtnTest {
	@Test
	public void onEventDelegates () {
		MyThingQtn subject = new MyThingQtn ();
		Parameter param = mock (Parameter.class);
		subject.impl = mock (MyThingImpl.class);

		subject.onEvent (param);

		verify (subject.impl).onEvent (subject, param);
	}
}

public class MyThingImplTest {
	@Test
	public void handlesEvent () {
		MyThingQtn qtn = mock (MyThingQtn.class);
		Parameter param = mock (Parameter.class);
		MyThingImpl subject = new MyThingImpl ();

		subject.onEvent (qtn, param);

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