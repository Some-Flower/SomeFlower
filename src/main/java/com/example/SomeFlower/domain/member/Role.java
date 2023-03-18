package com.example.SomeFlower.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST","손님"),
    USER("ROLE_USER","사용자"),
    FLORIST("ROLE_FLORIST","플로리스트"),
    ADMIN("ROLE_ADMIN","관리자");

    private final String roleKey;
    private final String roleValue;
}
