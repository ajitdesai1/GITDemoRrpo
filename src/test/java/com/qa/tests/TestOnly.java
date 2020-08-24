package com.qa.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestOnly {
	public static WebDriver driver;
	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "D:\\EclipseInstallFolder\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://classic.crmpro.com/");

		login();
	}
	static public void login() {
		String title = driver.getTitle();
		System.out.println(title);
		if (title.equalsIgnoreCase("Privacy error")) {
			driver.findElement(By.id("details-button")).click();
			driver.findElement(By.id("proceed-link")).click();
		}
		driver.findElement(By.name("username")).sendKeys("batchautomation");
		driver.findElement(By.name("password")).sendKeys("Test@12345");
		driver.findElement(By.xpath("//input[@type='submit']")).submit();
		driver.switchTo().frame("mainpanel");
		String str = driver.getTitle();
		System.out.println(str);
		driver.findElement(By.linkText("Logout"));	
		}

	}
