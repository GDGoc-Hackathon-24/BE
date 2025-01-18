
package GDGoC.team_24.domain.user.repository;

import GDGoC.team_24.domain.family.domain.Family;
import GDGoC.team_24.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByPhoneNumber(String phoneNumber);
    @Query("SELECT u from User u WHERE u.family = :family")
    User findByFamily(@Param("family")Family family);

    boolean existsByPhoneNumber(String phoneNumber);


}

