
class SuccessEvent {}
class FailureEvent {

	public final Exception exception;

	public FailureEvent (Exception exception) {
		this.exception = exception;
	}
}














//------

public class BusTransmitter {
	private EventBus bus;
	public BusTransmitter (EventBus bus) {
		this.bus = bus;
	}

	public void doAThing (ThingToDo toDo) {
		try {
			toDo.do ();
			bus.post (new SuccessEvent ());
		}
		catch (Exception exception) {
			bus.post (new FailureEvent (exception))
		}
	}
}







//------

@Test
public void successResultsInSuccessIndicator () {
	EventBus bus = mock (EventBus.class);
	ThingToDo toDo = mock (ThingToDo.class);
	BusTransmitter subject = new BusTransmitter (bus);

	subject.doAThing (toDo);

	ArgumentCaptor<Object> captor = ArgumentCaptor.forClass (Object.class);
	verify (bus).post (captor.capture ());
	assertSame (SuccessEvent.class, captor.getValue ().getClass ());
}











//------

@Test
public void failureResultsInFailureIndicator () {
	EventBus bus = mock (EventBus.class);
	ThingToDo toDo = mock (ThingToDo.class);
	when (toDo.do ()).thenThrow (new Exception ());
	BusTransmitter subject = new BusTransmitter (bus);

	subject.doAThing (toDo);

	ArgumentCaptor<Object> captor = ArgumentCaptor.forClass (Object.class);
	verify (bus).post (captor.capture ());
	assertSame (FailureEvent.class, captor.getValue ().getClass ());
}










//------

public class BusTransmitter {
	private EventBus bus;
	public BusTransmitter (EventBus bus) {
		this.bus = bus;
	}

	public void doAThing (ThingToDo toDo) {
		try {
			toDo.do ();
			bus.post (new SuccessEvent ());
		}
		catch (Exception exception) {
			bus.post (new FailureEvent (null)) // null!
		}
	}
}







//------

@Test
public void failureResultsInFailureIndicator () {
	EventBus bus = mock (EventBus.class);
	ThingToDo toDo = mock (ThingToDo.class);
	when (toDo.do ()).thenThrow (new Exception ("booga"));
	BusTransmitter subject = new BusTransmitter (bus);

	subject.doAThing (toDo);

	ArgumentCaptor<Object> captor = ArgumentCaptor.forClass (Object.class);
	verify (bus).post (captor.capture ());
	Exception exception = (FailureEvent)captor.getValue ().exception;
	assertEquals ("booga", exception.getMessage ());
}









//------
/*

Morals:

1. Always watch your test fail before you make it pass.

2. Asserting on a type only works if the type holds no state.



















*/