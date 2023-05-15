package clinical.data;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class scnVisitDto {
	private Integer visit_seq;
	private String user_email;
	private Date visit_date;

	@Builder
	public scnVisitDto(int visit_seq, String user_email, Date visit_date) {
		this.visit_seq = visit_seq;
		this.user_email = user_email;
		this.visit_date = visit_date;
	}
	
	public scnVisitEntity toEntity() {
		scnVisitEntity visit = scnVisitEntity.builder()
				.visit_seq(visit_seq)
				.user_email(user_email)
				.visit_date(visit_date)
				.build();
		return visit;
	}

	//entity -> dto
	public scnVisitDto scnVisitToDto(scnVisitEntity visit) {
		return scnVisitDto.builder()
				.visit_seq(visit.getVisit_seq())
				.user_email(visit.getUser_email())
				.visit_date(visit.getVisit_date())
				.build();
	}
}
