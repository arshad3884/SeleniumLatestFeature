package alpha.SeleniumLatestFeatures;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.emulation.Emulation;

public class SetSessionTimeZone {

	public static void main(String[] args) {
		
		ChromeDriver driver = new ChromeDriver();
		DevTools devTools= driver.getDevTools();
		devTools.createSession();
		
		devTools.send(Emulation.setTimezoneOverride("UTC"));
		
		/*Here are some common IANA Time Zone Database identifiers:

			Africa

			Africa/Cairo
			Africa/Johannesburg
			Africa/Lagos
			America

			America/New_York
			America/Los_Angeles
			America/Chicago
			Antarctica

			Antarctica/Casey
			Antarctica/Davis
			Antarctica/Palmer
			Asia

			Asia/Tokyo
			Asia/Dubai
			Asia/Kolkata
			Europe

			Europe/London
			Europe/Paris
			Europe/Berlin
			Pacific

			Pacific/Auckland
			Pacific/Honolulu
			Pacific/Guam
			Etc/GMT±X

			Etc/GMT-12 (GMT-12:00)
			Etc/GMT+0 (GMT+00:00)
			Etc/GMT+12 (GMT+12:00)
			UTC

			UTC
			Etc/UTC
			*/
		driver.get("https://rahulshettyacademy.com/");
		
	}

}
