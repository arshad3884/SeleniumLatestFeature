package alpha.SeleniumLatestFeatures;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.fetch.Fetch;
import org.openqa.selenium.devtools.v114.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v114.network.model.ErrorReason;

public class NetworkFailedRequest {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		//Writing pattern for HTTP call
		//java.util.Optional<java.lang.String> urlPattern
		Optional<List<RequestPattern>> patterns = Optional.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"),Optional.empty(),Optional.empty()))); 
		//Enable to fetch the network calls using Fetch.enable
		devTools.send(Fetch.enable(patterns, Optional.empty()));
		// The above call only trace the HTTP calls of Requested pattern
		devTools.addListener(Fetch.requestPaused(), request ->
		{
			//On above created pattern we are tracking a request contain *GetBook* in URL and explicitly failing it
			devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
		});
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.xpath("//button[@routerlink='/library']")).click();

	}

}
