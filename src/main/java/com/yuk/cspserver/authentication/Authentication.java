package com.yuk.cspserver.authentication;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public interface Authentication {
    void check(@NotNull Map<String, ? extends List<String>> headers) throws Exception;

    @NotNull
    String login(@NotNull Map<String, ? extends List<String>> headers, @Nullable String body) throws Exception;
}
