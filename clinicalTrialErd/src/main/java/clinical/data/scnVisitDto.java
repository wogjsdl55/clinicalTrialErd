package clinical.data;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class scnVisitDto {
	private String user_email;
	private Date visit_date;

	@Builder
	public scnVisitDto(String user_email, Date visit_date) {
		this.user_email = user_email;
		this.visit_date = visit_date;
	}
	
	public scnVisitEntity toEntity() {
		scnVisitEntity visit = scnVisitEntity.builder()
				.user_email(user_email)
				.visit_date(visit_date)
				.build();
		return visit;
	}

	//entity -> dto
	public scnVisitDto scnVisitToDto(scnVisitEntity visit) {
		return scnVisitDto.builder()
				.user_email(visit.getUser_email())
				.visit_date(visit.getVisit_date())
				.build();
	}
}
