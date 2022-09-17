package com.zayden.sendemailtogroup.service;

import com.zayden.sendemailtogroup.vo.SignUpForm;

public interface AccountService {
    void processNewAccount(SignUpForm signUpForm);
}
