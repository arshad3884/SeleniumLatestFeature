package alpha.SeleniumLatestFeatures;

import java.net.URI;
import java.util.function.Predicate;

import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicAuthentication {

	public static void main(String[] args) {
		
		ChromeDriver driver = new ChromeDriver();
		//We use predicate to handle basicAuthentication
		//Creating Predicate, consumer
		Predicate<URI> uriPredicate = uri -> uri.getHost().contains("httpbin.org");
		
		//Provide authentication info to driver by casting it
		((HasAuthentication)driver).register(uriPredicate, UsernameAndPassword.of("foo", "bar"));
		
		driver.get("http://httpbin.org/basic-auth/foo/bar");

	}

}
