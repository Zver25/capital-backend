package space.sviridovskiy.capital.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import space.sviridovskiy.capital.auth.domain.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {
	User create(User user);
	void changePassword(User user, String newPassword);
	void changeFullname(String username, String fullname);
	Optional<User> findByUsername(String username);
	Optional<User> findByUsernameAndPassword(String username, String password);
}
