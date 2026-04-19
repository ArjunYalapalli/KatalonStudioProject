import common.FileHandlers
import ui.UIHelper
UIHelper ui = new UIHelper()

//ui.click(findTestObject('Login/btnLogin'))

//ui.setText(ui.dynamicXpath("//input[@name='email']"), "test@test.com")

//ui.clickShadow("custom-login", "button.login")



// Write data

FileHandlers fh = new FileHandlers()

fh.setCellData("Data Files/AppData.xlsx", "SignUpDetails", 1, 2, "arjun@gmail.com")

// Read data
String username = fh.getCellData("Data Files/AppData.xlsx", "SignUpDetails", 1, 2)
println(username)