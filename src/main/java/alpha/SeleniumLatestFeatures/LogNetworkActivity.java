package alpha.SeleniumLatestFeatures;

import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.network.Network;
import org.openqa.selenium.devtools.v114.network.model.Request;
import org.openqa.selenium.devtools.v114.network.model.Response;

public class LogNetworkActivity {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		
		//Network.requestWillBeSent event triggers when HTTP request is generated
		devTools.addListener(Network.requestWillBeSent(), request ->
		{
			Request req = request.getRequest();
			//System.out.println("Request: "+ req.getUrl());
			//System.out.println(req.getHeaders());
		});
		
		//Network.reponseReceived event triggers when HTTP response is available 
		//then we have to get the preview the response on selenium by getting a response object res
		//We add listener to catch the event call
		devTools.addListener(Network.responseReceived(), response ->
		{
			Response res= response.getResponse();
			//System.out.println("Response: "+ res.getUrl());
			//System.out.println(res.getStatus());
			if (res.getStatus().toString().startsWith("4"))
			{
				System.out.println("Response: "+ res.getUrl()+" is failing with status code: "+res.getStatus());	
			}
		});
		driver.get("https://www.amazon.com/");
	}

}
