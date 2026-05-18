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

//assertion
def json = new JsonSlurper().parseText(response.getResponseBodyContent())

assert json.id != null : "id should not be null!"
assert json.firstName != null && json.firstName != '' : "firstName should not be null or empty!"
assert json.lastName != null && json.lastName != '' : "lastName should not be null or empty!"
assert json.email != null && json.email != '' : "email should not be null or empty!"
assert json.username != null && json.username != '' : "username should not be null or empty!"
assert json.role != null && json.role != '' : "role should not be null or empty!"
assert json.image != null && json.image != '' : "image should not be null or empty!"

println "TC-VAL-002 PASSED — All required fields present and not empty"