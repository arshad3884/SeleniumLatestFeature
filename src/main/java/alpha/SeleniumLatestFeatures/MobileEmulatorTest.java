package alpha.SeleniumLatestFeatures;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class MobileEmulatorTest {

	public static void main(String[] args) {
		
		ChromeDriver driver = new ChromeDriver(); //Created a driver of chromeDriver class it inherits ChromeDriver class and can be run only in chromium browsers (Chrome, Edge) 
		DevTools devTools = driver.getDevTools(); //Created an object of DevTool 
		devTools.createSession(); //Must create a session to chromeDevtools using CDP
		// Now we can send commands to CDP (Chrome DevTools Protocol) using selenium by .send function
		// CDP will invoke and run commands on devTools https://chromedevtools.github.io/devtools-protocol
		//devTools.send(Emulation.setTimezoneOverride("Asia/Tokyo")); //use IANA Time Zone Database (TZDB) identifier list
		
		//Another way to execute CDP commands directly without using selenium function
		Map<String, Object> deviceMatrics = new HashMap<String, Object>();
		deviceMatrics.put("width", 390);
		deviceMatrics.put("height", 844);
		deviceMatrics.put("deviceScaleFactor", 1);
		deviceMatrics.put("mobile", true);
		
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMatrics);
		
		driver.get("https://rahulshettyacademy.com/");
		driver.findElement(By.xpath("(//button[@class='navbar-toggle'])[1]")).click();
		
	}

}
