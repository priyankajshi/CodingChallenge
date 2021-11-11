package com.autotests.api;

import com.autotests.api.ws.LoanDetailsWs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.HashMap;
import java.util.Map;

class LoanDetailsTest {

    String baseUri;
    Map<String, String> headers;
    String apiPath;

    @BeforeTest
    public void setUp() {
       baseUri = "https://credapi.credify.tech";
       apiPath = "/api/brfunnelorch/v2/resume/byLeadSecret";
       headers = new HashMap<>();
       headers.put("x-cf-source-id", "coding-challenge");
       headers.put("x-cf-corr-id","19bb9b74-410a-11ec-973a-0242ac130003");
       headers.put("Content-Type","application/json");
    }

    @DataProvider(name = "loanPayloadDP")
    public Object[][] loanPayloadDP(){
        return new Object[][] {
                {"b8096ec7-2150-405f-84f5-ae99864b3e96", true},
                {"b8096ec7-2150-405f-84f5-ae99864b3e96", false},
        };
    }

    @Test(dataProvider = "loanPayloadDP")
    public void testPostLoanDetails(String loanAppUuid, boolean skipSideEffects) throws JsonProcessingException {
        String payload = preparePayload(loanAppUuid, skipSideEffects);
        Response response = RestAssured
                .given()
                .headers(headers)
                .body(payload)
                .post(baseUri + apiPath);
        Assert.assertNotNull(response.getBody(), "Response  body is null");
        Assert.assertEquals(response.getStatusCode(), 200, "Response code is not 200");
        if(response.getStatusCode() == 200) {
            Logger.getLogger(LoanDetailsTest.class).info(response.getBody().asPrettyString());
            ObjectMapper mapper = new ObjectMapper();
            LoanDetailsWs ws = mapper.readValue(response.getBody().asPrettyString(), LoanDetailsWs.class);
            Assert.assertEquals(ws.getLoanAppResumptionInfo().getProductType(), "PERSONAL_LOAN");
        }
    }

    @Test
    @Parameters({"loanAppUuid", "skipSideEffects"})
    public void testPostLoanDetailsInvalid(String loanAppUuid, boolean skipSideEffects) throws JsonProcessingException {
        String payload = preparePayload(loanAppUuid, skipSideEffects);
        Response response = RestAssured
                .given()
                .headers(headers)
                .body(payload)
                .post(baseUri + apiPath);
        Assert.assertNotNull(response.getBody(), "Response  body is null");
        Assert.assertEquals(response.getStatusCode(), 404, "Response code is not 404");
        Assert.assertTrue(response.getBody().asString().contains("MISSING_LOAN_APPLICATION"));
    }

    private String preparePayload(String loanAppUuid, boolean skipSideEffects){
        return "{\"loanAppUuid\":\"" + loanAppUuid + "\", \"skipSideEffects\":" + skipSideEffects + "}";
    }
}