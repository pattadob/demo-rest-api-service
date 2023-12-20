package com.example.restapi.acim;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
record AcimUser(
        String username,
        String firstName,
        String lastName,
        String email,
        String role,
        String rolePass,
        String period,
        String startDate,
        String endDate,
        String locationCode,
        String locationName
) {

}
