package clinical.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "group_value")
@EntityListeners(AuditingEntityListener.class)
public class pageSettingValueEntity {

    //Entity 클래스는 실제 DB 테이블과 매핑되는 핵심 클래스로, 데이터베이스의 테이블에 존재하는 컬럼들을 필드로 가지는 객체입니다.

    @Id
    private String etc1;
    private String etc2;
    private String etc3;
    private String etc4;
    private String etc5;
    private String etc6;
    private String etc7;
    private String etc8;
    private String etc9;

    @OneToOne(mappedBy = "PageSettingValueEntity", fetch = FetchType.EAGER)
    @JoinColumn(name = "etc1")
    private pageSettingDetailEntity PageSettingDetailEntity;

}
