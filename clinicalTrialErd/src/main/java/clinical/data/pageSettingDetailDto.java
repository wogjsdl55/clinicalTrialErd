package clinical.data;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class pageSettingDetailDto {
	private Integer seq;
	private String group_type;
	private List<pageSettingValueEntity> PageSettingValueEntity;

	@Builder
	public pageSettingDetailDto(Integer seq, String group_type, List<pageSettingValueEntity> PageSettingValueEntity) {
		this.seq = seq;
		this.group_type = group_type;
		this.PageSettingValueEntity = PageSettingValueEntity;
	}

	//entity -> dto
	public pageSettingDetailDto pageSettingToDto(pageSettingDetailDto page) {
		return pageSettingDetailDto.builder()
                .seq(page.getSeq())
                .group_type(page.getGroup_type())
				.PageSettingValueEntity(page.getPageSettingValueEntity())
                .build();
	}

}
