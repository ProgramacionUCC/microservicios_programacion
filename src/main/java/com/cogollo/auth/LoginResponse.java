package com.cogollo.auth;

public record LoginResponse(String token, String tipo, long expiraEnMs) { }
