package com.zayden.sendemailtogroup.dao;

import com.zayden.sendemailtogroup.domain.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
