package com.yuk.cspserver.authentication;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Profile("default")
public class DiscardAuthentication implements Authentication{
    @Override
    public void check(@NotNull Map<String, ? extends List<String>> headers) throws Exception {

    }

    @NotNull
    @Override
    public String login(@NotNull Map<String, ? extends List<String>> headers, @Nullable String body) throws Exception {
        return "";
    }
}
