package com.vocallabs.outreach.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "ocean",
        url = "${ocean.base-url}"
)
public interface OceanClient {
}