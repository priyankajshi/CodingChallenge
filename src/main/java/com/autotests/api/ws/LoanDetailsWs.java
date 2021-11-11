package com.autotests.api.ws;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class LoanDetailsWs {
    private LoanAppResumptionInfoWs loanAppResumptionInfo;
    private Set offers;
    private String selectedOffer;
    private Set requiredAgreements;
    private Set resetOptions;
    private String originalLoanApp;
}
