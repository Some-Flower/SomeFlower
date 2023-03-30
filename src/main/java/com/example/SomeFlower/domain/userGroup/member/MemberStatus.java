package com.example.SomeFlower.domain.userGroup.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberStatus {
    ACTIVE("Active",0),
    DELETED("Deleted",1);


    private final String statusKey;
    private final int statusNum;
}
