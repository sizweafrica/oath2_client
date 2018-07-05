/*
 * 
 */
package sizwe.oauth2;

import java.io.IOException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 *
 * @author andrewgraaff
 */
public class SizweOAuth2Interceptor implements ClientHttpRequestInterceptor{
    
    private SizweOAuth2Token token;
    private String header;
    
    public SizweOAuth2Interceptor(String header, SizweOAuth2Token token){
        this.token = token;
        this.header = header;
    }
    
    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        request.getHeaders().add(header, token.getTokenHeader());
        return execution.execute(request, body);
    }
    
}
