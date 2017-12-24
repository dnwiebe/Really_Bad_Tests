
((TextView)view.findViewById (R.id.phone_number))
	.setText ("(555) 867-5309");





















//------

@Test
public void textViewDoesTheCasting () {
	View view = mock (View.class);
	TextView textView = mock (TextView.class);
	when (view.findViewById (R.id.phone_number)).thenReturn (textView);

	TextView result = Utils.textView (view, R.id.phone_number);

	assertSame (textView, result);
}













//------

class Utils {

	public static TextView textView (View view, @IdRes int id) {
		return (TextView)view.findViewById (R.id.phone_number);
	}
}

















//------

@Test
public void textViewDoesTheCasting () {
	@IdRes int textViewId = 1234;
	View view = mock (View.class);
	TextView textView = mock (TextView.class);
	when (view.findViewById (textViewId)).thenReturn (textView);

	TextView result = Utils.textView (view, textViewId);

	assertSame (textView, result);
}












//------

class Utils {

	public static TextView textView (View view, @IdRes int id) {
		@IdRes int textViewId = 1234;
		return (TextView)view.findViewById (textViewId);
	}
}
















//------
/*

Morals:

1. Always watch your test fail before you make it pass.

2. Always use obviously fake data for tests unless you can't.



















*/