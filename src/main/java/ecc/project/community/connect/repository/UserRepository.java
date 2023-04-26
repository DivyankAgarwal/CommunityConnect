package ecc.project.community.connect.repository;

import ecc.project.community.connect.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {

}
