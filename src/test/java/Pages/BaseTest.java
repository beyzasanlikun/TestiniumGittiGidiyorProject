package Pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BaseTest {
    public WebDriver driver;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\testinium\\Desktop\\gittigidiyor-master\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://www.gittigidiyor.com/");

    }

    @Test
    public void testing() throws InterruptedException, IOException {
        //arama ile ilgili işlemler
        SearchPage searchpage= new SearchPage(driver);
        searchpage.searchProduct();
        searchpage.searchPage();
        searchpage.selectProduct();

        //Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılır.
        File file1 = new File("Information.txt");
        FileWriter fw = new FileWriter(file1);
        fw.write(driver.findElement(By.id("sp-title")).getText() + "\n");
        fw.write(driver.findElement(By.id("sp-price-discountPrice")).getText());
        fw.close();

        //sepetle ilgili işlemler
        BasketPage basketpage= new BasketPage(driver);
        basketpage.addtoBasketandCompare();
        basketpage.changeAmount();
        basketpage.deleteProduct();

    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
