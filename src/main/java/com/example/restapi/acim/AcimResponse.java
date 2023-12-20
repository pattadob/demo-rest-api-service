package com.example.restapi.acim;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

record AcimResponse(
        String code,
        String desc,
        @JsonInclude(JsonInclude.Include.NON_NULL) List<AcimResponseDetail> processFail) {
}
