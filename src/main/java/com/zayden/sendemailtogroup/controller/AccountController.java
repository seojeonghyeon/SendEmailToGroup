package com.zayden.sendemailtogroup.controller;

import com.zayden.sendemailtogroup.dto.AccountDto;
import com.zayden.sendemailtogroup.service.AccountService;
import com.zayden.sendemailtogroup.validator.SignUpFormValidator;
import com.zayden.sendemailtogroup.vo.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final SignUpFormValidator signUpFormValidator;
    private final AccountService accountService;

    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(signUpFormValidator);
    }


    @GetMapping("/sign-up")
    public String signUpForm(Model model){
        model.addAttribute(new SignUpForm());
        return "account/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors){
        if(errors.hasErrors()){
            return "account/sign-up";
        }
        accountService.processNewAccount(signUpForm);
        return "redirect:/";
    }

    @GetMapping("/check-email-token")
    public String checkEmailToken(String token, String email, Model model){
        AccountDto accountDto = accountService.findByEmail(email);
        String view = "account/checked-email";
        if(accountDto == null){
            model.addAttribute("error","wrong.email");
            return view;
        }
        if(!accountDto.getEmailCheckToken().equals(token)){
            model.addAttribute("error","worng.token");
            return view;
        }

        accountDto.completeSignUp();
        model.addAttribute("nickname", accountDto.getNickname());
        return view;
    }

}
