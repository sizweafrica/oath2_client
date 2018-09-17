/*
 * 
 */
package sizwe.oauth2;

import java.net.URI;
import java.net.URISyntaxException;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sizwe.oauth2.SizweOAuth2Token;
import sizwe.oauth2.SizweOauth2GrantTypes;

/**
 *
 * @author andrewgraaff
 */

public class TokenIntegrationTest {
    @Test
    public void getToken() throws URISyntaxException{
        SizweOAuth2Token token = new SizweOAuth2Token(
                new URI("https://sizwe.eu.auth0.com/oauth/token"),
                "https://api.sizweafrica.co.za",
                SizweOauth2GrantTypes.CLIENT_CREDENTIALS,
                "Y3DOXGuBMifw9xL4n2VL0lu6CcFGOPw2",
                "S8mozox7969iHBCIhwbBufzqDE4yGhgbZl1uEeOI0qSHkeP_qvPEFWnlBZxHeGVp", "write:address");
        System.out.println(token.getTokenHeader());
        assertNotNull(token.getTokenHeader());
        token.getTokenHeader();
        token.getTokenHeader();
        token.getTokenHeader();
        token.getTokenHeader();
        token.getTokenHeader();
        token.getTokenHeader();
        token.getTokenHeader();
    }
    
    /**
     * 
     */
    //@Test
    public void doRequest() throws URISyntaxException{
        SizweOAuth2Token token = new SizweOAuth2Token(
                new URI("https://ffaargsizwe.eu.auth0.com/oauth/token"),
                "https://api.sizweafrica.co.za",
                SizweOauth2GrantTypes.CLIENT_CREDENTIALS,
                "Y3DOXGuBMifw9xL4n2VL0lu6CcFGOPw2",
                "S8mozox7969iHBCIhwbBufzqDE4yGhgbZl1uEeOI0qSHkeP_qvPEFWnlBZxHeGVp", "write:address");
        
        SizweOAuth2Interceptor interceptor = new SizweOAuth2Interceptor("Authorization", token);
        SizweOauth2RestTemplate template = new SizweOauth2RestTemplate(interceptor);
        template.postForEntity("http://localhost:8080/api/private/staging", "sdsddfsg", String.class);
    }
}
