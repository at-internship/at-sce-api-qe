package com.at.globalclasses.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {

	private String  type;
    private String  firstName;
    private String  lastName;
    private String  email;
    private String  password;
    private String  status;
	
}
