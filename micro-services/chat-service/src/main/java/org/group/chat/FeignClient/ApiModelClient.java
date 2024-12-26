package org.group.chat.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "api-model-service",
        url = "${application.config.api-model-url}"
)
public interface ApiModelClient {
    @PostMapping("/predict")
    String optimizeCode(String code);
}