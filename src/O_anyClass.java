
class SuccessIndicator {}
class FailureIndicator {}

public class BusTransmitter {

	private EventBus bus;

	public BusTransmitter (EventBus bus) {
		this.bus = bus;
	}

	public void doAThing (ThingToDo toDo) {
		bus.post (toDo.do () ?
			new SuccessIndicator () :
			new FailureIndicator ())
	}
}






//------

@Test
public void successResultsInSuccessIndicator () {
	EventBus bus = mock (EventBus.class);
	ThingToDo toDo = mock (ThingToDo.class);
	when (toDo.do ()).thenReturn (true);
	BusTransmitter subject = new BusTransmitter (bus);

	subject.doAThing (toDo)

	verify (bus).post (any (SuccessIndicator.class));
}

@Test
public void failureResultsInFailureIndicator () {
	EventBus bus = mock (EventBus.class);
	ThingToDo toDo = mock (ThingToDo.class);
	when (toDo.do ()).thenReturn (false);
	BusTransmitter subject = new BusTransmitter (bus);

	subject.doAThing (toDo)

	verify (bus).post (any (FailureIndicator.class));
}

//------


class SuccessIndicator {}
class FailureIndicator {}

public class BusTransmitter {

	private EventBus bus;

	public BusTransmitter (EventBus bus) {
		this.bus = bus;
	}

	public void doAThing (ThingToDo toDo) {
		bus.post ("booga");
	}
}






//------

@Test
public void successResultsInSuccessIndicator () {
	EventBus bus = mock (EventBus.class);
	ThingToDo toDo = mock (ThingToDo.class);
	when (toDo.do ()).thenReturn (true);
	BusTransmitter subject = new BusTransmitter (bus);

	subject.doAThing (toDo);

	ArgumentCaptor<Object> captor = ArgumentCaptor.forClass (Object.class);
	verify (bus).post (captor.capture ());
	assertSame (SuccessIndicator.class, captor.getValue ().getClass ());
}







//------

public class BusTransmitter {

	public static final SUCCESS_INDICATOR = new SuccessIndicator ();
	public static final FAILURE_INDICATOR = new FailureIndicator ();
	
	private EventBus bus;
	
	public BusTransmitter (EventBus bus) {
		this.bus = bus;
	}

	public void doAThing (ThingToDo toDo) {
		bus.post (toDo.do () ?
			SUCCESS_INDICATOR :
			FAILURE_INDICATOR)
	}

	private static class SuccessIndicator {}
	private static class FailureIndicator {}
}



//------

@Test
public void successResultsInSuccessIndicator () {
	EventBus bus = mock (EventBus.class);
	ThingToDo toDo = mock (ThingToDo.class);
	when (toDo.do ()).thenReturn (true);
	BusTransmitter subject = new BusTransmitter (bus);

	subject.doAThing (toDo);

	verify (bus).post (SUCCESS_INDICATOR);
}














//------
/*

Morals:

1. Always watch your test fail before you make it pass.

2. org.mockito.Matchers.any() doesn't pay any runtime attention to its parameter.



















*/