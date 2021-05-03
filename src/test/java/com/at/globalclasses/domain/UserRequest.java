package com.at.globalclasses.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {

	private Integer  type;
    private String  firstName;
    private String  lastName;
    private String  email;
    private String  password;
    private Integer  status;
	
}
