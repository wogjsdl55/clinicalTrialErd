package clinical.data;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "page_setting")
@EntityListeners(AuditingEntityListener.class)
public class pageSettingEntity {
	
	//Entity 클래스는 실제 DB 테이블과 매핑되는 핵심 클래스로, 데이터베이스의 테이블에 존재하는 컬럼들을 필드로 가지는 객체입니다.	
	@Id
	private String page_name;
	
	private String page_type_01;

	private String page_type_02;
	
	private String page_type_03;
	
	private String page_type_04;
	
	private String page_type_05;
}
