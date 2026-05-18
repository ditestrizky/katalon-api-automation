package common

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.RestRequestObjectBuilder
import com.kms.katalon.core.testobject.ConditionType

public class ApiHelper {

	static String getToken() {
		RequestObject loginRequest = new RestRequestObjectBuilder()
				.withRestUrl('https://dummyjson.com/auth/login')
				.withRestRequestMethod('POST')
				.withTextBodyContent('{"username": "emilys", "password": "emilyspass"}')
				.withHttpHeaders([
					new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json')
				])
				.build()
		def loginResponse = WS.sendRequest(loginRequest)

		def token = WS.getElementPropertyValue(loginResponse, 'accessToken')
		return token
	}

	static List getAuthHeaders(String token) {
		return [
			new TestObjectProperty('Authorization', ConditionType.EQUALS, "Bearer ${token}"),
			new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json')
		]
	}
}
