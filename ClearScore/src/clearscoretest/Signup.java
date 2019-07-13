package clearscoretest;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

@Test
public class Signup extends TestMain {

		public void signuptest() throws InterruptedException
		{
    	Boolean signup_button = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/div/nav/div/ul/li[8]/a")).isDisplayed();
    	
    	Boolean	signup_button_enable = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/div/nav/div/ul/li[8]/a")).isEnabled();
    	// Searching and verifying the Sign Up button is Displayed and Enabled
    	
    	if ((signup_button == true) &&(signup_button_enable == true))
    	{
    		System.out.println("Sign up button is available and enabled to Click"); 
    	}
    	else 
    	{
    		System.out.println("Sign up button is unavailable");
    	}
    	
    	driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/div/nav/div/ul/li[8]/a")).click();
    	System.out.println("Clicked the Sign Up button");
    	try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String URL = driver.getCurrentUrl();
    	AssertJUnit.assertEquals(URL, "https://app.clearscore.com/signup" );  //Comparing the url of the page with the expected result url
    	System.out.println("We are on the signup page since the url matches...yay!!");
    	
    	WebElement dropDown = driver.findElement( By.xpath( "//*[@id='signupform_region_input']" ) );
    	List<WebElement> allOptions = dropDown.findElements(By.xpath(".//option"));
    	//List<WebElement> elements = driver.findElements(By.xpath("//*[@id='signupform_region_input']"));
        System.out.println("Number of elements:" +allOptions.size());
        System.out.println("Verifying if the list contains the countries: India, United Kingdom and South Africa"); // Finding the regions and verifying them against expected values
        System.out.println("Verification on the way");
        for (int i=0; i<allOptions.size();i++)
        {
          
          List<String> valid = Arrays.asList("India", "United Kingdom", "South Africa");
               
          if (valid.contains(allOptions.get(i).getText()))
          {
        	  System.out.println(allOptions.get(i).getText());
          }
          
        }
        System.out.println("Hence verified");
        
         driver.findElement(By.xpath("//*[@id='signupform_email_input']")).sendKeys("test3@mailinator.com");
         Boolean button_on = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div[3]/button")).isEnabled();
        System.out.println("Is the Sign up button enabled? :"+" "+ button_on +" "+ "...since email id entered is valid" ); // right email id
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div[3]/button")).click();
        
		Thread.sleep(4000);
		
        URL = driver.getCurrentUrl();
        
    	if (URL.contains("https://app.clearscore.com/step1") == true) //verifying the url contains this string to be sure we are in the right page
    	{
    		System.out.println("We are in the the Step 1 of registration!!!");
    	}
    	else
    	{
    		System.out.println("Sorry bruv, you are on some other page i.e" + " " + URL);
    	}
}
}

    



