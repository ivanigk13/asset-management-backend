package com.lawencon.asset.security;

import org.springframework.security.core.Authentication;

public interface AuthPrincipal {
	Authentication getAuthentication();
}
