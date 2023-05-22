package clinical.data;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


@Data
@NoArgsConstructor
public class scnInvestigationDto {
	private String user_email;
	private String age;
	private Date birthday;
	private String ethnic;
	private String gender;
	private String races;
	private LocalTime time_birth;
	private LocalDateTime regdate;


	@Builder
	public scnInvestigationDto(String user_email, String age, Date birthday, String ethnic, String gender, String races, LocalTime time_birth, LocalDateTime regdate) {
		this.user_email = user_email;
		this.age = age;
		this.birthday = birthday;
		this.ethnic = ethnic;
		this.gender = gender;
		this.races = races;
		this.time_birth = time_birth;
		this.regdate = regdate;

	}
	
	public scnInvestigationEntity toEntity() {
		scnInvestigationEntity investigation = scnInvestigationEntity.builder()
				.user_email(user_email)
				.age(age)
				.birthday(birthday)
				.ethnic(ethnic)
				.gender(gender)
				.races(races)
				.time_birth(time_birth)
				.regdate(regdate)
				.build();
		return investigation;
	}

	//entity -> dto
	public scnInvestigationDto scnInvestigationToDto(scnInvestigationEntity investigationEntity) {
		return scnInvestigationDto.builder()
				.user_email(investigationEntity.getUser_email())
				.age(investigationEntity.getAge())
				.birthday(investigationEntity.getBirthday())
				.ethnic(investigationEntity.getEthnic())
				.gender(investigationEntity.getGender())
				.races(investigationEntity.getRaces())
				.time_birth(investigationEntity.getTime_birth())
				.regdate(investigationEntity.getRegdate())
				.build();
	}
}
