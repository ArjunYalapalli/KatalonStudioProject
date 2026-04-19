package ui

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI




public class Login {

	UIHelper ui = new UIHelper()
	
	@Keyword
	def signIn(String email, password) {
		ui.click(findTestObject(''))
		ui.setText(findTestObject(''), email)
		ui.setText(findTestObject(''), password)
		
	}
}
