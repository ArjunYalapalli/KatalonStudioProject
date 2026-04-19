package ui

import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import common.BaseClass


public class SignUp {

	UIHelper ui = new UIHelper()
	
	
	@Keyword
	def genarateSignUpData(int rowNumber) {
		BaseClass base = new BaseClass()
		// set name
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', rowNumber, 0, base.utils.getRandomString())
		// set email
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', rowNumber, 1, base.utils.getRandomEmail())
		// set password
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', rowNumber, 3, base.utils.getRandomString())
		// first Name
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', rowNumber, 9, base.utils.getRandomString())
		// last name
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', rowNumber, 10, base.utils.getRandomString())
		// Company
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', rowNumber, 11, base.utils.getRandomString())
		// Address
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', rowNumber, 12, base.utils.getRandomString())
		// Address 2
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', rowNumber, 13, base.utils.getRandomString())
		// mobile number
		base.files.setCellData('Data Files/AppData.xlsx', 'SignUpDetails', rowNumber, 18, base.utils.getRandomMobileNumber())
	}

	@Keyword
	def createNewUser(int rowNumber) {
		genarateSignUpData(rowNumber)

		String Name = findTestData('Data Files/SignUpDetails').getValue('Name', rowNumber)
		String email = findTestData('Data Files/SignUpDetails').getValue('EmailAddress', rowNumber)
		
		ui.click(findTestObject('Object Repository/HomePage/btn_SignUp_Login'))
		ui.setText(findTestObject('Object Repository/LoginPage/input_Name'), Name)
		ui.setText(findTestObject('Object Repository/LoginPage/input_Email'), email)

		ui.click(findTestObject('Object Repository/LoginPage/btn_SignUp'))
		
		// Write code for fill all form and genarate user and store user login Details in 'LoginDetails' sheet
		
		return email
	}
}
