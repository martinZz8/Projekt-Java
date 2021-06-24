package com.example.backend.DTO;

public class AdministratorVerifyO {
    Integer ret_code;
    Long administrator_id;
    String description;

    public AdministratorVerifyO() {
    }

    public AdministratorVerifyO(Integer ret_code, Long administrator_id, String description) {
        this.ret_code = ret_code;
        this.administrator_id = administrator_id;
        this.description = description;
    }

    public Integer getRet_code() {
        return ret_code;
    }

    public void setRet_code(Integer ret_code) {
        this.ret_code = ret_code;
    }

    public Long getAdministrator_id() {
        return administrator_id;
    }

    public void setAdministrator_id(Long administrator_id) {
        this.administrator_id = administrator_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
