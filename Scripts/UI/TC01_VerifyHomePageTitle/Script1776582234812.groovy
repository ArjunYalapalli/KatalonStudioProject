import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.navigateToUrl(GlobalVariable.ApplicationURL_UI)
WebUI.click(findTestObject('Object Repository/HomePage/btn_SignUp_Login'))

