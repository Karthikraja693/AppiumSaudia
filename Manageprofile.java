package com.lao.appiu.android;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Manageprofile {

	public static AndroidDriver driver;

	private String AlFursanID;
	private String PasswordLogin;
	private String NationalID;

	@BeforeEach
	public void profileSetUp() throws IOException {

		FileInputStream stream = new FileInputStream("config.properties");
		Properties properties = new Properties();
		properties.load(stream);
		AlFursanID = properties.getProperty("AlFursanID");
		PasswordLogin = properties.getProperty("PasswordLogin");
		NationalID = properties.getProperty("NationalID");

		UiAutomator2Options options = new UiAutomator2Options();
		options.setCapability("platformName", "Android");
		options.setCapability("platformVersion", "14");
		options.setCapability("udid", "10BE1D1652000E5");
		options.setCapability("deviceName", "KarthikIQ");
		options.setCapability("appPackage", "com.saudia.test.pprd");
		options.setCapability("appActivity", "com.saudi.airline.presentation.common.main.MainActivity type=2"); 
		try {
			driver = new AndroidDriver(new URI("http://127.0.0.1:4723/wd/hub").toURL(), options);
			Thread.sleep(6000); // Consider replacing this with an appropriate wait condition
		} catch (MalformedURLException | URISyntaxException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFirst() throws InterruptedException, IOException {
		allowAppPermission();
		clickLogin();
		alFursanID();
		idPassword();
		loginButton();
		agreeButton();
		clickSkip();
		clickDontAllow();
		declineButton();
		dashboardButton();
		profileIcon();
		manageButton();
		personalButton();
		countryResidence();
		clickDone();
		personalTwo();
		nationalSelect();
		clickNationalDone();
		nationalNavigate();
		enterNational();
		doneNational();
		contactEdit();
		contactEmail();
		enterEmailAndCaptureError();
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

	public void clickLogin() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		while (driver.findElements(By.xpath("//android.widget.HorizontalScrollView[@text=\"Login\"]")).size() > 0) {
			WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(
					AppiumBy.xpath("//android.widget.HorizontalScrollView[@text=\"Login\"]")));
			if (button.isDisplayed()) {
				Thread.sleep(3000);
				button.click();
				/* wait.until(ExpectedConditions.invisibilityOf(button)); */// Wait for the button to disappear
			}
		}
	}

	public void alFursanID() {
		WebElement memberID = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy
						.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));

		memberID.click(); // Focus the field
		memberID.sendKeys(AlFursanID); // Now send the keys

	}

	public void idPassword() {
		WebElement password = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy
						.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));

		password.click(); // Focus the field
		password.sendKeys(PasswordLogin); // Now send the keys
		driver.hideKeyboard();

	}

	public void loginButton() throws InterruptedException {
		WebElement loginClick = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Login Button")));
		loginClick.click();
		Thread.sleep(7000);
	}

	public void agreeButton() throws InterruptedException {
		WebElement agreeClick = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
				.visibilityOfElementLocated(AppiumBy.accessibilityId("Click here to Agree and continue button")));
		agreeClick.click();
		Thread.sleep(2000);
	}

	public void clickSkip() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		while (driver.findElements(By.xpath("//android.widget.HorizontalScrollView[@text=\"Skip\"]")).size() > 0) {
			WebElement skipButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
					AppiumBy.xpath("//android.widget.HorizontalScrollView[@text=\"Skip\"]")));
			if (skipButton.isDisplayed()) {
				Thread.sleep(2000);
				skipButton.click();
				/* wait.until(ExpectedConditions.invisibilityOf(button)); */// Wait for the button to disappear
			}
		}
	}

	public void clickDontAllow() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		while (driver.findElements(By.id("com.android.permissioncontroller:id/permission_deny_button")).size() > 0) {
			WebElement dontAllow = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/permission_deny_button")));
			if (dontAllow.isDisplayed()) {
				Thread.sleep(2000);
				dontAllow.click();
				/* wait.until(ExpectedConditions.invisibilityOf(button)); */// Wait for the button to disappear
			}
		}
	}

	public void declineButton() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		/*
		 * while
		 * (driver.findElements(By.xpath("//android.widget.TextView[@text=\"Decline\"]")
		 * ) .size() > 0) {
		 */
		WebElement decline = wait
				.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Decline Button")));
		if (decline.isDisplayed()) {
			Thread.sleep(2000);
			decline.click();
			/* wait.until(ExpectedConditions.invisibilityOf(button)); */// Wait for the button to disappear
		}

	}

	public void dashboardButton() throws InterruptedException {
		WebElement alfursanButton = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("AlFursan Tab 4 of 4")));
		alfursanButton.click();
		Thread.sleep(6000);
	}

	public void profileIcon() throws InterruptedException {
		WebElement profileButton = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
				.visibilityOfElementLocated(AppiumBy.accessibilityId("This is AlFursan profile icon")));

		profileButton.click(); // Focus the field
		Thread.sleep(3000);

	}

	public void manageButton() throws InterruptedException {
		WebElement manageClick = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Manage ProfileButton")));

		manageClick.click(); // Focus the field
		Thread.sleep(6000);

	}

	public void personalButton() throws InterruptedException {
		WebElement infoButton = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
				.visibilityOfElementLocated(AppiumBy.accessibilityId("EditPersonal informationbutton")));

		infoButton.click(); // Focus the field
		Thread.sleep(3000);

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

	public void countryResidence() throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    // Identify the parent element that uniquely represents the Country of Residence dropdown
	    WebElement residenceDropdownParent = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(26)")));	
	    
	    // Check the current selected country
	    WebElement selectedCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//android.widget.EditText[@text='Saudi Arabia' or @text='India']")));
	    
	    String currentCountry = selectedCountry.getText();
	    System.out.println("Selected Country: " + selectedCountry);
	    
	    // Tap on the dropdown to open it
	    WebElement residenceDropdown = wait.until(
	            ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator
	                    ("new UiSelector().className(\"android.view.View\").instance(27)")));
	    tapElement(driver, residenceDropdown);
	    Thread.sleep(3000); // Ensure the dropdown has opened
	    
	    // Select based on current country
	    if (currentCountry.equalsIgnoreCase("India")) {
	        WebElement saudiaOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                AppiumBy.androidUIAutomator("new UiSelector().text(\"Saudi Arabia\")")));
	        saudiaOption.click();
	    } else if (currentCountry.equalsIgnoreCase("Saudi Arabia")) {
	        WebElement searchField = wait.until(ExpectedConditions.presenceOfElementLocated(
	                By.xpath("//android.widget.EditText")));
	        searchField.sendKeys("India");

	        // Select India from the filtered results
	        WebElement indiaOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//android.widget.TextView[@text='India']")));
	        Thread.sleep(3000);  // Optional delay to allow the list to load
	        indiaOption.click();
	    }

	    Thread.sleep(4000);  // Optional delay to ensure the selection is applied
	}


	public void clickDone() throws InterruptedException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		while (driver.findElements(By.xpath("//android.widget.HorizontalScrollView[@text=\"Done\"]")).size() > 0) {
			WebElement doneButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
					AppiumBy.xpath("//android.widget.HorizontalScrollView[@text=\"Done\"]")));
			if (doneButton.isDisplayed()) {
				Thread.sleep(3000);
				doneButton.click();
				/* wait.until(ExpectedConditions.invisibilityOf(button)); */// Wait for the button to disappear
				wait.until(ExpectedConditions.invisibilityOf(doneButton));
				takeScreenshot("screenshot_after_done_click.png");
			}
		}
	}

	// Method to capture and save the screenshot to a specific folder in Downloads
	public void takeScreenshot(String fileName) throws IOException {
		// Take screenshot using TakesScreenshot interface
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String downloadPath = "C:/Users/karth/screenshots";

		// Specify the location to save the screenshot
		File destinationFile = new File(downloadPath + "/" + fileName);

		// Copy the screenshot to the desired location
		Files.copy(screenshot, destinationFile);

		System.out.println("Screenshot saved: " + destinationFile.getAbsolutePath());
	}

	public void personalTwo() throws InterruptedException {
		WebElement informationButton = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
				.visibilityOfElementLocated(AppiumBy.accessibilityId("EditPersonal informationbutton")));

		informationButton.click(); // Focus the field
		Thread.sleep(6000);

	}

	public void nationalSelect() throws InterruptedException {
		// Wait for the residence dropdown button to be visible and clickable
	/*	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement selectedNational = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//android.widget.EditText[@text='Saudi Arabia' or @text='India']")));

		// Get the current country text
		String currentNational = selectedNational.getText();*/

		// Locate the element displaying the currently selected country
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement nationalDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(25)"))); 
		tapElement(driver, nationalDropdown);
		Thread.sleep(3000); // Allow the dropdown to open

		// If the current selection is India, select Saudi Arabia; otherwise, select
		// India
	/*	if (currentNational.equalsIgnoreCase("India")) {*/
			WebElement saudiaNation = wait.until(ExpectedConditions.visibilityOfElementLocated(
					AppiumBy.androidUIAutomator("new UiSelector().text(\"Saudi Arabia\")")));
			saudiaNation.click();
		/*} else if (currentNational.equalsIgnoreCase("Saudi Arabia")) {
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement searchField = wait1
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText")));

			searchField.sendKeys("India");

			// Wait for the dropdown list and select "India" by visible text
			WebElement indiaNation = wait1.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='India']")));
			Thread.sleep(3000);
			indiaNation.click();
		}*/

		Thread.sleep(4000); // Optional: Ensure the page refreshes after selection
	}

	public void clickNationalDone() throws InterruptedException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		while (driver.findElements(By.xpath("//android.widget.HorizontalScrollView[@text=\"Done\"]")).size() > 0) {
			WebElement nationalDone = wait.until(ExpectedConditions.visibilityOfElementLocated(
					AppiumBy.xpath("//android.widget.HorizontalScrollView[@text=\"Done\"]")));
			if (nationalDone.isDisplayed()) {
				Thread.sleep(3000);
				nationalDone.click();
				// Wait for the button to disappear
				wait.until(ExpectedConditions.invisibilityOf(nationalDone));
				Thread.sleep(5000);
				takeScreenshot("screenshot_after_done_click.png");
			}
		}
	}

	public void nationalNavigate() throws InterruptedException {
		WebElement residenceArrow = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId(
						"Please provide your National ID as your nationality has been updatedbutton")));

		residenceArrow.click(); // Focus the field
		Thread.sleep(3000);

	}

	public void enterNational() throws InterruptedException {
		WebElement nationalInput = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
		/* ("//android.widget.EditText[@text=\"National ID number\"]"))); */

		nationalInput.click(); // Focus the field
		nationalInput.sendKeys(NationalID); // Now send the keys
		Thread.sleep(2000);

	}

	public void doneNational() throws InterruptedException, IOException {
		WebElement nationalEntered = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("DoneButton")));

		nationalEntered.click(); // Focus the field
		Thread.sleep(9000);
		takeScreenshot("screenshot_profile_info_nationalid_updated.png");
	}

	public void contactEdit() throws InterruptedException {
		WebElement contactButton = new WebDriverWait(driver, Duration.ofSeconds(20)).until(
				ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("EditContact details button")));

		contactButton.click(); // Focus the field
		Thread.sleep(6000);

	}

	public void contactEmail() throws InterruptedException {
		WebElement emailEdit = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(
						AppiumBy.accessibilityId("Email address. karthiktester885+35@gmail.com. button")));

		emailEdit.click(); // Focus the field
		Thread.sleep(6000);
	}

	public void enterEmailAndCaptureError() throws InterruptedException, IOException {
		// Wait for the email field to be visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement emailField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText"))); 
		emailField.clear();
		System.out.println("Cleared the existing email address.");

		// Enter the already used email ID
		emailField.sendKeys("karthiktester885@gmail.com");

		WebElement doneEmail = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("DoneButton")));

		doneEmail.click(); // Focus the field
		Thread.sleep(2000);

		// Wait for the error message to appear
		/*
		 * WebElement errorMessage =
		 * wait.until(ExpectedConditions.visibilityOfElementLocated( By.
		 * xpath("/android.widget.TextView[@text=\"This email address is already linked to an account.\"]"
		 * )));
		 * 
		 * // Optionally, check if the error message is displayed if
		 * (errorMessage.isDisplayed()) { System.out.println("Error message displayed: "
		 * + errorMessage.getText());
		 */

		// Take a screenshot after the error message appears
		takeScreenshot("email_error_screenshot.png");
	}

}