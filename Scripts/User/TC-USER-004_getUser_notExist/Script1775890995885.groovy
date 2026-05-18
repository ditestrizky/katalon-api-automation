import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import common.ApiHelper

def token = ApiHelper.getToken()

RequestObject userReq = new RestRequestObjectBuilder()
    .withRestUrl('https://dummyjson.com/users/3124')
    .withRestRequestMethod('GET')
    .withHttpHeaders(ApiHelper.getAuthHeaders(token))
    .build()

def response = WS.sendRequest(userReq)

WS.verifyResponseStatusCode(response, 404)

def message = WS.getElementPropertyValue(response, 'message')
assert message != null : "Error message should exist!"

println "TC-USER-004 PASSED — Non-existent user correctly returned 404: ${message}"