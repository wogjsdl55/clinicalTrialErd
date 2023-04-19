package Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "member")
public class memberEntity {

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
	
	public long getSeq() {
		return seq;
	}
	public void setId(int seq) {
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
