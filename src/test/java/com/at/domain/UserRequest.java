package com.at.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
public class UserRequest {
    private String  type;
    private String  firstName;
    private String  lastName;
    private String  email;
    private String  password;
    private String  status;
}
