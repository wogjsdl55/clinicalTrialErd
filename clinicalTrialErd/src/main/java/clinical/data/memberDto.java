package clinical.data;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class memberDto {
	private String user_email;
	private String user_name;
	private String user_pwd;
	private Date user_regdate;
	
	
	@Builder
	public memberDto(String user_email, String user_name, String user_pwd, Date user_regdate) {
		this.user_email = user_email;
		this.user_name = user_name;
		this.user_pwd = user_pwd;
		this.user_regdate = user_regdate;
	}
	
//	public member toEntity() {
//		return member.builder()
//				.user_email(user_email)
//				.user_email(user_email)
//				.user_email(user_email)
//				.build();
//	}
}
