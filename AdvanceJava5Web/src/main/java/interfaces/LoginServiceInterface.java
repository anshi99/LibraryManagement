package interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginServiceInterface {
	
	public boolean verifyUser(String username, String password, HttpServletRequest request,
			HttpServletResponse response);

}
