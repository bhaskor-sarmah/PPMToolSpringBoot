package io.bhaskor.ppmtool.exceptions;

/**
 * UsernameAlreadyExistResponse
 */
public class UsernameAlreadyExistResponse {

    private String username;

    public UsernameAlreadyExistResponse() {
    }

    public UsernameAlreadyExistResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UsernameAlreadyExistResponse username(String username) {
        this.username = username;
        return this;
    }

}