package org.group.authentication.dto;

import lombok.*; 

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto{
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private boolean gender;

}
