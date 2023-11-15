package alpha.SeleniumLatestFeatures;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.fetch.Fetch;

public class NetworkMocking {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		//Enable to fetch the network calls using Fetch.enable
		devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
		
		//Network.requestPaused event triggers when HTTP request is generated
		devTools.addListener(Fetch.requestPaused(), request ->
		{
			if (request.getRequest().getUrl().contains("shetty"))
			{
				//Here the sending request is paused we are making changes in the url
				String newUrl = request.getRequest().getUrl().replace("=shetty", "=BadGuy");
				System.out.println(newUrl);
				//Pushing changed url in the HTTP request and continue the paused request
				devTools.send(Fetch.continueRequest(request.getRequestId(),Optional.of(newUrl), Optional.of(request.getRequest().getMethod()),
						Optional.empty(), Optional.empty(), Optional.empty()));
			}
			else {
				//If required string is not found we are resuming the paused HTTP requests
				devTools.send(Fetch.continueRequest(request.getRequestId(),Optional.of(request.getRequest().getUrl()), Optional.of(request.getRequest().getMethod()),
						Optional.empty(), Optional.empty(), Optional.empty()));
			}
		});
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.xpath("//button[@routerlink='/library']")).click();
		Thread.sleep(3000);
		System.out.println(driver.findElement(By.xpath("//p")).getText());
	}

}
