package alpha.SeleniumLatestFeatures;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class SetGeoLocation {

	public static void main(String[] args) {
		
		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		//Running chrome developers option command directly using CDP
		
		//Creating hashMap to pass the parameters to CDP command.
		Map<String, Object> coordinates = new HashMap<String, Object>(); 
		//Madrid, Spain (40.420588869248306, -3.6974049152422848)
		coordinates.put("latitude", 40.420588869248306);
		coordinates.put("longitude", -3.6974049152422848);
		coordinates.put("accuracy", 1);
		driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
		
		//Alternate way to run CDP command using selenium send method directly
		//devTools.send(Emulation.setGeolocationOverride(40, 3, 1));
		
		driver.get("https://www.google.com");
		driver.findElement(By.name("q")).sendKeys("Netflix", Keys.ENTER);

	}

}
