package clinical.data;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class bioInfoDto {
	private String apply_enty_name;
	private String approval_time;
	private String goods_name;
	private String clinic_exam_title;
	private String clinic_step_name;
	
	@Builder
	public bioInfoDto(String apply_enty_name, String approval_time, String goods_name, String clinic_exam_title, String clinic_step_name) {
		this.apply_enty_name = apply_enty_name;
		this.approval_time = approval_time;
		this.goods_name = goods_name;
		this.clinic_exam_title = clinic_exam_title;
		this.clinic_step_name = clinic_step_name;
	}
	
//	public memberEntity toEntity() {
//		memberEntity member = memberEntity.builder()
//				.user_email(user_email)
//				.user_name(user_name)
//				.user_pwd(user_pwd)
//				.regdate(regdate)
//				.build();
//		return member;
//	}
}
