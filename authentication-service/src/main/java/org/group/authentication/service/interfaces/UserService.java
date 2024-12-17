package org.group.authentication.service.interfaces;

import org.group.authentication.dto.AuthenticationResponse;
import org.group.authentication.dto.UserDto;

import java.util.List;

public interface UserService {
	List<UserDto> findAll();
	UserDto findById(final Long id);
 	AuthenticationResponse login( final UserDto userDto);
	AuthenticationResponse register(final UserDto clientDto);

	UserDto update(final UserDto CustomerDto);
 	void deleteById(final Long id);
	boolean existsById(final Long id);
	 AuthenticationResponse refreshToken( String refreshToken);
}

