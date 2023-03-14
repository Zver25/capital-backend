package space.sviridovskiy.capital.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import space.sviridovskiy.capital.auth.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	Optional<User> findById(long id);
}
