package com.example.backend.DTO;

public class UserVerifyO {
    Integer ret_code;
    Long user_id;
    String description;

    public UserVerifyO() {
    }

    public UserVerifyO(Integer ret_code, Long user_id, String description) {
        this.ret_code = ret_code;
        this.user_id = user_id;
        this.description = description;
    }

    public Integer getRet_code() {
        return ret_code;
    }

    public void setRet_code(Integer ret_code) {
        this.ret_code = ret_code;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
