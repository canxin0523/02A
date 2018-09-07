package com.pinyougo.pojo;

import java.io.Serializable;

/**
 * Created by 0523 on 2018/9/5.
 */

    public class Result implements Serializable {
        private boolean success;
        private String message;
        public Result(boolean success, String message) {
            super();
            this.success = success;
            this.message = message;
        }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


