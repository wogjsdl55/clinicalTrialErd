package clinical.data;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class pageSettingDto {
	private String seq;
	private String group_name;
	private List<pageSettingDetailEntity> PageSettingDetailEntity;
	
	@Builder
	public pageSettingDto(String seq, String group_name, List<pageSettingDetailEntity> PageSettingDetailEntity) {
		this.seq = seq;
		this.group_name = group_name;
		this.PageSettingDetailEntity = PageSettingDetailEntity;
	}

	//entity -> dto
	public pageSettingDto pageSettingToDto(pageSettingEntity page) {
		return pageSettingDto.builder()
                .seq(page.getSeq())
                .group_name(page.getGroup_name())
				.PageSettingDetailEntity(page.getPageSettingDetailEntity().stream().toList())
                .build();
	}
	

}
