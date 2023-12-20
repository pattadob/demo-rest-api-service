package com.example.restapi.acim;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AcimResponseDetail(
        @JsonProperty("username") String name) {
}
