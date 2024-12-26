//package org.gateway.config;
//
//import org.springframework.cloud.gateway.config.GlobalCorsProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsConfigurationSource;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//
//@Configuration
//public class GatewayCorsConfiguration {
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // Frontend URL
//        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // HTTP methods
//        corsConfiguration.setAllowedHeaders(Arrays.asList("*")); // Allow all headers
//        corsConfiguration.setAllowCredentials(true); // Enable credentials (cookies, etc.)
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration); // Apply CORS to all routes
//        return source;
//    }
//
//    @Bean
//    public GlobalCorsProperties globalCorsProperties(CorsConfigurationSource corsConfigurationSource) {
//        GlobalCorsProperties globalCorsProperties = new GlobalCorsProperties();
//        globalCorsProperties.getCorsConfigurations().put("/**", corsConfigurationSource.getCorsConfiguration(null));
//        return globalCorsProperties;
//    }
//}
