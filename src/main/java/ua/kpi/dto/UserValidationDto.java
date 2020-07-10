package ua.kpi.dto;

public class UserValidationDto {
    private boolean usernameValid;
    private boolean passwordValid;
    private boolean passwordsMatch;
    private boolean userExistsWithUsername;

    private boolean isUserValid;

    public boolean isUsernameValid() {
        return usernameValid;
    }

    public void setUsernameValid(boolean usernameValid) {
        this.usernameValid = usernameValid;
    }

    public boolean isPasswordValid() {
        return passwordValid;
    }

    public void setPasswordValid(boolean passwordValid) {
        this.passwordValid = passwordValid;
    }

    public boolean arePasswordsMatch() {
        return passwordsMatch;
    }

    public void setPasswordsMatch(boolean passwordsMatch) {
        this.passwordsMatch = passwordsMatch;
    }

    public boolean isUserExistsWithUsername() {
        return userExistsWithUsername;
    }

    public void setUserExistsWithUsername(boolean userExistsWithUsername) {
        this.userExistsWithUsername = userExistsWithUsername;
    }

    public boolean isUserValid() {
        return isUserValid;
    }

    public void setUserValid(boolean userValid) {
        isUserValid = userValid;
    }
}
