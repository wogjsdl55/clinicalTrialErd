package clinical.repository;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import clinical.data.memberEntity;

//JpaRepository 인터페이스 : 모든 JPA 관련 기능을 사용하고 싶을때 이용한다.
@Repository
public interface memberRepository extends JpaRepository<memberEntity, Integer> {
	//@Query("SELECT user_email, user_name, user_pwd FROM member m")
    //Stream<memberEntity> streamAll();
	//List<memberEntity> findByEmail(String user_email);
}