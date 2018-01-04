
@Test
public void onViewCreatedAttachesEditorActionListener () {
	View view = mock (View.class);
	EditText firstName = mock (EditText.class);
	when (view.findViewById (R.id.first_name)).thenReturn (firstName);
	MyFragment subject = new MyFragment ();

	subject.onViewCreated (view, null);

	verify (firstName).setOnEditorActionListener (
		any (OnEditorActionListener.class)
	);
}










// ------

@Test
public void firstNameClearsFocusOnEditorDoneButNotAnythingElse () {
	View view = mock (View.class);
	EditText firstName = mock (EditText.class);
	when (view.findViewById (R.id.first_name)).thenReturn (firstName);
	MyFragment subject = new MyFragment ();

	subject.onViewCreated (view, null);

	ArgumentCaptor<OnEditorActionListener> captor = 
		ArgumentCaptor.forClass (OnEditorActionListener.class);
	verify (firstName).setOnEditorActionListener (captor.capture ());
	OnEditorActionListener listener = captor.getValue ();

	checkFirstNameListenerWithIME_ACTION_DONE (listener, firstName);
	checkFirstNameListenerWithNonIME_ACTION_DONE (listener, firstName);
}






//------

private void checkFirstNameListenerWithIME_ACTION_DONE (
	OnEditorActionListener listener,
	EditText firstName
) {
	reset (firstName);

	listener.onEditorAction (null, EditorInfo.IME_ACTION_DONE, null);

	verify (firstName).clearFocus ();
}

private void checkFirstNameListenerWithNonIME_ACTION_DONE (
	OnEditorActionListener listener,
	EditText firstName
) {
	reset (firstName);

	listener.onEditorAction (null, EditorInfo.IME_ACTION_SEND, null);

	verify (firstName, never ()).clearFocus ();
}


//------
/*

Morals:

1. Don't leave tests unfinished.  Remove them instead.

2. Test callbacks by capturing them and calling them under controlled conditions.

















*/