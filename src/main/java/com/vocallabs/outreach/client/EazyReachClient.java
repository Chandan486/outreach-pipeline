package com.vocallabs.outreach.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "eazyreach",
        url = "${eazyreach.base-url}"
)
public interface EazyReachClient {
}