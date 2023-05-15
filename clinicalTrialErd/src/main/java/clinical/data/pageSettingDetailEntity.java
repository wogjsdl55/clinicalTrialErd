package clinical.data;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "pageSettingEntity")
@Table(name = "group_detail")
@EntityListeners(AuditingEntityListener.class)
public class pageSettingDetailEntity {

    //Entity 클래스는 실제 DB 테이블과 매핑되는 핵심 클래스로, 데이터베이스의 테이블에 존재하는 컬럼들을 필드로 가지는 객체입니다.

    private int seq;
    private String group_type;

    @Id
    @ManyToOne
    @JoinColumn(name = "group_name")
    private pageSettingEntity PageSettingEntity;

    @OneToOne
    @JoinColumn(name = "etc1")
    private pageSettingValueEntity PageSettingValueEntity;

}
