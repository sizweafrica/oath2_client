/*
 * 
 */
package sizwe.oauth2;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author andrewgraaff
 */
public class SizweTokenRequest {
    
    @JsonProperty("client_id")
    private String clientId;
    
    @JsonProperty("client_secret")
    private String clientSecret;
    
    @JsonProperty("grant_type")
    private String grantType;
    
    @JsonProperty("scope")
    private String scope;
    
    @JsonProperty("audience")
    private String audience;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }
    
    
    
}
