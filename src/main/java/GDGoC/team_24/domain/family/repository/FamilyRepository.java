package GDGoC.team_24.domain.family.repository;

import GDGoC.team_24.domain.family.domain.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {

}
