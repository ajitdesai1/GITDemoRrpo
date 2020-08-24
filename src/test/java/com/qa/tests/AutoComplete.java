package com.qa.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoComplete {
	public static WebDriver driver;

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "D:\\EclipseInstallFolder\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		driver.findElement(By.name("q")).sendKeys("selenium");
		//		List <WebElement> listItems = driver.findElements(By.xpath("//div[@class='aajZCb']"));
		//		int si = listItems.size();
		//		System.out.println(si);
		//		listItems.get(2).click();

//		WebDriverWait wait = new WebDriverWait(driver,30);
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@class='aajZCb']")));

		List<WebElement> list = driver.findElements(By.xpath("//input[@name='']"));
		System.out.println("Auto Suggest List ::" + list.size());

		for(int i = 0 ;i< list.size();i++)
		{
			System.out.println(list.get(i).getText());

			if(list.get(i).getText().equals("selenium tutorial"))
			{
				System.out.println("AD");
				list.get(i).click();
				break;
			}
		}

	}

}
