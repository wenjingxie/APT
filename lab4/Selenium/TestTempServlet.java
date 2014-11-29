import junit.framework.*;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestTempServlet extends TestCase {

  public void test_andy_login() throws Exception {
      WebDriver driver = new FirefoxDriver();
      driver.get("http://apt-public.appspot.com/testing-lab-login.html");
      driver.findElement(By.name("userId")).clear();
      driver.findElement(By.name("userId")).sendKeys("Andy");
      driver.findElement(By.name("userPassword")).clear();
      driver.findElement(By.name("userPassword")).sendKeys("apple");
      driver.findElement(By.xpath("//input[@value='Log me in!']")).click();
      assertTrue(driver.findElement(By.xpath("//body")).getText().contains("Convert"));
      driver.quit();
  }

  public void test_bob_login() throws Exception {
      WebDriver driver = new FirefoxDriver();
      driver.get("http://apt-public.appspot.com/testing-lab-login.html");
      driver.findElement(By.name("userId")).clear();
      driver.findElement(By.name("userId")).sendKeys(" bob");
      driver.findElement(By.name("userPassword")).clear();
      driver.findElement(By.name("userPassword")).sendKeys("bathtub");
      driver.findElement(By.xpath("//input[@value='Log me in!']")).click();
      assertTrue(driver.findElement(By.xpath("//body")).getText().contains("Convert"));
      driver.quit();
  }

  public void test_charley_login() throws Exception {
      WebDriver driver = new FirefoxDriver();
      driver.get("http://apt-public.appspot.com/testing-lab-login.html");
      driver.findElement(By.name("userId")).clear();
      driver.findElement(By.name("userId")).sendKeys("charley");
      driver.findElement(By.name("userPassword")).clear();
      driver.findElement(By.name("userPassword")).sendKeys(" china");
      driver.findElement(By.xpath("//input[@value='Log me in!']")).click();
      assertTrue(driver.findElement(By.xpath("//body")).getText().contains("Convert"));
      driver.quit();
  }

  public void test_lockout_login() throws Exception {
      WebDriver driver = new FirefoxDriver();
      //first time
      driver.get("http://apt-public.appspot.com/testing-lab-login.html");
      driver.findElement(By.name("userId")).clear();
      driver.findElement(By.name("userId")).sendKeys("charley");
      driver.findElement(By.name("userPassword")).clear();
      driver.findElement(By.name("userPassword")).sendKeys(" China");
      driver.findElement(By.xpath("//input[@value='Log me in!']")).click();

      //second time
      driver.navigate().back();
      driver.findElement(By.name("userId")).clear();
      driver.findElement(By.name("userId")).sendKeys("charley");
      driver.findElement(By.name("userPassword")).clear();
      driver.findElement(By.name("userPassword")).sendKeys(" China");
      driver.findElement(By.xpath("//input[@value='Log me in!']")).click();

      //third time
      driver.navigate().back();
      driver.findElement(By.name("userId")).clear();
      driver.findElement(By.name("userId")).sendKeys("charley");
      driver.findElement(By.name("userPassword")).clear();
      driver.findElement(By.name("userPassword")).sendKeys(" China");
      driver.findElement(By.xpath("//input[@value='Log me in!']")).click();

      // wait for 1 min
      Thread.sleep(60*1000);

      driver.navigate().back();
      driver.findElement(By.name("userId")).clear();
      driver.findElement(By.name("userId")).sendKeys("charley");
      driver.findElement(By.name("userPassword")).clear();
      driver.findElement(By.name("userPassword")).sendKeys("china");
      driver.findElement(By.xpath("//input[@value='Log me in!']")).click();
      assertTrue(driver.findElement(By.xpath("//body")).getText().contains("Convert"));
      driver.quit();
  }


  public void test_temp_convert() throws Exception {
      WebDriver driver = new FirefoxDriver();
      driver.get("http://apt-public.appspot.com/testing-lab-calculator.html");
      driver.findElement(By.name("farenheitTemperature")).clear();
      driver.findElement(By.name("farenheitTemperature")).sendKeys("12");
      driver.findElement(By.xpath("//input[@value='Convert']")).click();
      assertTrue(driver.findElement(By.xpath("//h2")).getText().equals("12 Farenheit = -11.11 Celsius"));
      driver.quit();
  }

  public void test_boundary_convert() throws Exception {
      WebDriver driver = new FirefoxDriver();
      driver.get("http://apt-public.appspot.com/testing-lab-calculator.html");
      driver.findElement(By.name("farenheitTemperature")).clear();
      driver.findElement(By.name("farenheitTemperature")).sendKeys("212");
      driver.findElement(By.xpath("//input[@value='Convert']")).click();
      assertTrue("boundary 212 should have 2 places of precision",driver.findElement(By.xpath("//h2")).getText().equals("212 Farenheit = 100.00 Celsius"));
      driver.quit();
  }

  public void test_outBoundary1_convert() throws Exception {
      WebDriver driver = new FirefoxDriver();
      driver.get("http://apt-public.appspot.com/testing-lab-calculator.html");
      driver.findElement(By.name("farenheitTemperature")).clear();
      driver.findElement(By.name("farenheitTemperature")).sendKeys("-12");
      driver.findElement(By.xpath("//input[@value='Convert']")).click();
      assertTrue("-12 should have 1 place of precision",driver.findElement(By.xpath("//h2")).getText().equals("-12 Farenheit = 24.4 Celsius"));
      driver.quit();
  }
  public void test_outBoundary2_convert() throws Exception {
      WebDriver driver = new FirefoxDriver();
      driver.get("http://apt-public.appspot.com/testing-lab-calculator.html");
      driver.findElement(By.name("farenheitTemperature")).clear();
      driver.findElement(By.name("farenheitTemperature")).sendKeys("214");
      driver.findElement(By.xpath("//input[@value='Convert']")).click();
      assertTrue("-214 should have 1 place of precision",driver.findElement(By.xpath("//h2")).getText().equals("214 Farenheit = 101.1 Celsius "));
      driver.quit();
  }  

  public void test_format1_convert() throws Exception {
      WebDriver driver = new FirefoxDriver();
      driver.get("http://apt-public.appspot.com/testing-lab-calculator.html");
      driver.findElement(By.name("farenheitTemperature")).clear();
      driver.findElement(By.name("farenheitTemperature")).sendKeys("test");
      driver.findElement(By.xpath("//input[@value='Convert']")).click();
      assertTrue(driver.findElement(By.xpath("//h2")).getText().equals("Need to enter a valid temperature!Got a NumberFormatException on test"));
      driver.quit();
  }

  public void test_format2_convert() throws Exception {
      WebDriver driver = new FirefoxDriver();
      driver.get("http://apt-public.appspot.com/testing-lab-calculator.html");
      driver.findElement(By.name("farenheitTemperature")).clear();
      driver.findElement(By.name("farenheitTemperature")).sendKeys("9.73e1");
      driver.findElement(By.xpath("//input[@value='Convert']")).click();
      assertTrue("test invalid input format 9.73e1",driver.findElement(By.xpath("//h2")).getText().equals("Need to enter a valid temperature!Got a NumberFormatException on test"));
      driver.quit();
  }

}

