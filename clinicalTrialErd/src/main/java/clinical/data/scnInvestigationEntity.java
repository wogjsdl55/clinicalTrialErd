package clinical.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "investigation")
@EntityListeners(AuditingEntityListener.class)
public class scnInvestigationEntity {
	
	//Entity 클래스는 실제 DB 테이블과 매핑되는 핵심 클래스로, 데이터베이스의 테이블에 존재하는 컬럼들을 필드로 가지는 객체입니다.
	@Id
	private String user_email;
	private String age;
	private LocalDate birthday;
	private String ethnic;
	private String gender;
	private String races;
	private LocalTime time_birth;
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime regdate;

}
