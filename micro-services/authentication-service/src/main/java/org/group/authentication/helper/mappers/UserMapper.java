package org.group.authentication.helper.mappers;


import org.group.authentication.config.Authentication.CustomUserDetails;
import org.group.authentication.dto.UserDto;
import org.group.authentication.model.Users;

public class UserMapper{
	public static UserDto map(final Users user) {
		return UserDto.builder()
				.id(user.getId())
				.email(user.getEmail())
				.firstname(user.getFirstname())
				.lastname(user.getLastname())
				.gender(user.isGender())
				.build();
	}

	public static Users map(final UserDto userDto) {
		return Users.builder()
				.id(userDto.getId())
				.email(userDto.getEmail())
				.firstname(userDto.getFirstname())
				.lastname(userDto.getLastname())
				.password(userDto.getPassword())
				.gender(userDto.isGender())

				.build();
	}
	public static CustomUserDetails mapToCustomUserDetails( final Users user) {
		return CustomUserDetails.builder()
				.id(user.getId())
				.email(user.getEmail())
				.password(user.getPassword())
				.build();
	}
}








