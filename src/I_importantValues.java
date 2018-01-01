
class EncryptedDataStoreAccessor {

	static final byte[] SALT = {
		101, 56, -120, 91, -83, -4, -81, 8,
		-112, -120, 78, -27, 5, 106, 119, 45
	};

	public String getValue (String name) {
		// use SALT to decrypt value from datastore
	}

	public void setValue (String name, String value) {
		// use SALT to encrypt value into datastore
	}
}








//------

class EncryptedDataStoreAccessor {

	static final byte[] SALT = {
		101, 56, -120, 91, -83, 4, -81, 8,
		-112, -120, 78, -27, 5, 106, 119, 45
	};

	public String getValue (String name) {
		// use SALT to decrypt value from datastore
	}

	public void setValue (String name, String value) {
		// use SALT to encrypt value into datastore
	}
}








//------

@Test
public void weHaventJustHosedOurUserBase () {
	assertArrayEquals (
		new byte[] {
			101, 56, -120, 91, -83, -4, -81, 8,
			-112, -120, 78, -27, 5, 106, 119, 45
		},
		EncryptedDataStoreAccessor.SALT
	);
}













//------
/*

Morals:

1. Test behavior.

2. Don't test data--UNLESS ITS VALUE IS IMPORTANT.

















*/