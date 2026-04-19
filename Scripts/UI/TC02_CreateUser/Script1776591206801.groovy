import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.ApplicationURL_UI)
CustomKeywords.'ui.SignUp.createNewUser'()