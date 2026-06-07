package com.vocallabs.outreach.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "brevo",
        url = "${brevo.base-url}"
)
public interface BrevoClient {
}