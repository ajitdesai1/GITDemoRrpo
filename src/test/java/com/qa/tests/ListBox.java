package com.qa.tests;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListBox {
	static WebDriver driver;
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "D:\\EclipseInstallFolder\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demoqa.com");
		

		formsPracticeForm();
		elementsWebTables();
		keyboardEvent();
		mouseEvents();
		textLinks();
		widgetsTabs();
		widgetMenu();
		alerts();
	}
	private static void alerts() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='header-text' and text()='Alerts, Frame & Windows']//following-sibling::div[@class='header-right']")).click();
		driver.findElement(By.xpath("//div[@class='header-text' and text()='Alerts, Frame & Windows']//../..//following-sibling::div[@class='element-list collapse show']/ul/li[@id='item-0']")).click();
		//NEW TAB
		driver.findElement(By.id("tabButton")).click();
		//get window handlers as list
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		//switch to new tab
		driver.switchTo().window(browserTabs .get(1));
		driver.get("https://www.google.com");
		String gTitle = driver.getTitle();
		Thread.sleep(2000);
		//check is it correct page opened or not (e.g. check page's title)
		if (gTitle.equalsIgnoreCase("Google")) {
			System.out.println("new tab title has matched");
		}else {
			System.out.println("new tab title has not matched");
		}
		//then close tab and get back
		driver.close();
		driver.switchTo().window(browserTabs.get(0));
		
		//NEW WINDOW
		driver.findElement(By.id("windowButton")).click();
		List<String> win= new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(win.get(1)).getTitle();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		String s = driver.getTitle();
		System.out.println("you have opened a "+s+" on new window...thank you!");
		driver.close();
		driver.switchTo().window(win.get(0));
		
		//ALERTS
		driver.findElement(By.xpath("//div[@class='header-text' and text()='Alerts, Frame & Windows']//../..//following-sibling::div[@class='element-list collapse show']/ul/li[@id='item-1']")).click();
		driver.findElement(By.id("alertButton")).click();
		Alert alert = driver.switchTo().alert();		
		
        // Capturing alert message.    
        String alertMessage= driver.switchTo().alert().getText();		
        System.out.println(alertMessage);	
        Thread.sleep(2000);
   		// Accepting alert		
        alert.accept();		//use alert.dismiss() to click on Cancel button
        
        driver.findElement(By.id("timerAlertButton")).click();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.alertIsPresent());

            Alert alert1 = driver.switchTo().alert();
            System.out.println("Alert box text " + alert1.getText());
            alert1.accept();


        
		
		}
	private static void formsPracticeForm() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='category-cards']//div[@class='card mt-4 top-card']//div//div[@class='card-body']//h5[text()='Forms']")).click();
		driver.findElement(By.xpath("//div[@class='element-list collapse show']//child::*//li[@id='item-0']//span[text()='Practice Form']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("firstName")).sendKeys("FName");
		driver.findElement(By.id("lastName")).sendKeys("LName");
		driver.findElement(By.id("userEmail")).sendKeys("werwe3423424@werwersd34r23.com");
		//****this was a radio button but wasn't able to recognize, hence has to follow this approach
		WebElement element = driver.findElement(By.id("gender-radio-1"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		driver.findElement(By.id("userNumber")).sendKeys("7041234567");
		String pNumber = driver.findElement(By.id("userNumber")).getAttribute("value");
		System.out.println(pNumber);
		driver.findElement(By.id("dateOfBirthInput")).click();
		//WebElement month = driver.findElement(By.xpath("\\div[@class='react-datepicker__month-container']\\div[@class='react-datepicker__header']\\div[@class='react-datepicker__header__dropdown react-datepicker__header__dropdown--select']\\div[@class='react-datepicker__month-dropdown-container react-datepicker__month-dropdown-container--select']\\div[@class='react-datepicker__month-select']"));
		//		WebElement month = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[1]/form/div[5]/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select"));
		//		Select monthCombo = new Select(month);
		//monthCombo.selectByVisibleText("March");
		//		monthCombo.selectByIndex(5);
		Select month = new Select(driver.findElement(By.xpath("//*[@id='dateOfBirth']//div[@class='react-datepicker__month-container']//div[@class='react-datepicker__header']//div[@class='react-datepicker__month-dropdown-container react-datepicker__month-dropdown-container--select']//select[@class='react-datepicker__month-select']")));
		month.selectByIndex(5);

		WebElement year = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[1]/form/div[5]/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select"));
		Select yearCombo = new Select(year);
		yearCombo.selectByVisibleText("1999");

		WebElement date = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[1]/form/div[5]/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div[4]/div[3]"));
		date.click();

		WebElement chk1 = driver.findElement(By.id("hobbies-checkbox-1"));
		actions.moveToElement(chk1).click().build().perform();

		WebElement chk2 = driver.findElement(By.id("hobbies-checkbox-2"));
		actions.moveToElement(chk2).click().build().perform();
		
		driver.findElement(By.id("currentAddress")).sendKeys("1254 Park Rd");
	
	}
	private static void elementsWebTables() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='header-right']")).click();
		driver.findElement(By.xpath("//li[@id='item-3']")).click();
		driver.findElement(By.id("addNewRecordButton")).click();

		driver.findElement(By.id("firstName")).sendKeys("AF");
		driver.findElement(By.id("lastName")).sendKeys("AL");
		driver.findElement(By.id("userEmail")).sendKeys("adddeeresd@werdsff.com");
		driver.findElement(By.id("age")).sendKeys("25");
		driver.findElement(By.id("salary")).sendKeys("25000");
		driver.findElement(By.id("department")).sendKeys("Comp");
		driver.findElement(By.id("submit")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("searchBox")).sendKeys("AF");
		String name = driver.findElement(By.xpath("//div[@class='rt-td' and text()='AF']")).getText();
		if (name.equalsIgnoreCase("AF")){
			System.out.println("your search is found");
			//driver.findElement(By.id("delete-record-4")).click();
		}
		else {
			System.out.println("your search is not found");
		}
		Thread.sleep(1000);	
	}
	private static void keyboardEvent() throws InterruptedException {
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.id("searchBox"))).build().perform();
		builder.sendKeys(Keys.BACK_SPACE).build().perform();
		Thread.sleep(2000);
		String name1 = driver.findElement(By.xpath("//div[@class='rt-td' and text()='AF']")).getText();
		System.out.println(name1);
		if (name1.equalsIgnoreCase("AF")){
			driver.findElement(By.id("delete-record-4")).click();
			System.out.println("your record is deleted");
		}
		else {
			System.out.println("your record is not deleted");
		}
		driver.findElement(By.xpath("//li[@id='item-4']")).click();
	}
	private static void mouseEvents() {
		//mouse double click event
		Actions ac2 = new Actions(driver);
		WebElement we2 = driver.findElement(By.xpath("//button[@id='doubleClickBtn']")); //.build().perform();
		ac2.doubleClick(we2).perform();
		String dClick = driver.findElement(By.id("doubleClickMessage")).getText();
		if (dClick.equalsIgnoreCase("You have done a double click")) {
			System.out.println("Double click message matched: "+dClick);
		}
		//mouse right click event
		WebElement we3 = driver.findElement(By.xpath("//button[@id='rightClickBtn']"));
		ac2.contextClick(we3).perform();
		//mouse left click event
		WebElement we4 = driver.findElement(By.xpath("//button[text() = 'Click Me']"));
		ac2.click(we4).perform();
	}
	private static void textLinks() {
		driver.findElement(By.xpath("//li[@id='item-5']")).click();
		driver.findElement(By.id("created")).click();
		String linkRes = driver.findElement(By.id("linkResponse")).getText();
		if(linkRes.equalsIgnoreCase("Link has responded with staus 201 and status text Created")) {
			System.out.println("Link response has been matched");
		}
	}
	private static void widgetsTabs() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='header-right']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='header-text' and text()='Widgets']//following-sibling::div[@class='header-right']")).click();
		Thread.sleep(1000);
		//the following two lines are used to scroll down the page by 1000 pixels
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='header-text' and text()='Widgets']//../..//following-sibling::div[@class='element-list collapse show']/ul/li[@id='item-5']")).click();
		driver.findElement(By.xpath("//nav[@class='nav nav-tabs']//a[@id='demo-tab-origin']")).click();
		System.out.println("origin tab is clicked");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//nav[@class='nav nav-tabs']//a[@id='demo-tab-what']")).click();
		System.out.println("what tab is clicked");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//nav[@class='nav nav-tabs']//a[@id='demo-tab-use']")).click();
		System.out.println("use tab is clicked");
	}
	static public void widgetMenu() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='header-text' and text()='Widgets']//../..//following-sibling::div[@class='element-list collapse show']/ul/li[@id='item-7']")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(("Main Item 1"))).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(("Main Item 2"))).click();
		driver.findElement(By.xpath("//a[text()='Main Item 2']//following-sibling::ul//li/a[text()='SUB SUB LIST »']")).click();
		driver.findElement(By.xpath("//a[text()='Main Item 2']//following-sibling::ul//li/a[text()='SUB SUB LIST »']//following-sibling::ul//li/a[text()='Sub Sub Item 1']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='header-text' and text()='Widgets']//following-sibling::div[@class='header-right']")).click();
	}
}