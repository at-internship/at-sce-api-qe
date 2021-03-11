package com.at.globalclasses.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {

	private String email;
	private String password;
		
}
