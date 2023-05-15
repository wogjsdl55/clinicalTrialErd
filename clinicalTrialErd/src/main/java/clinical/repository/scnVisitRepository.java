package clinical.repository;

import clinical.data.scnVisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepository 인터페이스 : 모든 JPA 관련 기능을 사용하고 싶을때 이용한다.
@Repository
public interface scnVisitRepository extends JpaRepository<scnVisitEntity, String> {
}
