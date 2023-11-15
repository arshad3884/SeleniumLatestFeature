package alpha.SeleniumLatestFeatures;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class ConsoleLogCapture {

	public static void main(String[] args) {
		
		ChromeDriver driver = new ChromeDriver();
		//using TestNG Listeners - onTestFailure method to handle failed Logs
		//below error handling code should be written in onTestFailure method
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.xpath("//a[text()='Browse Products']")).click();
		driver.findElement(By.xpath("//a[text()='Selenium']")).click();
		driver.findElement(By.cssSelector(".add-to-cart")).click();
		driver.findElement(By.xpath("//a[text()='Cart']")).click();
		driver.findElement(By.xpath("//input[@id='exampleInputEmail1']")).clear();
		driver.findElement(By.xpath("//input[@id='exampleInputEmail1']")).sendKeys("2");
		
		//Read console error code
		LogEntries entry = driver.manage().logs().get(LogType.BROWSER); //Get log entry object
		List<LogEntry> logs = entry.getAll(); //getAll return all logs List
		for (LogEntry log: logs)
		{
			//Iterating and printing the logs 
			System.out.println(log.getMessage());
 		}
	}

}
