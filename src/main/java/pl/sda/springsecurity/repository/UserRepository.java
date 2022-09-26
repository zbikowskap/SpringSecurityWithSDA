package pl.sda.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.springsecurity.model.User;

@Repository
public interface UserRepository extends JpaRepository {
    User findByUsername(String username);
}
