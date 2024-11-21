package Spring_security.Models;

import lombok.Data;

@Data
public class LoginDto {
	
	private String usernameOrEmail;
	private String password;

}
