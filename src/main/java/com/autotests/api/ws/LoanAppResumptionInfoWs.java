package com.autotests.api.ws;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class LoanAppResumptionInfoWs {
    private long loanAppId;
    private String loanAppUuid;
    private String referrer;
    private String status;
    private String productType;
    private String sourceSystem;
    private String desiredAmount;
    private BorrowerResumptionInfoWs borrowerResumptionInfo;
    private String coBorrowerResumptionInfo;
    private Boolean turnDown;
    private Boolean hasLogin;
    private Set availableAppImprovements;
    private String cashOutAmount;
    private String canAddCollateral;
    private String rewardProgramId;
    private String rewardProgramCode;
    private String addon;
    private String isMobileDiscountApplied;
    private String checkingDiscountAvailable;
}
