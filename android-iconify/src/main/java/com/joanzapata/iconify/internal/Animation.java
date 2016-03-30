package com.joanzapata.iconify.internal;

public enum Animation {
    SPIN("spin"), PULSE("pulse"), NONE(null);

    private final String token;

    Animation(String token) {
        this.token = token;
    }

    String getToken() {
        return token;
    }
}
