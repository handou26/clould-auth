package com.jd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "authorizationparam")
public class AuthorizationParam {
	
	private String clientId; // 客户端id
	
	private String secret; // (可信客户端需要)客户端密钥
	
	private String[] scopes; // 客户受限范围
	
	private String authorizedGrantTypes; // 授权客户端使用的授权类型
	
	// private String authorities;//授予客户端的权限
	
	private int tokenExpire;// token过期时间
	
	private int tokenRefresh;// token 刷新时间

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String[] getScopes() {
		return scopes;
	}

	public void setScopes(String[] scopes) {
		this.scopes = scopes;
	}

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public int getTokenExpire() {
		return tokenExpire;
	}

	public void setTokenExpire(int tokenExpire) {
		this.tokenExpire = tokenExpire;
	}

	public int getTokenRefresh() {
		return tokenRefresh;
	}

	public void setTokenRefresh(int tokenRefresh) {
		this.tokenRefresh = tokenRefresh;
	}

}
