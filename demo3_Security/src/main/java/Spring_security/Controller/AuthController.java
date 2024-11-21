package Spring_security.Controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.apache.coyote.http11.Http11InputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Spring_security.Entity.Role;
import Spring_security.Entity.Users;
import Spring_security.Models.LoginDto;
import Spring_security.Models.SignUpDto;
import Spring_security.Repository.RoleRepository;
import Spring_security.Repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/signin")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser (@RequestBody SignUpDto signUpDto){
		if(userRepository.existsByUsername(signUpDto.getUsername())) {
			return new ResponseEntity<>("Username is already taken!",HttpStatus.BAD_REQUEST);
		}
		
		if(userRepository.existsByEmail(signUpDto.getEmail())) {
			return new ResponseEntity<>("Email is already taken!",HttpStatus.BAD_REQUEST);
		}
		
		Users user = new Users();
		user.setName(signUpDto.getName());
		user.setUsername(signUpDto.getUsername());
		user.setEmail(signUpDto.getEmail());
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		
	    Optional<javax.management.relation.Role> roleOptional = roleRepository.findByName("USER");

	    if (!roleOptional.isPresent()) {
	        return new ResponseEntity<>("Role 'USER' not found!", HttpStatus.BAD_REQUEST);
	    }

	    Role role = roleOptional.get(); // Lấy Role "USER" nếu tồn tại
	    user.setRoles(Collections.singleton(role)); // Gán Role cho user

	    // Lưu người dùng vào cơ sở dữ liệu
	    userRepository.save(user);
		
		return new ResponseEntity<>("Email is already taken!",HttpStatus.OK);
		
	}
}
