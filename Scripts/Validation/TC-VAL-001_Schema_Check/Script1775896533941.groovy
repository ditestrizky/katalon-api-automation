import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import groovy.json.JsonSlurper
import common.ApiHelper

def token = ApiHelper.getToken()

RequestObject userReq = new RestRequestObjectBuilder()
    .withRestUrl('https://dummyjson.com/auth/me')
    .withRestRequestMethod('GET')
    .withHttpHeaders(ApiHelper.getAuthHeaders(token))
    .build()

def response = WS.sendRequest(userReq)
WS.verifyResponseStatusCode(response, 200)


def json = new JsonSlurper().parseText(response.getResponseBodyContent())

def requiredKeys = ['id', 'firstName', 'lastName', 'email', 'username', 'role', 'image']
requiredKeys.each { key ->
    assert json.containsKey(key) : "Missing required field: ${key}"
}

println "TC-VAL-001 PASSED — All required schema fields present"