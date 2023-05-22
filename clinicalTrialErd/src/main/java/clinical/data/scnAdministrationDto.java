package clinical.data;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


@Data
@NoArgsConstructor
public class scnAdministrationDto {
	private String user_email;
	private String injection_yn;
	private Date injection_start_date;
	private LocalTime injection_start_time;
	private Date injection_end_date;
	private LocalTime injection_end_time;
	private Date injection_date;
	private String unit;
	private String injection_cnt;
	private String route;
	private String rate_change;
	private String rate_reason;
	private LocalDateTime regdate;


	@Builder
	public scnAdministrationDto(String user_email, String injection_yn, Date injection_start_date, LocalTime injection_start_time, Date injection_end_date, LocalTime injection_end_time, Date injection_date, String unit,
								String injection_cnt, String route, String rate_change, String rate_reason, LocalDateTime regdate) {
		this.user_email = user_email;
		this.injection_yn = injection_yn;
		this.injection_start_date = injection_start_date;
		this.injection_start_time = injection_start_time;
		this.injection_end_date = injection_end_date;
		this.injection_end_time = injection_end_time;
		this.injection_date = injection_date;
		this.unit = unit;
		this.injection_cnt = injection_cnt;
		this.route = route;
		this.rate_change = rate_change;
		this.rate_reason = rate_reason;
		this.regdate = regdate;

	}
	
	public scnAdministrationEntity toEntity() {
		scnAdministrationEntity administration = scnAdministrationEntity.builder()
				.user_email(user_email)
				.injection_yn(injection_yn)
				.injection_start_date(injection_start_date)
				.injection_start_time(injection_start_time)
				.injection_end_date(injection_end_date)
				.injection_end_time(injection_end_time)
				.injection_date(injection_date)
				.unit(unit)
				.injection_cnt(injection_cnt)
				.route(route)
				.rate_change(rate_change)
				.rate_reason(rate_reason)
				.regdate(regdate)
				.build();
		return administration;
	}

	//entity -> dto
	public scnAdministrationDto scnAdministrationToDto(scnAdministrationEntity administration) {
		return scnAdministrationDto.builder()
				.user_email(administration.getUser_email())
				.injection_yn(administration.getInjection_yn())
				.injection_start_date(administration.getInjection_start_date())
				.injection_start_time(administration.getInjection_start_time())
				.injection_end_date(administration.getInjection_end_date())
				.injection_end_time(administration.getInjection_end_time())
				.injection_date(administration.getInjection_date())
				.unit(administration.getUnit())
				.injection_cnt(administration.getInjection_cnt())
				.route(administration.getRoute())
				.rate_change(administration.getRate_change())
				.rate_reason(administration.getRate_reason())
				.regdate(administration.getRegdate())
				.build();
	}
}
