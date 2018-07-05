/*
 * 
 */
package sizwe.oauth2;

import org.springframework.web.client.RestTemplate;

/**
 *
 * @author andrewgraaff
 */
public class SizweOauth2RestTemplate extends RestTemplate {
    
    public SizweOauth2RestTemplate(SizweOAuth2Interceptor interceptor){
        this.getInterceptors().add(interceptor);
    }
    
}
