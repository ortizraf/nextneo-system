package com.nextneo.system.utils.path;

/**
* @author  Rafael M Ortiz
*/
public class ResourcePath {

	public final static String USER = "/user";
	public final static String LOGIN = "/login";

	public class User {

		public final static String FIND_BY_ID = "findById/{id}";
		public final static String FIND_BY_USERNAME = "findByUsername/{username}";

	}
	
	public class Login {
		
		public final static String DO_LOGIN = "doLogin";

	}

}
