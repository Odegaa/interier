package uz.interier.repositories;

import uz.interier.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByFirstName(String firstName);

    User findByFirstName(String firstName);

    User getUserByLastName(String lastName);

    User findByLastName(String lastName);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String email);

}