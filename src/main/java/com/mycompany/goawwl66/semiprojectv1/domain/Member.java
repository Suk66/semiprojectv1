package com.mycompany.goawwl66.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Member {
    private int nmo;
    private String userid;
    private String password;
    private String repassword;
    private String name;
    private String email;
    private LocalDateTime regdate;

}
