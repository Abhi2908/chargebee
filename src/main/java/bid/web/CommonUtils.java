package bid.web;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.github.javafaker.Faker;

public class CommonUtils extends TestBase {

	static WebDriver driver;;
	static final Logger logger = Logger.getLogger(CommonUtils.class.getName());

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;

	public CommonUtils() {
		this.driver = GetDriver.getDriver();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param by
	 * @return Open the Excel file
	 */
	public static void setExcelFile(String Path, String SheetName) throws Exception {
		try {

			FileInputStream ExcelFile = new FileInputStream(Path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * @param by
	 * @return Access the required test data sheet
	 */
	public static Object[][] getTableArray(String FilePath, String SheetName, int iTestCaseRow) throws Exception {
		String[][] tabArray = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int startCol = 1;
			int ci = 0, cj = 0;
			int totalRows = 1;
			int totalCols = 2;
			tabArray = new String[totalRows][totalCols];
			for (int j = startCol; j <= totalCols; j++, cj++) {
				tabArray[ci][cj] = getCellData(iTestCaseRow, j);
				logger.info(tabArray[ci][cj]);
			}
		} catch (FileNotFoundException e) {
			logger.info("Could not read the Excel sheet");
			logger.info(e.toString());
		} catch (IOException e) {
			logger.info("Could not read the Excel sheet");
			logger.info(e.toString());
		}
		return (tabArray);
	}

	/**
	 * @param by
	 * @return This method is to read the test data from the Excel cell, in this we
	 *         are passing parameters as Row num and Col num
	 */
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			logger.info(e.toString());
			return "";
		}
	}

	/**
	 * @param by
	 * @return clicking on link using java script
	 */
	public static Boolean accessLink(By by) {
		try {
			waitForPresenceOfElement(by);
			javaScriptScrollUsingBy(by);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			assert false;
			return false;
		}
	}

	/**
	 * @param by move to element and click through Action
	 */
	public static void moveToWebElementAndClick(By by) {
		try {
			waitForPresenceOfElement(by);
			javaScriptScrollUsingBy(by);
			Actions actions = new Actions(driver);
			WebElement element = driver.findElement(by);
			actions.moveToElement(element).click().build().perform();
			logger.info("The object was clicked successfuly");
		} catch (Exception e) {
			logger.error("The object was not clicked successfuly");
			assert (false);
		}
	}

	/**
	 * @param Method to click popup
	 */
	public static void clickAlertPopup() {
		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

	/**
	 * @param Method to click window popup
	 * @throws AWTException
	 */
	public static void clickOnScreenThroughRobot() throws AWTException {
		// try {
		Robot robot = new Robot();
		robot.setAutoDelay(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		/*
		 * } catch (Exception e) { logger.error(e); assert (false); }
		 */
	}

	/**
	 * @param by
	 * @return Method to wait for presence element
	 * 
	 */
	public static boolean waitForPresenceOfElement(By by) {
		try {
			int objectTimeOutSeconds = Integer.parseInt(getProps().getProperty("objectTimeout"));

			WebDriverWait waitDriver = new WebDriverWait(driver, objectTimeOutSeconds);
			waitDriver.until(ExpectedConditions.presenceOfElementLocated(by));
			logger.info("Waited success for " + by.toString());
			return true;
		} catch (WebDriverException e) {
			logger.error("Error is : " + e);
			Assert.fail();
			assert (false);
		}
		return false;
	}

	/**
	 * @param by
	 * @return Method to wait for a page load using fluent
	 * 
	 */
	@SuppressWarnings("unused")
	protected boolean waitUntilLoadingPage(final String text, final By by) {

		Wait<WebDriver> waitFluent = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(300))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

		WebElement element = waitFluent.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(by);
				String getTextOnPage = element.getText();
				if (getTextOnPage.equals(text)) {
					System.out.println(getTextOnPage);
					return element;
				} else {
					System.out.println("FluentWait Failed " + text);
					return null;
				}
			}
		});
		return false;

	}

	/**
	 * @param by
	 * @return Method to wait for an element clickable
	 * 
	 */
	public static boolean waitForElementToBeClickable(By by) {
		try {
			int objectTimeOutSeconds = Integer.parseInt(getProps().getProperty("objectTimeout"));

			WebDriverWait waitDriver = new WebDriverWait(driver, objectTimeOutSeconds);
			waitDriver.until(ExpectedConditions.elementToBeClickable(by));
			logger.info("Waited success for " + by.toString());
			return true;
		} catch (WebDriverException e) {
			logger.error(e);
			Assert.fail();
			assert (false);
		}
		return false;
	}

	public static boolean javaScriptTextEditor(By by) {
		try {
			WebElement text = driver.findElement(by);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].innerHTML = 'Automation text editor'", text);
			return true;
		} catch (WebDriverException e) {
			logger.error(e);
			Assert.fail();
			assert (false);
		}
		return false;

	}

	/**
	 * @param promo_weekend_checkbox
	 * @return Method to wait for an element clickable
	 * 
	 */
	public static boolean waitForVisibilityOfAllElements(List<WebElement> checkbox) {
		try {
			int objectTimeOutSeconds = Integer.parseInt(getProps().getProperty("objectTimeout"));

			WebDriverWait waitDriver = new WebDriverWait(driver, objectTimeOutSeconds);
			waitDriver.until(ExpectedConditions.visibilityOfAllElements(checkbox));
			logger.info("Waited success for " + checkbox.toString());
			return true;
		} catch (WebDriverException e) {
			logger.error(e);
			Assert.fail();
			assert (false);
		}
		return false;
	}

	/**
	 * @param by
	 * @return Method to wait for element visible
	 * 
	 */
	public static boolean visibilityOfElementLocated(By by) {
		try {
			int objectTimeOutSeconds = Integer.parseInt(getProps().getProperty("objectTimeout"));

			WebDriverWait waitDriver = new WebDriverWait(driver, objectTimeOutSeconds);
			waitDriver.until(ExpectedConditions.visibilityOfElementLocated(by));
			logger.info("Waited success for " + by.toString());
			return true;
		} catch (WebDriverException e) {
			logger.error(e);
			Assert.fail();
			assert (false);
		}
		return false;
	}

	/**
	 * @param loaderTwo_locator
	 * @return Method to wait for an if the element is invisible or not
	 * 
	 */
	public static boolean invisibilityOfElementLocated(By loader_locator) {
		try {
			int objectTimeOutSeconds = Integer.parseInt(getProps().getProperty("objectTimeout"));

			WebDriverWait waitDriver = new WebDriverWait(driver, objectTimeOutSeconds);
			waitDriver.until(ExpectedConditions.invisibilityOfElementLocated(loader_locator));
			logger.info("Waited success for " + loader_locator.toString());
			return true;
		} catch (WebDriverException e) {
			logger.error(e);
			Assert.fail();
			assert (false);
		}
		return false;
	}

	/**
	 * waits for backgrounds processes on the browser to complete
	 *
	 * @param timeOutInSeconds
	 */
	public static void waitForBrowserPageToLoad() {
		int objectTimeOutSeconds = Integer.parseInt(getProps().getProperty("DocumentReadyState"));
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("Welcome");
			}
		};
		try {
			WebDriverWait wait = new WebDriverWait(driver, objectTimeOutSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			error.printStackTrace();
		}
	}

	/**
	 * @param
	 * @return Method to close current window
	 * 
	 */
	public static void closeCurrentWindow() {
		try {
			Set<String> ids = driver.getWindowHandles();
			Iterator<String> it = ids.iterator();
			String parentId = it.next();
			String childId = it.next();
			driver.switchTo().window(childId);
			driver.close();
			driver.switchTo().window(parentId);
		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * @param
	 * @return Method to get another window
	 * 
	 */
	public static void getAnotherWindow(String URL) {
		try {
			driver.get(URL);
		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * Handling windows with window handler
	 * 
	 * @param windows
	 */
	public void switchFirstWindowAndSelectFirstTwoLocation() {

		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		// actions

		driver.switchTo().window(tabs2.get(0));

	}

	/**
	 * @param
	 * @return Method switch To Window
	 * 
	 */
	public static void switchToWindow(String targetTitle) {
		String origin = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			if (driver.getTitle().equals(targetTitle)) {
				return;
			}
		}
		driver.switchTo().window(origin);
	}

	/**
	 * Moves the mouse to given element
	 *
	 * @param element on which to hover
	 */
	public static void hover(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	/**
	 * @param
	 * @return Method to close specific window
	 * 
	 */
	public void closeSpecificWindow(String windowTitle) {
		Set<String> windows = driver.getWindowHandles();
		String mainwindow = driver.getWindowHandle();
		for (String handle : windows) {
			driver.switchTo().window(handle);
			System.out.println("switched to " + driver.getTitle() + "  Window");
			String pagetitle = driver.getTitle();
			if (pagetitle.equalsIgnoreCase(windowTitle)) {
				driver.close();
				System.out.println("Closed the  '" + pagetitle + "' Tab now ...");
			}
		}
		driver.switchTo().window(mainwindow);
	}

	/**
	 * @param entered        values
	 * @param expectedObject storing values in string array
	 */
	public static List<String> gettingObjectsInStringArray(String[] entered_values, List<String> expectedObject) {

		String[] lengths = entered_values;
		for (int i = 0; i < lengths.length; i++) {
			expectedObject.add(lengths[i]);
		}
		return expectedObject;
	}

	/**
	 * @param actual_ui_result
	 * @param actualObject
	 * @return getting list of ui text of elememts
	 */
	public static List<String> verifyUIElement(List<WebElement> actual_ui_result, List<String> actualObject) {
		for (WebElement customerName : actual_ui_result) {
			String actual = customerName.getText().toUpperCase();
			actualObject.addAll(Arrays.asList(actual));
		}
		return actualObject;
	}

	/**
	 * return a match string from list and click
	 * @param list of web elements
	 */
	public static void matchStringFromListAndClick(By by, String value) {

		List<WebElement> columVal = driver.findElements(by);

		System.out.println("Size of the contents in the column state is : " + columVal.size());

		for (int i = 0; i < columVal.size(); i++) {
			System.out.println("Content text is : " + columVal.get(i).getText());
			// match the content here in the if loop
			if (columVal.get(i).getText().equals(value)) {
				// perform action
				columVal.get(i).click();
			}
		}
	}

	/**
	 * return a list and lick elements text
	 * 
	 * @param list of web elements
	 * @return
	 */
	public static List<String> getElementsText(List<WebElement> list) {
		List<String> elemTexts = new ArrayList<String>();
		for (WebElement el : list) {
			if (!el.getText().isEmpty()) {
				elemTexts.add(el.getText());
			}
		}
		return elemTexts;
	}
	
	/**
	 * return a list of elements of sum
	 * @param list of web elements
	 * @return
	 */
	public static double getSumOfDoubleDigits(List<WebElement> listOfTrialMonesy) {
		 double sum = 0.0;

		 for (WebElement el : listOfTrialMonesy) {
				
				 System.out.println(el.getText());
		            String s = el.getText();
		            s = s.substring(1,s.length());
		            System.out.println(s);
		            sum = sum + Double.parseDouble(s);
		            System.out.println(sum);
				
		}
		return sum;
		
	}
	
	public static Boolean verifyTitle(String pageTitles) {
		try {
			Assert.assertEquals(driver.getTitle(), pageTitles);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			assert false;
			return false;
		}
	}

	/**
	 * Extracts text from list of elements matching the provided locator into new
	 * List<String>
	 * 
	 * @param locator
	 * @return list of strings
	 */
	public static List<String> getElementsText(By locator) {
		List<WebElement> elems = driver.findElements(locator);
		List<String> elemTexts = new ArrayList<String>();
		for (WebElement el : elems) {
			if (!el.getText().isEmpty()) {
				elemTexts.add(el.getText());
			}
		}
		return elemTexts;
	}

	/**
	 * Verifies whether the element matching the provided locator is displayed on
	 * 
	 * @param by
	 * @throws AssertionError if the element matching the provided locator is not
	 *                        found or not displayed
	 */
	public static void verifyElementDisplayed(By by) {
		try {
			assertTrue("Element not visible: " + by, driver.findElement(by).isDisplayed());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail("Element not found: " + by);
		}
	}

	/**
	 * Verifies whether the element matching the provided locator is NOT displayed
	 * 
	 * @param by
	 * @throws AssertionError the element matching the provided locator is displayed
	 */
	public static void verifyElementNotDisplayed(By by) {
		try {
			Assert.assertFalse(driver.findElement(by).isDisplayed(), "Element should not be visible: " + by);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param actualObject
	 * @param expectedObject verifying two array list
	 */
	public static void assertListOfString(List<String> actualObject, List<String> expectedObject) {
		try {
			Collections.sort(actualObject);
			Collections.sort(expectedObject);
			if (actualObject.equals(expectedObject)) {
				assert (true);
				System.out.println("Expected " + expectedObject + " and actual " + actualObject + " matched");
			} else {
				System.out.println("Expected " + expectedObject + " and actual " + actualObject + " did not match");
				assert (false);
			}
		} catch (Exception e) {
			System.out.println(e);
			assert (false);
		}
	}

	/**
	 * @param by
	 * @return Method to find presence of element
	 */
	public static boolean checkElementPresence(By by) {
		try {
			if (driver.findElements(by).size() != 0) {
				logger.info("The element is present " + by.toString());
				return true;
			} else {
				logger.info("The element not present " + by.toString());
			}

		} catch (Exception e) {
			logger.error(e);
			Assert.fail();
		}
		return false;
	}

	/**
	 * @param by
	 * @return Method to element is enabled
	 */
	public static boolean checkElementIsEnabled(By by) {
		try {
			if (driver.findElement(by).isEnabled()) {
				logger.info("The element is present " + by.toString());
				return true;
			} else {
				logger.info("The element not present " + by.toString());
			}

		} catch (Exception e) {
			logger.error(e);
			Assert.fail();
		}
		return false;
	}

	/**
	 * @param second
	 * @throws Exception hardStop
	 */
	public static void focusStop(long second) throws Exception {
		try {
			Thread.sleep(second);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Waits for element to be not stale
	 *
	 * @param element
	 */
	public static void waitForStaleElement(WebElement element) {
		int y = 0;
		while (y <= 15) {
			if (y == 1)
				try {
					element.isDisplayed();
					break;
				} catch (StaleElementReferenceException st) {
					y++;
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} catch (WebDriverException we) {
					y++;
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		}
	}

	/**
	 * @param by
	 * @return Verifying element display
	 */
	public static boolean checkElementdisplayed(By by) {
		try {
			if (driver.findElement(by).isDisplayed()) {
				logger.info("The element is displayed " + by.toString());
				return true;
			} else {
				logger.info("The element is not displayed " + by.toString());
			}
		} catch (Exception e) {
			logger.error(e);
			Assert.fail();
		}
		return false;
	}

	/**
	 * @return Method to check if alert message is displayed when launching th
	 *         ebrowser
	 */
	public static boolean checkPresenceOfAlert() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	/**
	 * Method to wait for the page load (explicit wait, the Page time out is
	 * provided in the config.properties file)
	 */
	public static void waitForPageLoad() {
		try {
			int pageTimeOutSeconds = Integer.parseInt(getProps().getProperty("pageTimeOut")); // Wait time flows from
			// config.properties
			driver.manage().timeouts().pageLoadTimeout(pageTimeOutSeconds, TimeUnit.SECONDS);

			logger.info("Page loaded successfully " + driver.getTitle());
		} catch (Exception e) {
			logger.error("Error inside Wait For Page Upload is : " + e);
			assert (false);
		}
	}

	/**
	 * Method to enter data in text field incase ajax actions are required
	 * 
	 */
	public void waitFindAKeyBoardEnter(By by, String text) {
		Actions mouse = new Actions(driver);
		WebElement element = driver.findElement(by);
		mouse.moveToElement(element).click().sendKeys(text).build().perform();
	}

	/**
	 * @param by
	 * @param text Method to wait and enter text in a text field
	 */
	public static void waitFindEnterText(By by, String text) {
		try {
			waitForPresenceOfElement(by);
			visibilityOfElementLocated(by);
			driver.findElement(by).clear();
			visibilityOfElementLocated(by);
			driver.findElement(by).sendKeys(text);
			logger.info("Object found success " + by.toString() + " and entered the value " + text);
			waitForPageLoad();
		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * @param by
	 * @param object Method to wait and enter text in a text field
	 */
	public static void waitFindEnterTextAsList(By by, String value) {
		try {
			waitForPresenceOfElement(by);
			visibilityOfElementLocated(by);
			Thread.sleep(2000);
			visibilityOfElementLocated(by);
			driver.findElement(by).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), value);
			logger.info("Object found success " + by.toString() + " and entered the value " + value);
			waitForPageLoad();
		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * @param by
	 * @param text Method to upload photos without click
	 */
	public static void waitFindUploadPhotoWithoutClick(By by, String text) {
		try {
			waitForPresenceOfElement(by);
			// driver.findElement(by).clear();
			driver.findElement(by).sendKeys(text);
			logger.info("Object found success " + by.toString() + " and entered the value " + text);
			waitForPageLoad();
		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * @param scroll up
	 * @return
	 */
	public static void scrollUpPageUsingJavaScript() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2250)");

	}

	/**
	 * @param scroll up
	 * @return
	 */
	public static void scrollPageUpUsingActions(By element) {

		Actions action = new Actions(driver);
		WebElement el = driver.findElement(element);
		action.moveToElement(el).sendKeys(Keys.PAGE_UP).perform();
	}

	/**
	 * @param scroll up
	 * @return
	 */
	public static void actionsMouseClick(By element) {

		Actions action = new Actions(driver);
		WebElement el = driver.findElement(element);
		action.click(el).build().perform();
	}

	/**
	 * @param scroll up
	 * @return
	 */
	public static void controlClickUsingActionsXYOffset(By element) {
		Actions builder = new Actions(driver);
		WebElement elOne = driver.findElement(element);

		int xOffset = elOne.getLocation().getX();
		int yOffset = elOne.getLocation().getY();

		builder.moveToElement(elOne, xOffset, yOffset).click().perform();

	}

	/**
	 * @param
	 * @return
	 * @throws Exception
	 */
	public static void actionsSeriesMouseClick(By elementOne, String value, By elementTwo) throws Exception {

		Actions builder = new Actions(driver);

		WebElement elOne = driver.findElement(elementOne);
		WebElement elTwo = driver.findElement(elementTwo);

		builder.moveToElement(elOne).build().perform();
		builder.moveToElement(elTwo).click().perform();
		focusStop(3000);

		Action seriesOfActions;
		seriesOfActions = builder.sendKeys(elOne, value).click().clickAndHold().build();
		seriesOfActions.perform();
	}

	/**
	 * @param
	 * @return
	 * @throws Exception
	 */
	public static void actionsSerieMouseClick(By elementOne, By elementTwo) throws Exception {

		Actions builder = new Actions(driver);
		WebElement elOne = driver.findElement(elementOne);
		WebElement elTwo = driver.findElement(elementOne);
		builder.moveToElement(elOne).click().perform();
		focusStop(3000);
		Action seriesOfActions;
		seriesOfActions = builder.keyDown(elOne, Keys.SHIFT).build();
		seriesOfActions.perform();
	}

	/**
	 * @param
	 * @return
	 */
	public static void actionsMouseDoubleClick(By element) {

		Actions action = new Actions(driver);
		WebElement el = driver.findElement(element);
		action.doubleClick(el).build().perform();
	}

	/**
	 * @param
	 * @return
	 */
	public static void actionsMouseClickAndHold(By element) {

		Actions action = new Actions(driver);
		WebElement el = driver.findElement(element);
		action.clickAndHold(el).build().perform();
	}

	/**
	 * @param scroll down
	 * @return
	 */
	public static void scrollPageDownUsingActions(By element) {

		Actions action = new Actions(driver);
		WebElement el = driver.findElement(element);
		action.moveToElement(el).sendKeys(Keys.PAGE_DOWN).perform();
	}

	/**
	 * @param scroll Down
	 * @return
	 */
	public static void scrollDownPageUsingJavaScript() {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-250)");

	}

	/**
	 * @return Method to generate unique numbers
	 */
	public static String generateUniqueNumbers() {
		Faker faker = new Faker();
		String regex = "^(?!000|666)[0-8][0-9]{2}(?!00)[0-9]{2}(?!0000)[0-9]{4}$";
		Pattern pattern = Pattern.compile(regex);
		String unique_number = "";
		while (unique_number == null || unique_number.isEmpty()) {
			String temp = faker.number().digits(10);
			Matcher matcher = pattern.matcher(temp);
			if (matcher.matches()) {
				unique_number = temp;
			}
		}
		return unique_number;
	}

	/**
	 * @param Method to drag and drop
	 */
	public static void dragAndDrop(By from, By to, int offset) {
		try {
			// Element which needs to drag.
			WebElement From = driver.findElement(from);

			// Element on which need to drop.
			WebElement To = driver.findElement(to);

			// Using Action class for drag and drop.
			Actions act = new Actions(driver);

			// Dragged and dropped.
			act.dragAndDropBy(From, offset, 100).build().perform();

		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * @param Method to drag and drop
	 */
	public static void dragAndDropUsingJavaScriptExecutor(By from) {
		try {

			// WebElement LocatorFrom = driver.findElement(from);

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			Object LocatorFrom = executor.executeScript(
					"document.querySelector(\"#editor > div > div > div > div > div > div > div.sc-jLiVlK.eACBbd.blockbuilder-preferences.right > div > div > div > div.tab-content > div.tab-pane.active > div > div:nth-child(2) > div > div.blockbuilder-content-tool-name\")");
			Object LocatorTo = executor.executeScript("document.getElementById(\"u_body\")");

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("function createEvent(typeOfEvent) {\n"
					+ "var event =document.createEvent(\"CustomEvent\");\n"
					+ "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n"
					+ "data: {},\n" + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
					+ "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
					+ "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
					+ "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
					+ "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
					+ "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
					+ "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
					+ "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
					+ "var dropEvent = createEvent('drop');\n"
					+ "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
					+ "var dragEndEvent = createEvent('dragend');\n"
					+ "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
					+ "var source = arguments[0];\n" + "var destination = arguments[1];\n"
					+ "simulateHTML5DragAndDrop(source,destination);", LocatorFrom, LocatorTo);

		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * @param Method to drag and drop
	 */
	public static void dragAndDropUsingRobot(By from, By to) {
		try {

			// Element which needs to drag.
			WebElement From = driver.findElement(from);

			// Element on which need to drop.
			WebElement To = driver.findElement(to);

			Point coordinates1 = From.getLocation();
			Point coordinates2 = To.getLocation();

			System.out.println("xxxxxxxxx" + coordinates1);
			System.out.println("yyyyyyyyy" + coordinates2);

			Robot robot = new Robot();
			robot.mouseMove(coordinates1.getX(), coordinates1.getY());
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseMove(coordinates2.getX(), coordinates2.getY());
			robot.mouseRelease(InputEvent.BUTTON1_MASK);

		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * @param Method to drag and drop
	 */
	public static void dragAndDropClickHoldRelease(By from, By to) {
		try {
			// Element which needs to drag.
			WebElement From = driver.findElement(from);

			// Element on which need to drop.
			WebElement To = driver.findElement(to);

			// Using Action class for drag and drop.
			Actions builder = new Actions(driver);

			Action dragAndDrop = builder.clickAndHold(From).moveToElement(From).release(To).build();
			dragAndDrop.perform();
		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * @param Method to drag and drop
	 */
	public static void dragAndDropXandYaxis(By from, By to) {
		try {
			// Actions class method to drag and drop
			Actions builder = new Actions(driver);

			WebElement From = driver.findElement(from);

			WebElement To = driver.findElement(to);

			// Here, getting x and y offset to drop source object on target object location
			// First, get x and y offset for from object
			int xOffset1 = From.getLocation().getX();

			int yOffset1 = From.getLocation().getY();

			System.out.println("xOffset1--->" + xOffset1 + " yOffset1--->" + yOffset1);

			// Secondly, get x and y offset for to object
			int xOffset = To.getLocation().getX();

			int yOffset = To.getLocation().getY();

			System.out.println("xOffset--->" + xOffset + " yOffset--->" + yOffset);

			// Find the xOffset and yOffset difference to find x and y offset needed in
			// which from object required to dragged and dropped
			xOffset = (xOffset - xOffset1) + 10;
			yOffset = (yOffset - yOffset1) + 20;

			// Perform dragAndDropBy
			builder.dragAndDropBy(From, xOffset, yOffset).perform();

		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * @param Method to select an option from drop down
	 */
	public static void waitFindSelect(By by, String option) {
		try {

			Select dropdown = new Select(driver.findElement(by));
			dropdown.selectByVisibleText(option);
			logger.info("Object selected " + option + " successfully" + by.toString());

		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * @param promo_weekend_checkbox selected element get unselected
	 * @return
	 */
	public static void waitFindClickMultipleCheckBox(List<WebElement> checkbox) {
		try {
			for (int i = 0; i < checkbox.size(); i++) {
				Thread.sleep(3000);

				if (checkbox.get(i).isSelected() == false) {
					waitForVisibilityOfAllElements(checkbox);
					Thread.sleep(2000);
					(checkbox).get(i).click();
					waitForPageLoad();
					logger.info("Checkbox Object clicked successfully" + checkbox.toString());
				}
			}
			waitForPageLoad();
			logger.info("Checkbox already selected" + checkbox.toString());
		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * @param promo_weekend_checkbox selected element get unselected
	 * @return
	 */
	public static void waitFindClickMultipleCheckBoxUsingJavaScript(List<WebElement> by) {
		try {
			Thread.sleep(2000);
			// List<WebElement> list = driver.findElements(by);
			for (WebElement cb : by) {
				Thread.sleep(1000);
				// if (!cb.isSelected()) {
				// javaScriptScrollUsingBy(by);
				cb.click();
				break;
				// } else {
				// System.out.println("Check box selected");
				// }

			}
			waitForPageLoad();
		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}
	
	/**
	 * @param click On Name From List
	 */
	public synchronized void clickOnNameFromList(By by, String text) throws Exception {
		// By.cssSelector("div.xS > div > div.y6 > span"));
		List<WebElement> list = driver.findElements(by);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getText());
			Thread.sleep(1000);
			if (list.get(i).getText().equals(text) == true) {
				list.get(i).click();
				System.out.println("Value selected from list : " + list.get(i).getText());
				break;
			}
		}
	}

	/**
	 * @param click check box
	 */
	public static void javaScriptCheckBox(By by) {
		try {
			waitForPresenceOfElement(by);
			javaScriptScrollUsingBy(by);
			if (!driver.findElement(by).isSelected() && driver.findElement(by).isEnabled()) {
				WebElement element = driver.findElement(by);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				logger.info("The object " + by.toString() + " is clicked successfully using javascript");
			} else if (!driver.findElement(by).isEnabled()) {
				logger.info("The object " + by.toString() + " is not enabled");
			} else {
				logger.info("The object " + by.toString() + " is already checked");
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * @param by wait and click selected check box
	 */
	public static void waitFindClickCheckBox(By by) {
		try {
			waitForPresenceOfElement(by);
			if (driver.findElement(by).isSelected() == true) {
				javaScriptScrollUsingBy(by);
				driver.findElement(by).click();
				waitForPageLoad();
				logger.info("Checkbox Object clicked successfully" + by.toString());
			}
			waitForPageLoad();
			logger.info("Object clicked successfully" + by.toString());
		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * @param click check box
	 * @param
	 */
	public void clickCheckbox(By by) {
		try {
			waitForPresenceOfElement(by);
			if (!driver.findElement(by).isSelected()) {
				driver.findElement(by).click();
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * @param Method          to wait for dynamic load.
	 * @param objectClassName Expected class attribute value which changes instantly
	 */
	public static void waitForDynamicLoad(By by, String objectClassName) {
		try {
			int MaxTimeWait = Integer.parseInt(getProps().getProperty("pageTimeOut"));
			Instant current;
			int _pollTimer = 2000;
			Instant start = Instant.now();
			Thread.sleep(_pollTimer);
			logger.info("checking if page/data is being refreshed.....");
			WebElement object = driver.findElement(by);
			String objectClass = object.getAttribute("class");
			while (!objectClassName.equalsIgnoreCase(objectClass)) {
				Thread.sleep(_pollTimer);
				object = driver.findElement(by);
				objectClass = object.getAttribute("class");
				logger.info("waiting for obj " + by.toString());
				current = Instant.now();
				if (Duration.between(start, current).toMillis() >= MaxTimeWait * 1000) {
					logger.info("Wait Logo Timeout after " + MaxTimeWait + " seconds");
					throw new InterruptedException(
							"Waited more than " + MaxTimeWait + " seconds and still Data Loading on Page");
				}
				break;
			}
			logger.info(".....page/data refresh is now complete");
			Thread.sleep(_pollTimer);
		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * @param expectedObject
	 * @param actualObject
	 * @return Method to compare 2 strings
	 */
	public static boolean assertString(String expectedObject, String actualObject) {
		try {
			Assert.assertEquals(expectedObject, actualObject);
			logger.info("Expected " + expectedObject + " and actual " + actualObject + " matched");
			return (true);
		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
		return false;
	}
	
	/**
	 * @param expectedObject
	 * @param actualObject
	 * @return Method to compare 2 strings
	 */
	public static boolean assertDouble(double expectedObject, double actualObject) {
		try {
			Assert.assertEquals(expectedObject, actualObject);
			logger.info("Expected " + expectedObject + " and actual " + actualObject + " matched");
			return (true);
		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
		return false;
	}

	/**
	 * @param Method to remove special characters
	 * @return And convert into Double
	 * @throws AWTException
	 */
	public static double removeSpecialCharAndGetValueInDouble(String getText, int x ) {
		String str = getText;
		String specialCharacter = str.replaceAll("[^a-zA-Z0-9]", " ").trim();
		String removeValue = specialCharacter.substring(x);
		double getValueInDouble = Double.parseDouble(removeValue);
		
		return getValueInDouble;
	}
	
	/**
	 * @param Remove first value
	 * @return And convert into Double
	 * @throws AWTException
	 */
	public static double removeFirstValuesAndGetValueInDouble(String getText, int x ) {
		String str = getText;
		String removeValue = str.substring(x);
		double getValueInDouble = Double.parseDouble(removeValue);
		
		return getValueInDouble;
	}

	/**
	 * @param date format
	 * @return Method to wait for an if the element is invisible or not
	 * 
	 */
	public static String dateFormat() {

		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
		String stringDate = DateFor.format(date);
		DateFor = new SimpleDateFormat("dd MMM  yyyy");
		stringDate = DateFor.format(date);
		System.out.println("Date Format dd MMM yyyy :" + stringDate);

		return stringDate;
	}

	/**
	 * @param by
	 * @return Method to get the text of the object from application
	 */
	public static String textFromApplication(By by) {
		boolean result = false;
		int attempts = 0;
		String text = null;
		while (attempts < 4) {
			try {
				waitForPresenceOfElement(by);
				text = driver.findElement(by).getText().trim();
				logger.info("The text from the object is " + text);
				result = true;
				// break;
			} catch (Exception e) {
				logger.error(e);
			}
			attempts++;
		}
		return text;
	}

	/**
	 * @param by
	 * @return Method to get the text from field
	 */
	public static String textFromField(By by) {
		boolean result = false;
		int attempts = 0;
		String objectValue = null;
		while (attempts < 4) {
			try {
				waitForPresenceOfElement(by);
				WebElement object = driver.findElement(by);
				objectValue = object.getAttribute("value");
				logger.info("The text from the object is " + objectValue);
				result = true;
				// break;
			} catch (Exception e) {
				logger.error(e);
			}
			attempts++;
		}
		return objectValue;
	}

	/**
	 * @param by Method to Click using javascript
	 */
	public static void javaScriptClickUsingBy(By by) {
		try {
			waitForPresenceOfElement(by);
			WebElement element = driver.findElement(by);
			if (element.isEnabled()) {
				javaScriptScrollUsingBy(by);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				logger.info("The object " + by.toString() + " is clicked successfully using javascript");
			} else {
				logger.info("The object " + by.toString() + " is is not enabled to be clicked using javascript");
			}

		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * @param by Method to scroll to particular element
	 */
	public static void javaScriptScrollUsingBy(By by) {
		try {
			waitForPresenceOfElement(by);
			WebElement element = driver.findElement(by);
			if (checkElementdisplayed(by)) {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].scrollIntoView();", element);
				logger.info("The object " + by.toString() + " is clicked successfully using javascript");
			} else {
				logger.info("The object " + by.toString() + " is is not enabled to be clicked using javascript");
			}

		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * @param frame Method to find Elements inside the frame
	 */
	public static void moveInSideToFrameWithTitle(By frame) {
		try {
			driver.switchTo().frame(driver.findElement(frame));
			logger.info("move inside the frame");
		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * @param frame Method to find Elements inside the frame
	 */
	public static void moveInSideToFrame(String frame) {
		try {
			driver.switchTo().frame(frame);
			logger.info("move inside the frame");
		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * Method to move out of frame
	 */
	public static void moveOutSideFromiframe() {
		try {
			driver.switchTo().defaultContent();
			logger.info("out frame");
		} catch (Exception e) {
			logger.error(e);
			assert (false);
		}
	}

	/**
	 * @param first_frame
	 * @return Method to wait for an frame and switch
	 * 
	 */
	public static boolean iframeToBeAvailableAndSwitchToIt(By frame) {
		try {
			int objectTimeOutSeconds = Integer.parseInt(getProps().getProperty("objectTimeout"));

			WebDriverWait waitDriver = new WebDriverWait(driver, objectTimeOutSeconds);
			waitDriver.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
			logger.info("Waited success for " + frame.toString());
			return true;
		} catch (WebDriverException e) {
			logger.error(e);
			Assert.fail();
			assert (false);
		}
		return false;
	}

	/**
	 * @param url Verifying url
	 */
	public static void verifyingLoadApplicationPageUrl(String url) {
		try {
			waitForPageLoad();
			// focusStop(3);
			Assert.assertEquals(url, driver.getCurrentUrl());
		} catch (Exception e) {
			logger.error(e);
		}

	}

}
