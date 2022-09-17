package com.zayden.sendemailtogroup.vo;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class SignUpForm {
    @NotNull
    @Size(min=3, max=20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    private String nickname;

    @Email
    @NotNull
    private String email;

    @NotBlank
    @Size
    private String password;
}
