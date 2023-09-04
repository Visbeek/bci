package com.bci.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserResponseDTO {

    private String id;
    private String name;
    private String email;
    private String password;

    private List<PhoneDTO> phones;
    private Date created;
    private Date modified;

    @JsonProperty("last_login")
    private Date lastLogin;
    private String token;

    @JsonProperty("isactive")
    private Boolean isActive;
}
