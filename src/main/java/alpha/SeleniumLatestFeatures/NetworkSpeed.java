package alpha.SeleniumLatestFeatures;

import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.network.model.ConnectionType;
import org.openqa.selenium.devtools.v114.network.Network;

public class NetworkSpeed {

	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		//We can set the Offline status, latency, download throughput, upload throughput, connectionType using below command
		devTools.send(Network.emulateNetworkConditions(false, 3000, 20000, 10000, Optional.of(ConnectionType.ETHERNET)));
		
		/*
		//If we set offline status as true we can get the status of the request and print it
		devTools.send(Network.emulateNetworkConditions(true, 3000, 20000, 10000, Optional.of(ConnectionType.ETHERNET)));
		devTools.addListener(Network.loadingFailed(), loadingFailed->
		{
			System.out.println(loadingFailed.getErrorText());
			System.out.println(loadingFailed.getTimestamp());
		});
		*/
		
		long startTime = System.currentTimeMillis();
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
		driver.close();
	}

}
