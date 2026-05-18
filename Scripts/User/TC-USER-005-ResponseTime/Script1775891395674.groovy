import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import common.ApiHelper

def token = ApiHelper.getToken()

RequestObject userReq = new RestRequestObjectBuilder()
    .withRestUrl('https://dummyjson.com/auth/me')
    .withRestRequestMethod('GET')
    .withHttpHeaders(ApiHelper.getAuthHeaders(token))
    .build()

def response = WS.sendRequest(userReq)

WS.verifyResponseStatusCode(response, 200)

def elapsedTime = response.getElapsedTime()
assert elapsedTime < 1000 : "Response too slow! Actual: ${elapsedTime}ms, Threshold: 1000ms"

println "TC-USER-005 PASSED — Response time: ${elapsedTime}ms (threshold: 1000ms)"