package com.zayden.sendemailtogroup.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountDto {

    private String email;

    private String nickname;

    private String password;

    private boolean emailVerified;

    private String emailCheckToken;

    private LocalDateTime joinedAt;

    public void completeSignUp() {
        this.emailVerified = true;
        this.joinedAt = LocalDateTime.now();
    }
}
