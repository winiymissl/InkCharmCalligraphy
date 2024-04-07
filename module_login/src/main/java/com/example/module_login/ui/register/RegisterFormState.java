package com.example.module_login.ui.register;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
public class RegisterFormState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;

    @Nullable
    private Integer codeError;

    private boolean isDataValid;

    public RegisterFormState(@Nullable Integer usernameError, @Nullable Integer passwordError, @Nullable Integer codeError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.codeError = codeError;
        this.isDataValid = false;
    }

    public RegisterFormState(boolean isDataValid) {
        this.usernameError = null;
        this.passwordError = null;
        this.codeError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }


    @Nullable
    public Integer getCodeError() {
        return codeError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}