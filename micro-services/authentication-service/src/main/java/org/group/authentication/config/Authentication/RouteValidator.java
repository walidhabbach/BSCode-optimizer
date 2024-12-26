package org.group.authentication.config.Authentication;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;

public class RouteValidator {

    private static final List<String> openApiEndpoints = List.of(
            "/api/auth/signin",
            "/api/auth/signup"
    );
    public static boolean isSecured (HttpServletRequest request){
        System.out.println ("request.getRequestURI ()"+request.getRequestURI ());

        return  openApiEndpoints
                .stream()
                .anyMatch (uri -> request.getRequestURI ().contains(uri));
    }


}