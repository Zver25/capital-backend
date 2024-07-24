package space.sviridovskiy.capital.auth.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import space.sviridovskiy.capital.auth.domain.User;
import space.sviridovskiy.capital.auth.repository.UserRepository;
import space.sviridovskiy.capital.auth.service.UserService;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	@Override
	public User create(User user) {
		user.setPassword(encodePassword(user.getPassword()));

		return userRepository.save(user);
	}

	@Override
	public void changePassword(User user, String newPassword) {
		user.setPassword(encodePassword(newPassword));

		userRepository.save(user);
	}

	@Override
	public void changeFullname(String username, String fullname) {
		final User user = findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
		user.setFullname(fullname);

		userRepository.save(user);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Optional<User> findByUsernameAndPassword(String username, String password) {
		final Optional<User> optionalUser = findByUsername(username);

		return optionalUser.filter(user -> passwordEncoder.matches(password, user.getPassword()));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
	}
}
