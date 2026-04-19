import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import common.BaseClass
import ui.UIHelper as UIHelper

UIHelper ui = new UIHelper()

//ui.click(findTestObject('Login/btnLogin'))
//ui.setText(ui.dynamicXpath("//input[@name='email']"), "test@test.com")
//ui.clickShadow("custom-login", "button.login")

// Write data
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


// Read data
// Method one 
base.files.getCellData('Data Files/AppData.xlsx', 'SignUpDetails', 1, 2)

// Method two
String username = findTestData('Data Files/SignUpDetails').getValue('Title', 1)

println(username)