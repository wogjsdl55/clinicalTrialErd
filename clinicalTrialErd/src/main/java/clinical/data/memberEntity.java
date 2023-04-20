package clinical.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "member")
public class memberEntity {
	
	//Entity 클래스는 실제 DB 테이블과 매핑되는 핵심 클래스로, 데이터베이스의 테이블에 존재하는 컬럼들을 필드로 가지는 객체입니다.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seq;
	
	@Column(name = "user_email")
	private String user_email;
	
	@Column(name = "user_name")
	private String user_name;
	
	@Column(name = "user_pwd")
	private String user_pwd;
	
	public memberEntity(String user_email, String user_name, String user_pwd) {
		super();
		this.user_email = user_email;
		this.user_name = user_name;
		this.user_pwd = user_pwd;
	}
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getUserEmail() {
		return user_email;
	}
	public void setUserEmail(String user_email) {
		this.user_email = user_email;
	}
	public String getUserName() {
		return user_name;
	}
	public void setUserName(String user_name) {
		this.user_name = user_name;
	}
	public String getUserPwd() {
		return user_pwd;
	}
	public void setUserPwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
}
