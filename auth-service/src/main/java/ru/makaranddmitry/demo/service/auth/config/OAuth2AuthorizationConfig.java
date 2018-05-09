package ru.makaranddmitry.demo.service.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private TokenStore tokenStore = new InMemoryTokenStore();

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter:off
        //$2a$10$WKM0V1kIkNdrGsRasRvYHOLKVdrgoMmY7EGL4VRvimQMgaGsEe7zy
			clients.inMemory()
					.withClient("test-service-1")
					.secret("$2a$10$WKM0V1kIkNdrGsRasRvYHOLKVdrgoMmY7EGL4VRvimQMgaGsEe7zy")
					.authorizedGrantTypes("client_credentials", "refresh_token")
					.scopes("all")
                .and()
                    .withClient("test-service-2")
					.secret("$2a$10$WKM0V1kIkNdrGsRasRvYHOLKVdrgoMmY7EGL4VRvimQMgaGsEe7zy")
					.authorizedGrantTypes("client_credentials", "refresh_token")
					.scopes("all")
                    .and()
                    .withClient("film-service")
                    .secret("$2a$10$WKM0V1kIkNdrGsRasRvYHOLKVdrgoMmY7EGL4VRvimQMgaGsEe7zy")
                    .authorizedGrantTypes("client_credentials", "refresh_token")
                    .scopes("all")
                .and()
                    .withClient("gateway-service")
					.secret("$2a$10$WKM0V1kIkNdrGsRasRvYHOLKVdrgoMmY7EGL4VRvimQMgaGsEe7zy")
					.authorizedGrantTypes("client_credentials", "refresh_token")
					.scopes("all")
                .and()
                    .withClient("client")
					.authorizedGrantTypes("refresh_token", "password")
					.scopes("ui");
			// @formatter:on
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }
}
