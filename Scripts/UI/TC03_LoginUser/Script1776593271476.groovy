import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import common.BaseClass as BaseClass
import internal.GlobalVariable as GlobalVariable

BaseClass base = new BaseClass()

int rowNum = 1

String email = GlobalVariable.DynamicEmail

String password = findTestData('Data Files/SignUpDetails').getValue('Password', rowNum)

base.logIn.signIn()