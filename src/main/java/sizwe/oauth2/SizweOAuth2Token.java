/*
 * Stores the token and the expiry information
 */
package sizwe.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author andrewgraaff
 */
public class SizweOAuth2Token {

    private String token;           //The actual token
    private String tokenType;       //returned by server.  e.g. Bearer

    private URI tokenUri;        //The token URL
    private String audience;
    private String grantType;       //Right it is only client Credentials
    private String clientId;
    private String clientSecret;
    private String scope;           //Probably refactor as a list

    private SizweTokenRequest tokenRequest;

    private LocalDateTime expiresAt = LocalDateTime.MIN;

    private RestTemplate restTemplate;

    /**
     * 
     * @param uri
     * @param audience
     * @param grantType
     * @param clientId
     * @param clientSecret
     * @param scope 
     */
    public SizweOAuth2Token(URI uri, String audience, String grantType,
            String clientId, String clientSecret, String scope) {
        if (uri == null) {
            throw new RuntimeException("tokenURI can't be null.");
        }
        this.tokenUri = uri;

        if (audience == null) {
            throw new RuntimeException("audience can't be null. Use an empty string to ignore.");
        }
        this.audience = audience;

        if (grantType == null) {
            throw new RuntimeException("grantType Can't Be Null. Use an empty string to ignore");
        }
        this.grantType = grantType;

        if (clientId == null) {
            throw new RuntimeException("clientId can't be null. Use an empty string to ignore");
        }
        this.clientId = clientId;

        if (clientSecret == null) {
            throw new RuntimeException("clientSecret can't be null. Use an empty string to ignore");
        }
        this.clientSecret = clientSecret;

        if (scope == null) {
            throw new RuntimeException("scope can't be null. Use an empty string to ignore");
        }
        this.scope = scope;

        tokenRequest = new SizweTokenRequest();
        tokenRequest.setAudience(audience);
        tokenRequest.setClientId(clientId);
        tokenRequest.setClientSecret(clientSecret);
        tokenRequest.setGrantType(grantType);
        tokenRequest.setScope(scope);
    }


    /**
     * Will retrieve a new token if the token is expired or null
     *
     * @return
     */
    public String getTokenHeader() {
        if (token == null || LocalDateTime.now().isAfter(expiresAt)) {
            if (restTemplate == null) {
                restTemplate = new RestTemplate();
            }
            ResponseEntity<SizweTokenResponse> response
                    = restTemplate.postForEntity(tokenUri.toASCIIString(),
                            tokenRequest, SizweTokenResponse.class);
            if (response.getStatusCodeValue() != 200) {
                throw new RuntimeException("Could not retrieve token");
            }
            //Otherwise we should set the values
            token = response.getBody().getAccessToken();
            expiresAt = LocalDateTime.now().plusSeconds(response.getBody().getExpiresIn());
            tokenType = response.getBody().getTokenType();

        }
        return tokenType + " " + token;
    }

}
