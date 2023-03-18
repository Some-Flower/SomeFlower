package com.example.SomeFlower.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberStatus {
    DELETED("Deleted",0),
    ACTIVE("Active",1);

    private final String statusKey;
    private final int statusNum;
}
