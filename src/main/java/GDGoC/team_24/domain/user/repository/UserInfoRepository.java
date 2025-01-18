package GDGoC.team_24.domain.user.repository;

import GDGoC.team_24.domain.user.domain.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<Userinfo,Long> {
}
