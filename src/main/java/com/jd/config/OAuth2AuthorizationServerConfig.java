package com.jd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import com.jd.service.impl.UserDetailsServiceImpl;



/*
[/oauth/authorize]   获得授权码  

[/oauth/token]  获取access_token     参数 grant_type、username、password、  client_id、client_secret 

                                 刷新token请求                参数 grant_type、refresh_token、client_id、client_secret 
                                 
                                 
[/oauth/check_token]   检查头肯是否有效请求    参数  access_token

[/oauth/confirm_access]
[/oauth/token_key]
[/oauth/error]
*/

/**
 * 
 * 授权服务器
 * @author hanzhifeng

 * @creatDate 2019年3月27日
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig   extends AuthorizationServerConfigurerAdapter{
	
    private static final String SECRETPREFIX = "{noop}"; //spring security5 之后需要
	
    @Autowired
    private AuthenticationManager authenticationManager; //认证管理者
    
    @Autowired
    private UserDetailsServiceImpl  userDetailsService; // 用户信息服务
    
    @Autowired
    private TokenStore tokenStore; 
    
    @Autowired
    private AuthorizationParam authorizationParam;
    
    
  //保存令牌数据栈
    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory){
         return new RedisTokenStore(redisConnectionFactory); //使用redis存储令牌
    }
	

	//用来配置令牌端点的安全约束。
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		
		 oauthServer.tokenKeyAccess("permitAll()")
		 
					.checkTokenAccess("permitAll()")
					 
					.allowFormAuthenticationForClients();//--把allowFormAuthenticationForClients设置为true
		 
		//这个如果配置支持allowFormAuthenticationForClients的，
		 
		// 且url中有client_id和client_secret的会走ClientCredentialsTokenEndpointFilter来保护
		 
		// 如果没有支持allowFormAuthenticationForClients或者有支持但是url中没有client_id和client_secret的，
		 
		 //走basic认证保护
	}

	//定义客户详细信息服务的配置器。客户端详细信息可以被初始化，或者您可以直接引用一个现有的存储
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients.inMemory()
         .withClient(authorizationParam.getClientId()) //客户端ID
         .authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code")//设置验证方式
         .scopes(authorizationParam.getScopes())
         .secret(SECRETPREFIX+authorizationParam.getSecret())
         .accessTokenValiditySeconds(authorizationParam.getTokenExpire()) //token过期时间
         .refreshTokenValiditySeconds(authorizationParam.getTokenRefresh());//refresh过期时间
	}
	
	
    //用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
	@Override 
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		 endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager).userDetailsService(userDetailsService);
         
	}

}
