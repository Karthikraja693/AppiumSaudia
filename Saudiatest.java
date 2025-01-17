package com.lao.appiu.android;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Saudiatest {

	public static AndroidDriver driver;

	private String passport;
	private String Email;
	private String FirstName;
	private String LastName;
	private String password;

	@BeforeEach
	public void setUp() throws IOException {

		FileInputStream stream = new FileInputStream("config.properties");
		Properties properties = new Properties();
		properties.load(stream);
		passport = properties.getProperty("passport");
		Email = properties.getProperty("Email");
		FirstName = properties.getProperty("FirstName");
		LastName = properties.getProperty("LastName");
		password = properties.getProperty("password");

		UiAutomator2Options options = new UiAutomator2Options();
		options.setCapability("platformName", "Android");
		options.setCapability("platformVersion", "14");
		options.setCapability("udid", "10BE1D1652000E5");
		options.setCapability("deviceName", "KarthikIQ");
		options.setCapability("appPackage", "com.saudia.test.pprd");
		options.setCapability("appActivity", "com.saudi.airline.presentation.common.main.MainActivity type=2"); // Keep
																												// type=2

		try {
			driver = new AndroidDriver(new URI("http://127.0.0.1:4723/wd/hub").toURL(), options);
			Thread.sleep(6000); // Consider replacing this with an appropriate wait condition
		} catch (MalformedURLException | URISyntaxException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFirst() throws InterruptedException {
		allowAppPermission();
		clickJoin();
		joinAlfursan();
		selectDateOfBirth();
		selectNationality();
		scrollAndSelectNationality();
		selectResidence();
		searchAndSelectResidence();
		addPhone();
		addEmail();
		addPassword();
		checkboxClick();
		secondCheckbox();
		clickNext();
		selectTitle();
		addFirstName();
		addLastName();
		selectIssueCountry();
		scrollAndSelectIssuingCountry();
		clickSubmit();

	}

	public void allowAppPermission() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		while (driver.findElements(AppiumBy.xpath("//android.widget.Button[@resource-id='android:id/button2']"))
				.size() > 0) {
			WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(
					AppiumBy.xpath("//android.widget.Button[@resource-id='android:id/button2']")));
			button.click();
			wait.until(ExpectedConditions.invisibilityOf(button)); // Wait for the button to disappear
		}
	}

	public void clickJoin() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		while (driver.findElements(By.xpath("//android.widget.HorizontalScrollView[@text=\"Join AlFursan\"]"))
				.size() > 0) {
			WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(
					AppiumBy.xpath("//android.widget.HorizontalScrollView[@text=\"Join AlFursan\"]")));
			if (button.isDisplayed()) {
				Thread.sleep(3000);
				button.click();
				/* wait.until(ExpectedConditions.invisibilityOf(button)); */// Wait for the button to disappear
			}
		}
	}

	public void joinAlfursan() {
		WebElement inputField = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(
						AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")));

		inputField.click(); // Focus the field
		inputField.sendKeys(passport); // Now send the keys

	}

	public void selectDateOfBirth() throws InterruptedException {
		// Wait for the Date of Birth field to be visible and clickable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dateDropdown = wait.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.accessibilityId("Date of birth  text field read only dropdown collapsed")));

		// Perform the tap action using the W3C Actions API
		tapElement(driver, dateDropdown);
		Thread.sleep(3000);
		// Wait for the calendar to be visible
		WebElement yearSelector = wait
				.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Year Selector")));
		yearSelector.click(); // Open the year dropdown or scrollable view

		// Select year 1999
		WebElement year2000 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='2000']")));

		year2000.click();

		// Select month September
		WebElement monthSelector = driver.findElement(AppiumBy.accessibilityId("2000-10-04, WEDNESDAY"));
		monthSelector.click();

		WebElement confirmDate = driver.findElement(AppiumBy.accessibilityId("Click here to confirm date"));
		confirmDate.click();
	}

	public void tapElement(WebDriver driver, WebElement element) {
		// Define the touch input
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Get the center of the element to tap on
		int centerX = element.getRect().x + (element.getRect().width / 2);
		int centerY = element.getRect().y + (element.getRect().height / 2);

		// Create the sequence of actions for the tap
		Sequence tap = new Sequence(finger, 0)
				.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the tap
		((RemoteWebDriver) driver).perform(Collections.singletonList(tap));
	}

	public void selectNationality() throws InterruptedException {
		// Wait for the nationality dropdown button to be visible and clickable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement nationDropdown = wait
				.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Select nationality button")));

		// Tap on the dropdown to open it
		tapElement(driver, nationDropdown);
		Thread.sleep(2000);

	}

	public void scrollAndSelectNationality() throws InterruptedException {
		boolean isFound = false;

		while (!isFound) {
			try {
				// Attempt to locate the element
				WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@text=\"India\"]"));

				if (element.isDisplayed()) {
					element.click(); // Select the nationality
					isFound = true; // Element is found, exit the loop
				}
			} catch (NoSuchElementException e) {
				// If the element is not found, scroll down
				Dimension dimension = driver.manage().window().getSize();
				int start_x = (int) (dimension.width * 0.5);
				int start_y = (int) (dimension.height * 0.8);
				int end_x = start_x;
				int end_y = (int) (dimension.height * 0.2);

				// Using W3C Actions API to perform the scroll gesture
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence scroll = new Sequence(finger, 1);
				scroll.addAction(
						finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start_x, start_y));
				scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
				scroll.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(),
						end_x, end_y));
				scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				driver.perform(Arrays.asList(scroll)); // Perform the scroll action

				Thread.sleep(1000); // Wait for the scroll to finish
			}
		}
	}

	public void selectResidence() throws InterruptedException {
		// Wait for the nationality dropdown button to be visible and clickable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement countryDropdown = wait.until(
				ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("SelectCountry of residence button")));

		// Tap on the dropdown to open it
		tapElement(driver, countryDropdown);
		Thread.sleep(3000);

	}

	public void searchAndSelectResidence() throws InterruptedException {

		// Wait for the search field (using XPath to locate EditText element under
		// "Country/Territory" label)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement searchField = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText")));
	
		
		searchField.sendKeys("India");

		// Wait for the dropdown list and select "India" by visible text
		WebElement indiaOption = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='India']")));
		Thread.sleep(3000);
		indiaOption.click();

	}

	public void addPhone() throws InterruptedException {

		// Wait for the search field (using XPath to locate EditText element under
		// "Country/Territory" label)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement phoneNumber = wait.until(ExpectedConditions.presenceOfElementLocated(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));

		Thread.sleep(3000);
		phoneNumber.sendKeys("8095381136");

	}

	public void addEmail() throws InterruptedException {

		// Wait for the search field (using XPath to locate EditText element under
		// "Country/Territory" label)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement emailID = wait.until(ExpectedConditions.presenceOfElementLocated(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(2)")));

		Thread.sleep(3000);
		emailID.sendKeys(Email);
	}

	public void addPassword() throws InterruptedException {

		// Wait for the search field (using XPath to locate EditText element under
		// "Country/Territory" label)

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement passwordField = driver.findElement(AppiumBy.androidUIAutomator(

				"new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().className(\"android.widget.EditText\").instance(3));"));

		wait.until(ExpectedConditions.visibilityOf(passwordField));
		wait.until(ExpectedConditions.elementToBeClickable(passwordField));
		passwordField.click();
		passwordField.sendKeys(password);
	}

	public void checkboxClick() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// First attempt with UiScrollable by description
		try {
			WebElement checkBox = driver
					.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
							+ ".scrollIntoView(new UiSelector().description(\"I accept and agree to the Terms of Use.\"));"));

			// Wait until the element is visible and clickable
			wait.until(ExpectedConditions.visibilityOf(checkBox));
			wait.until(ExpectedConditions.elementToBeClickable(checkBox));

			// Click the checkbox
			checkBox.click();
		} catch (NoSuchElementException e) {
			// Fallback: Attempt to find the element by XPath
			System.out.println("Element not found by description, attempting by content-desc...");

			try {
				WebElement checkBoxAlt = driver.findElement(
						AppiumBy.xpath("//android.view.View[@content-desc='I accept and agree to the Terms of Use.']"));

				wait.until(ExpectedConditions.visibilityOf(checkBoxAlt));
				wait.until(ExpectedConditions.elementToBeClickable(checkBoxAlt));

				// Get the location of the checkbox and perform a tap using W3C actions
				Point p = checkBoxAlt.getLocation();

				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence tap = new Sequence(finger, 1);
				tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(),
						p.getX() + 10, p.getY() + 10)); // Move to the checkbox location
				tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())); // Touch down
				tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Touch up

				// Perform the tap
				driver.perform(Collections.singletonList(tap));

			} catch (NoSuchElementException ex) {
				System.out.println("Checkbox could not be found by both scrolling and fallback.");
				throw ex; // Rethrow exception if both attempts fail
			}
		}
	}

	public void secondCheckbox() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// First attempt with UiScrollable by description or use resource-id to
		// differentiate checkboxes
		try {
			WebElement privacyCheck = driver
					.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
							+ ".scrollIntoView(new UiSelector().description(\"I agree to the use and disclosure of data in accordance with the Terms of Use and Privacy Policy.\"))"));

			wait.until(ExpectedConditions.visibilityOf(privacyCheck));
			wait.until(ExpectedConditions.elementToBeClickable(privacyCheck));

			// Use PointerInput to tap the checkbox
			Point p = privacyCheck.getLocation();

			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence tap = new Sequence(finger, 1);
			tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), p.getX() + 10,
					p.getY() + 10)); // Move to the checkbox location
			tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())); // Touch down
			tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Touch up

			// Perform the tap action
			driver.perform(Collections.singletonList(tap));

		} catch (NoSuchElementException e) {
			System.out.println("Element cannot be found by description, attempting by XPath...");

			try {
				WebElement privacyCheckAlt = driver.findElement(AppiumBy.xpath(
						"//android.view.View[@content-desc='I agree to the use and disclosure of data in accordance with the Terms of Use and Privacy Policy.']"));

				wait.until(ExpectedConditions.visibilityOf(privacyCheckAlt));
				wait.until(ExpectedConditions.elementToBeClickable(privacyCheckAlt));

				// Use PointerInput to tap the checkbox
				Point pAlt = privacyCheckAlt.getLocation();

				PointerInput fingerAlt = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence tapAlt = new Sequence(fingerAlt, 1);
				tapAlt.addAction(fingerAlt.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(),
						pAlt.getX() + 10, pAlt.getY() + 10)); // Move to the checkbox location
				tapAlt.addAction(fingerAlt.createPointerDown(PointerInput.MouseButton.LEFT.asArg())); // Touch down
				tapAlt.addAction(fingerAlt.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Touch up

				// Perform the tap action
				driver.perform(Collections.singletonList(tapAlt));

			} catch (NoSuchElementException exc) {
				System.out.println("Second checkbox could not be found by both scrolling and fallback.");
				throw exc; // Rethrow exception if both attempts fail
			}
		}
	}

	public void clickNext() throws InterruptedException {

		// Wait for the search field (using XPath to locate EditText element under
		// "Country/Territory" label)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement nextButton = wait.until(ExpectedConditions.presenceOfElementLocated(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(26)")));
		Thread.sleep(3000);
		nextButton.click();
	}

	public void selectTitle() throws InterruptedException {
		// Wait for the nationality dropdown button to be visible and clickable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement titleDropdown = wait.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.accessibilityId("Title selected  dropdown collapsed")));

		// Tap on the dropdown to open it
		tapElement(driver, titleDropdown);
		Thread.sleep(3000);

		WebElement maleOption = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Mr.\"]")));
		Thread.sleep(3000);
		maleOption.click();

	}

	public void addFirstName() throws InterruptedException {

		// Wait for the search field (using XPath to locate EditText element under
		// "Country/Territory" label)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement firstName = wait.until(ExpectedConditions.presenceOfElementLocated(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
		Thread.sleep(3000);
		firstName.sendKeys(FirstName);
	}

	public void addLastName() throws InterruptedException {

		// Wait for the search field (using XPath to locate EditText element under
		// "Country/Territory" label)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement lastName = wait.until(ExpectedConditions.presenceOfElementLocated(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
		Thread.sleep(3000);
		lastName.sendKeys(LastName);
	}

	public void selectIssueCountry() throws InterruptedException {
		// Wait for the nationality dropdown button to be visible and clickable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement issuingCountry = wait.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.accessibilityId("SelectPassport issuance country button")));

		// Tap on the dropdown to open it
		tapElement(driver, issuingCountry);
		Thread.sleep(3000);

	}

	public void scrollAndSelectIssuingCountry() throws InterruptedException {
		boolean isFound = false;

		while (!isFound) {
			try {
				// Attempt to locate the element
				WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@text=\"India\"]"));

				if (element.isDisplayed()) {
					element.click(); // Select the nationality
					isFound = true; // Element is found, exit the loop
				}
			} catch (NoSuchElementException e) {
				// If the element is not found, scroll down
				Dimension dimension = driver.manage().window().getSize();
				int start_x = (int) (dimension.width * 0.5);
				int start_y = (int) (dimension.height * 0.8);
				int end_x = start_x;
				int end_y = (int) (dimension.height * 0.2);

				// Using W3C Actions API to perform the scroll gesture
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence scroll = new Sequence(finger, 1);
				scroll.addAction(
						finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start_x, start_y));
				scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
				scroll.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(),
						end_x, end_y));
				scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				driver.perform(Arrays.asList(scroll)); // Perform the scroll action

				Thread.sleep(2000); // Wait for the scroll to finish
			}
		}
	}

	public void clickSubmit() throws InterruptedException {

		// Wait for the search field (using XPath to locate EditText element under
		// "Country/Territory" label)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(17)")));
		Thread.sleep(3000);
		submitButton.click();
	}

}

/*
 * @AfterEach public void tearDown() { if (driver != null) { driver.quit(); } }
 */
