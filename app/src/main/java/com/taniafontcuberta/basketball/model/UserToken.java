package com.taniafontcuberta.basketball.model;

import com.google.gson.annotations.SerializedName;

public class UserToken {
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("grant_type")
    private String grantType;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("expires_in")
    private long expiresIn;

    @SerializedName("scope")
    private String scope;

    public UserToken() {
    }

    public UserToken(String accessToken, String tokenType, String grantType, String refreshToken, long expiresIn, String scope) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.grantType = grantType;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.scope = scope;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserToken userToken = (UserToken) o;

        if (expiresIn != userToken.expiresIn) return false;
        if (!accessToken.equals(userToken.accessToken)) return false;
        if (tokenType != null ? !tokenType.equals(userToken.tokenType) : userToken.tokenType != null)
            return false;
        if (grantType != null ? !grantType.equals(userToken.grantType) : userToken.grantType != null)
            return false;
        if (refreshToken != null ? !refreshToken.equals(userToken.refreshToken) : userToken.refreshToken != null)
            return false;
        return !(scope != null ? !scope.equals(userToken.scope) : userToken.scope != null);

    }

    @Override
    public int hashCode() {
        int result = accessToken.hashCode();
        result = 31 * result + (tokenType != null ? tokenType.hashCode() : 0);
        result = 31 * result + (grantType != null ? grantType.hashCode() : 0);
        result = 31 * result + (refreshToken != null ? refreshToken.hashCode() : 0);
        result = 31 * result + (int) (expiresIn ^ (expiresIn >>> 32));
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        grantType = (grantType == null) ? "null" : grantType;

        return "UserToken{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", grantType='" + grantType + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", scope='" + scope + '\'' +
                '}';
    }
}