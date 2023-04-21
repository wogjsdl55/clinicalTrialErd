package clinical.data;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "member")
public class memberEntity {
	
	//Entity 클래스는 실제 DB 테이블과 매핑되는 핵심 클래스로, 데이터베이스의 테이블에 존재하는 컬럼들을 필드로 가지는 객체입니다.	
	@Id
	private String user_email;
	
	private String user_name;

	private String user_pwd;
	
	private Date user_regdate;
	
	//repository 를 통해 조회한 entity 를 dto 로 변환 용도
	@Builder
	public memberEntity(String user_email, String user_name, String user_pwd, Date user_regdate) {
		this.user_email = user_email;
		this.user_name = user_name;
		this.user_pwd = user_pwd;
		this.user_regdate = user_regdate;
	}
	
}
