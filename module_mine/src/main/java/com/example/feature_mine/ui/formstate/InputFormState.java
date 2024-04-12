package com.example.feature_mine.ui.formstate;

/**
 * @Author winiymissl
 * @Date 2024-04-12 13:05
 * @Version 1.0
 */
public class InputFormState {
    private String nickNameError;
    private boolean dataValid;

    public InputFormState(String nickNameError, boolean dataValid) {
        this.nickNameError = nickNameError;
        this.dataValid = dataValid;
    }

    public InputFormState(boolean dataValid) {
        this.dataValid = dataValid;
    }

    public String getNickNameError() {
        return nickNameError;
    }

    public void setNickNameError(String nickNameError) {
        this.nickNameError = nickNameError;
    }

    public boolean isDataValid() {
        return dataValid;
    }
}
