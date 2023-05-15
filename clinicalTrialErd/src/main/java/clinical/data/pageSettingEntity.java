package clinical.data;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "group_main")
@EntityListeners(AuditingEntityListener.class)
public class pageSettingEntity {
	
	//Entity 클래스는 실제 DB 테이블과 매핑되는 핵심 클래스로, 데이터베이스의 테이블에 존재하는 컬럼들을 필드로 가지는 객체입니다.
	@Id
	@Column(name ="group_name")
	private String group_name;

	private String seq;

	@OneToMany(mappedBy = "PageSettingEntity", fetch = FetchType.EAGER)
	private List<pageSettingDetailEntity> PageSettingDetailEntity = new ArrayList<>();


}

