package com.qa.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindAllLinks {
		private static final String Continue = null;
		static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		{
			System.setProperty("webdriver.chrome.driver", "D:\\EclipseInstallFolder\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("https://www.google.com/");
			
		findLinks();
		//findTextBoxes();
		}

	}
	private static void findTextBoxes() {
		// TODO Auto-generated method stub
		List<WebElement> links = driver.findElements(By.xpath("//input[@type='text']"));
		System.out.println(links.size());
		String password=driver.findElement(By.id("firstName")).getAttribute("placeholder");
		System.out.println(password);
		for (int i=0;i<links.size();i++) {
			System.out.println(links.get(i).getText());
			
		}
	}
	private static void findLinks() throws InterruptedException {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		for (int i=0;i<links.size();i++) {
			if (links.get(i).getText().isEmpty()) {
				continue;
			} 
			driver.findElement(By.xpath("//a[text()='"+links.get(i).getText()+"']")).click();
			Thread.sleep(1000);
			driver.navigate().back();
			Thread.sleep(1000);
			links=driver.findElements(By.tagName("a"));
		}
		
	}

}
