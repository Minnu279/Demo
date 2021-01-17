package Facebook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FBlogin_validation {
		public static void main(String args[]) throws IOException, JXLException,BiffException,FileNotFoundException, InterruptedException{
		WebDriver driver = new FirefoxDriver();
		Sheet s;
		//Get access to work book
		FileInputStream fi = new FileInputStream("C:\\Users\\minnu\\eclipse-workspace\\MavenDemoProject\\Testdata.xls");
		Workbook w = Workbook.getWorkbook(fi);
		
		//to get the access to the sheet no:1
		s = w.getSheet(0);
		//Getting no of rows and printing it in console
		int z=s.getRows();
		System.out.println("no of rows:"+z);
		
		//Go to Facebook login page
		driver.get("https://facebook.com");
		
		//Reading the user name from excel
		for(int row=1; row <s.getRows();row++)
		{
		String username = s.getCell(0, row).getContents();
		System.out.println("Username:"+username);
		//driver.get("http://www.gmail.com");
		driver.manage().window().maximize();
		
		//Find the user name element from Facebook login page and passing the user name from excel
		driver.findElement(By.id("email")).sendKeys(username);
		
		//Read Password from excel and Printing it in console
		String password= s.getCell(1, row).getContents();
		System.out.println("Password "+password);
		
		//Find password element from FB login page and passing the read password from excel
		driver.findElement(By.id("pass")).sendKeys(password);
		Thread.sleep(10000);
		
		//Click submit button
		driver.findElement(By.name("login")).click();
		
		//Validate if Login is Successful. If login is success, logout from the page
        try {
            driver.findElement(By.id("loginbutton")).isDisplayed();
        		System.out.println("Login Failed");    
        		driver.findElement(By.id("email")).clear();	
        } catch (org.openqa.selenium.NoSuchElementException e) {
//        driver.findElement(By.className("hu5pjgll lzf7d6o1 sp_xnXmd0XPM3- sx_a63877")).isDisplayed();
         System.out.println("Login Successful");
         driver.findElement(By.className("j83agx80_l9j0dhe7")).click();
         //driver.findElement(By.xpath("//i[@class='hu5pjgll lzf7d6o1 sp_j2bFYiGpFkd sx_3e5c51']")).click();
         Thread.sleep(10000);
       //driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/span[1]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Log Out')]")).click();
        }
         
//		if((driver.findElement(By.xpath("//span[@id='errormsg_0_Passwd']"))).isDisplayed())
//		{
//		System.out.println("Login Fail !Error Exists");
//		String Error=driver.findElement(By.xpath("//span[@id='errormsg_0_Passwd']")).getText();
//		System.out.println("The Error is :"+Error);
//		}
//		else
//		{
//		System.out.println("Login Pass");
//		}
		//driver.close();
		}
		driver.quit();
}
}
