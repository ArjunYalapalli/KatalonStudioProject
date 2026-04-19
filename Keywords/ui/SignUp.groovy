package ui

import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import common.BaseClass


public class SignUp {


	@Keyword
	def genarateSignUpData() {
		BaseClass base = new BaseClass()
		// set name
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', 1, 0, base.utils.getRandomString())
		// set email
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', 1, 1, base.utils.getRandomEmail())
		// set password
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', 1, 3, base.utils.getRandomString())
		// first Name
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', 1, 9, base.utils.getRandomString())
		// last name
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', 1, 10, base.utils.getRandomString())
		// Company
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', 1, 11, base.utils.getRandomString())
		// Address
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', 1, 12, base.utils.getRandomString())
		// Address 2
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', 1, 13, base.utils.getRandomString())
		// mobile number
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', 1, 18, base.utils.getRandomMobileNumber())
	}

	@Keyword
	def createNewUser() {
		genarateSignUpData()

		WebUI.click(findTestObject('Object Repository/HomePage/btn_SignUp_Login'))
		WebUI.sendKeys(findTestObject('Object Repository/LoginPage/input_Name'), findTestData('Data Files/SignUpDetails').getValue('Name', 1))
		WebUI.sendKeys(findTestObject('Object Repository/LoginPage/input_Email'), findTestData('Data Files/SignUpDetails').getValue('EmailAddress', 1))

		WebUI.click(findTestObject('Object Repository/LoginPage/btn_SignUp'))
		
		// Write code for fill all form and genarate user and store user login Details in 'LoginDetails' sheet
	}
}
