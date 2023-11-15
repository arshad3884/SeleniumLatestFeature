package alpha.SeleniumLatestFeatures;

import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.network.Network;

import com.google.common.collect.ImmutableList;

public class BlockNetworkRequest {

	public static void main(String[] args) {
		
		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		//We are passing the the URL which need to be blocked in immutable List of String
		devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg","*.css")));
		
		long startTime = System.currentTimeMillis();
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
	}

}
