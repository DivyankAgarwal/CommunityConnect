package ecc.project.community.connect.repository;

import ecc.project.community.connect.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT password FROM user where email=?1", nativeQuery = true)
    String getPasswordByEmail(String email);

}
