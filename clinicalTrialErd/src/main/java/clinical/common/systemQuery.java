package clinical.common;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class systemQuery {

    //날짜비교
    public static boolean dateCheck(LocalDate visit, LocalDate birthday) {
        //주어진 날짜가, 파라미터로 전달받은 날짜보다 클 경우 true를 리턴
        return visit.isAfter(birthday);
    }

    public static boolean investigationCheck(String gender, String genderChk, String injectionYn,String injectionCk) {
        //주어진 날짜가, 파라미터로 전달받은 날짜보다 클 경우 true를 리턴
        if(genderChk.equals(gender)  && injectionCk.equals(injectionYn)){
            return true;
        }else{ return false; }
    }
}
