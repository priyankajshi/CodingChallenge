package com.autotests.api.ws;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowerResumptionInfoWs {
    private String firstName;
    private String maskedEmail;
    private Boolean ssnRequired;
    private String state;
    private String email;
}
