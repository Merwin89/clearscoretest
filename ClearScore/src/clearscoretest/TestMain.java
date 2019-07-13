package clearscoretest;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

public class TestMain {

	public static WebDriver driver; 
	public static String vURL = "http://www.clearscore.com";;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+ "\\Resources\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.get(vURL);
		driver.manage().deleteAllCookies(); //Deleting all existing cookies from browser for a fresh start
		System.out.println("Preparing test environment - Deleted all cookies");
		
		System.out.println("Test case 1 ==== Check if Notification is present");
		
		
		Testcase1(); //Presence of notification is verified
		
		System.out.println("Test case 3 ==== The notification does not reappear after being dismissed");
		Testcase1(); //Notification does not reappear verification
		
		Cookiewrite();	//Cookie setting verification
		
		
		//Signup();  //Signup button functionality verification
		
		Signup signup = new Signup();
		signup.signuptest();
		
		driver.close();
		driver.quit();
    			
	}
	
	
	@Test
		public static void Testcase1()
	{
		
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		//checking for the notification bar
	try {
		if(driver.findElement(By.className("websiteCookieNotice--2LLwd")) != null)
		{
			System.out.println("Cookie Notification is displayed?");
			
			//Verifying that the notification can be dismissed
			Boolean dismiss = driver.findElement(By.className("button---dYCq")).isDisplayed();
			if(dismiss == true)
			{
				System.out.println("Test case 2 ==== Check if Notification can be dismissed");
				System.out.println("The notification can be dismissed");
				driver.findElement(By.className("button---dYCq")).click();			
			}
			
		}
		//verifying that the notification does not appear again
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("The Notification is no longer present");
	}
	}
	
	@Test
	public static void Cookiewrite()
	{
		// create file named Cookies to store Login Information		
        File file = new File("ClearScoreCookies.data");							
        try		
        {	  
            // Delete old file if exists
			file.delete();		
            file.createNewFile();			
            FileWriter fileWrite = new FileWriter(file);							
            BufferedWriter Bwrite = new BufferedWriter(fileWrite);							
            // loop for getting the cookie information 		
            	
            		
            for(Cookie cookies : driver.manage().getCookies())							
            {			
                Bwrite.write((cookies.getName()+"  "+cookies.getValue()+"  "+cookies.getDomain()+"  "+cookies.getPath()+"  "+cookies.getExpiry()+"  "+cookies.isSecure()));																									
                Bwrite.newLine();             
            }			
            Bwrite.close();			
            fileWrite.close();
            System.out.println("The cookies are saved in ClearScoreCookies.data");
           // Writing the clearscore cookies to file
        }
        catch(Exception ex)					
        {		
            ex.printStackTrace();			
        }		
	
        
	}
	

	
	
	
}
