package clinical.data;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class pageSettingDto {
	private Integer seq;
	private String group_name;
	private List<pageSettingDetailEntity> PageSettingDetailEntity;
	
	@Builder
	public pageSettingDto(Integer seq, String group_name, List<pageSettingDetailEntity> PageSettingDetailEntity) {
		this.seq = seq;
		this.group_name = group_name;
		this.PageSettingDetailEntity = PageSettingDetailEntity;
	}

	//entity -> dto
	public pageSettingDto pageSettingToDto(pageSettingEntity page) {
		return pageSettingDto.builder()
                .seq(page.getSeq())
                .group_name(page.getGroup_name())
				.PageSettingDetailEntity(page.getPageSettingDetailEntity())
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
