package com.zayden.sendemailtogroup.service;

import com.zayden.sendemailtogroup.dao.AccountRepository;
import com.zayden.sendemailtogroup.domain.AccountEntity;
import com.zayden.sendemailtogroup.dto.AccountDto;
import com.zayden.sendemailtogroup.vo.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void processNewAccount(SignUpForm signUpForm) {
        AccountEntity accountEntity = AccountEntity.builder()
                .email(signUpForm.getEmail())
                .nickname(signUpForm.getNickname())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .build();
        accountRepository.save(accountEntity);
    }

    @Override
    public AccountDto findByEmail(String email) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(accountRepository.findByEmail(email), AccountDto.class);
    }
}
