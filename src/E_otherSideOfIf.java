
class AccountManipulator {
	public void transferFunds (Account fromAccount, Account toAccount, int amount) {
		if (fromAccount.getBalance () < amount) {
			throw new IllegalArgumentException (
				"Cannot transfer: insufficient funds"
			);
		}
		else {
			fromAccount.addFunds (-amount);
			toAccount.addFunds (amount);
		}
	}
}










//------

@Test
public void canTransferWhenFundsAreSufficient () {
	Account fromAccount = new Account (1000);
	Account toAccount = new Account (0);
	AccountManipulator subject = new AccountManipulator ();

	subject.transferFunds (fromAccount, toAccount, 1000);

	assertEquals (0, fromAccount.getBalance ());
	assertEquals (1000, toAccount.getBalance ());
}












//------

public void transferFunds (Account fromAccount, Account toAccount, int amount) {
	if (fromAccount.getBalance () < amount) {





















//------

public void transferFunds (Account fromAccount, Account toAccount, 
		int amount) {
	fromAccount.addFunds (-amount);
	toAccount.addFunds (amount);
}


















//------

@Test
public void cannotTransferWhenFundsAreInsufficient () {
	Account fromAccount = new Account (999);
	Account toAccount = new Account (0);
	AccountManipulator subject = new AccountManipulator ();

	try {
		subject.transferFunds (fromAccount, toAccount, 1000);
		fail ();
	}
	catch (IllegalArgumentException e) {
		assertEquals ("Cannot transfer: insufficient funds", 
			e.getMessage ());
	}
}








// ------

class AccountManipulator {
	public void transferFunds (Account fromAccount, Account toAccount, int amount) {
		if (fromAccount.getBalance () < amount) {
			throw new IllegalArgumentException (
				"Cannot transfer: insufficient funds"
			);
		}
		else {
			fromAccount.addFunds (-amount);
			toAccount.addFunds (amount);
		}
	}
}










//------

public void transferFunds (Account fromAccount, Account toAccount, 
		int amount) {
	if (fromAccount.getBalance () < amount) {
		throw new UnsupportedOperationException ("Test-drive me");
	}
	else {
		fromAccount.addFunds (-amount);
		toAccount.addFunds (amount);
	}
}













//------

class AccountManipulator {
	public void transferFunds (Account fromAccount, Account toAccount, 
			int amount) {
		if (fromAccount.getBalance () < amount) {
			Utils.TEST_DRIVE_ME ("What about nonsufficient funds?");
		}
		else {
			fromAccount.addFunds (-amount);
			toAccount.addFunds (amount);
		}
	}
}











//------
/*

Morals:

1. Write your if early, but throw an exception from the untested branch.



















*/