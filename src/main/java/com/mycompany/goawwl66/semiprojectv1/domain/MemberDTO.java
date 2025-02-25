package com.mycompany.goawwl66.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

@Data   // setter, getter, toString
@Builder    //
public class MemberDTO {

    private String userid;
    private String password;
    private String name;
    private String email;
}
