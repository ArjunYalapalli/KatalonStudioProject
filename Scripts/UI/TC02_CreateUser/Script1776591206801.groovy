import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import common.BaseClass
import internal.GlobalVariable

BaseClass base = new BaseClass()
rowNum = 1

GlobalVariable.DynamicEmail = base.signUp.createNewUser(rowNum)


