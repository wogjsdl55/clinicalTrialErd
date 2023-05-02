package clinical.data;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class pageSettingDto {
	private String page_name;
	private String page_type_01;
	private String page_type_02;
	private String page_type_03;
	private String page_type_04;
	private String page_type_05;
	
	@Builder
	public pageSettingDto(String page_name, String page_type_01, String page_type_02, String page_type_03, String page_type_04, String page_type_05) {
		this.page_name = page_name;
		this.page_type_01 = page_type_01;
		this.page_type_02 = page_type_02;
		this.page_type_03 = page_type_03;
		this.page_type_04 = page_type_04;
		this.page_type_05 = page_type_05;
	}
	
	//entity -> dto
	public pageSettingDto pageSettingToDto(pageSettingEntity page) {
		return pageSettingDto.builder()
                .page_name(page.getPage_name())
                .page_type_01(page.getPage_type_01())
                .page_type_02(page.getPage_type_02())
                .page_type_03(page.getPage_type_03())
                .page_type_04(page.getPage_type_04())
                .page_type_05(page.getPage_type_05())
                .build();
	}
	
//	public pageSettingEntity toEntity() {
//		pageSettingEntity pageSetting = pageSettingEntity.builder()
//				.page_name(page_name)
//				.page_type_01(page_type_01)
//				.page_type_02(page_type_02)
//				.page_type_03(page_type_03)
//				.page_type_04(page_type_04)
//				.page_type_05(page_type_05)
//				.build();
//		return pageSetting;
//	}
}
