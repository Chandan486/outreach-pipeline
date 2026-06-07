package com.vocallabs.outreach.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "prospeo",
        url = "${prospeo.base-url}"
)
public interface ProspeoClient {
}