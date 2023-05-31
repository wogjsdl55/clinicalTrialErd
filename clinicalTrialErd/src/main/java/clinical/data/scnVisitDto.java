package clinical.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
public class scnVisitDto {
	private String user_email;
	private LocalDate visit_date;

	@Builder
	public scnVisitDto(String user_email, LocalDate visit_date) {
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
