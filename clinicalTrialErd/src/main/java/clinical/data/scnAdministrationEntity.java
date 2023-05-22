package clinical.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "administration")
@EntityListeners(AuditingEntityListener.class)
public class scnAdministrationEntity {
	
	//Entity 클래스는 실제 DB 테이블과 매핑되는 핵심 클래스로, 데이터베이스의 테이블에 존재하는 컬럼들을 필드로 가지는 객체입니다.
	@Id
	private String user_email;
	private String injection_yn;
	private Date injection_start_date;
	private LocalTime injection_start_time;
	private Date injection_end_date;
	private LocalTime injection_end_time;
	private Date injection_date;
	private String unit;
	private String injection_cnt;
	private String route;
	private String rate_change;
	private String rate_reason;
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime regdate;

}
