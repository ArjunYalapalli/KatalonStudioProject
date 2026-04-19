package ui

import org.openqa.selenium.*
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.testobject.*
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class UIHelper {

	def int DEFAULT_TIMEOUT = 20
	def int RETRY_COUNT = 3

	// ==========================
	// 🔹 DRIVER
	// ==========================
	def WebDriver getDriver() {
		return DriverFactory.getWebDriver()
	}

	// ==========================
	// 🔹 WAIT UTILS
	// ==========================
	def WebElement waitForElement(TestObject to, int timeout = DEFAULT_TIMEOUT) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeout)
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(getBy(to)))
		KeywordUtil.logInfo("Element visible: " + to.getObjectId())
		return element
	}

	def waitForClickable(TestObject to, int timeout = DEFAULT_TIMEOUT) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeout)
		wait.until(ExpectedConditions.elementToBeClickable(getBy(to)))
		KeywordUtil.logInfo("Element clickable: " + to.getObjectId())
	}

	// ==========================
	// 🔹 CORE ACTION WRAPPER
	// ==========================
	def performAction(String actionName, Closure action) {
		int attempts = 0
		while (attempts < RETRY_COUNT) {
			try {
				action.call()
				return
			} catch (Exception e) {
				attempts++
				KeywordUtil.logInfo("Retry " + attempts + " for action: " + actionName)

				if (attempts == RETRY_COUNT) {
					WebUI.takeScreenshot()
					KeywordUtil.markFailed("Action failed: " + actionName + " | Error: " + e.getMessage())
				}

				WebUI.delay(1)
			}
		}
	}

	// ==========================
	// 🔹 CLICK
	// ==========================
	def click(TestObject to) {
		performAction("Click " + to.getObjectId(), {
			waitForClickable(to)
			WebElement element = waitForElement(to)
			highlight(element)

			try {
				element.click()
			} catch (Exception e) {
				jsClick(element)
			}
		})
	}

	// ==========================
	// 🔹 SET TEXT
	// ==========================
	def setText(TestObject to, String value) {
		performAction("SetText " + to.getObjectId(), {
			WebElement element = waitForElement(to)
			highlight(element)
			element.clear()
			element.sendKeys(value)
			KeywordUtil.logInfo("Entered: " + value)
		})
	}

	// ==========================
	// 🔹 GET TEXT
	// ==========================
	def getText(TestObject to) {
		WebElement element = waitForElement(to)
		highlight(element)
		String text = element.getText()
		KeywordUtil.logInfo("Text: " + text)
		return text
	}

	// ==========================
	// 🔹 VERIFY
	// ==========================
	def verifyText(TestObject to, String expected) {
		String actual = getText(to)
		if (!actual.equals(expected)) {
			WebUI.takeScreenshot()
			KeywordUtil.markFailed("Expected: " + expected + " but got: " + actual)
		}
	}

	// ==========================
	// 🔹 DROPDOWN
	// ==========================
	def selectByLabel(TestObject to, String label) {
		performAction("Select dropdown", {
			WebUI.selectOptionByLabel(to, label, false)
		})
	}

	// ==========================
	// 🔹 DYNAMIC OBJECT
	// ==========================
	def TestObject dynamicXpath(String xpath) {
		TestObject to = new TestObject("dynamicXpath")
		to.addProperty("xpath", ConditionType.EQUALS, xpath)
		return to
	}

	def TestObject dynamicCss(String css) {
		TestObject to = new TestObject("dynamicCss")
		to.addProperty("css", ConditionType.EQUALS, css)
		return to
	}

	// ==========================
	// 🔹 HIGHLIGHT
	// ==========================
	def highlight(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver()
		js.executeScript("arguments[0].style.border='2px solid red'", element)
	}

	// ==========================
	// 🔹 JS CLICK
	// ==========================
	def jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver()
		js.executeScript("arguments[0].click();", element)
		KeywordUtil.logInfo("Clicked using JS fallback")
	}

	// ==========================
	// 🔹 SHADOW DOM
	// ==========================
	def WebElement getShadowElement(String hostCss, String innerCss) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver()

		WebElement host = getDriver().findElement(By.cssSelector(hostCss))
		WebElement shadowRoot = (WebElement) js.executeScript("return arguments[0].shadowRoot", host)

		return shadowRoot.findElement(By.cssSelector(innerCss))
	}

	def clickShadow(String hostCss, String innerCss) {
		performAction("Shadow Click", {
			WebElement element = getShadowElement(hostCss, innerCss)
			highlight(element)

			try {
				element.click()
			} catch (Exception e) {
				jsClick(element)
			}
		})
	}

	// ==========================
	// 🔹 UTIL
	// ==========================
	def By getBy(TestObject to) {
		for (TestObjectProperty prop : to.getProperties()) {
			if (prop.getName().equals("xpath")) {
				return By.xpath(prop.getValue())
			} else if (prop.getName().equals("css")) {
				return By.cssSelector(prop.getValue())
			}
		}
		throw new RuntimeException("Locator not found")
	}
}