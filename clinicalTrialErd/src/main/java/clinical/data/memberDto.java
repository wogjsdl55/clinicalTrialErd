package clinical.data;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class memberDto {
	private String user_email;
	private String user_name;
	private String user_pwd;
	private LocalDateTime regdate;
	
	@Builder
	public memberDto(String user_email, String user_name, String user_pwd, LocalDateTime regdate) {
		this.user_email = user_email;
		this.user_name = user_name;
		this.user_pwd = user_pwd;
		this.regdate = regdate;
	}
	
	public memberEntity toEntity() {
		memberEntity member = memberEntity.builder()
				.user_email(user_email)
				.user_name(user_name)
				.user_pwd(user_pwd)
				.regdate(regdate)
				.build();
		return member;
	}
}
