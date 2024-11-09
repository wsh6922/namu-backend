package com.namu.thenamu.domain.user;

public enum Role {

    USER("일반 회원"),
    ADMIN("관리자");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
