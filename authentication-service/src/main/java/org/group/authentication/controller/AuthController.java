package org.group.authentication.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.group.authentication.dto.AuthenticationResponse;
import org.group.authentication.dto.UserDto;
import org.group.authentication.helper.util.JwtUtil;
import org.group.authentication.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> login( @RequestBody UserDto userDto) {
        System.out.println("signin : "+userDto.getPassword()+userDto.getEmail());
        return ResponseEntity.ok().body(this.userService.login(userDto));
    }
    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody UserDto userDto){
        AuthenticationResponse response = this.userService.register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        System.out.println ("refresh token");
        String token = authorizationHeader.substring(7);
        return ResponseEntity.ok(userService.refreshToken(token));
    }
    @PutMapping
    public ResponseEntity<UserDto> updateUser(
            @RequestBody  UserDto UserDto
    ) {
        return ResponseEntity.ok(this.userService.update(UserDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("id") Long UserId) {
        return ResponseEntity.ok(this.userService.existsById(UserId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(
            @PathVariable("id") Long userId
    ) {
        return ResponseEntity.ok(this.userService.findById(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long userId
    ) {
        this.userService.deleteById(userId);
        return ResponseEntity.accepted().build();
    }

}
