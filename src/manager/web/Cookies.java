package manager.web;

import javax.servlet.http.Cookie;

public class Cookies {

	private final Cookie[] cookies;

	public Cookies(Cookie[] cookies) {
		this.cookies = cookies;

	}

	public Cookie searchLoggedUser() {
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user.loggedin")) {
				return cookie;
			}
		}

		return null;
	}

}
