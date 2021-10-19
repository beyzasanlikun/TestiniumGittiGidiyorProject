package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class SearchPage {
    WebDriver driver;
    public SearchPage(WebDriver driver){ this.driver=driver;}

    public void searchProduct() {
        //çerezleri kapatır.
        WebElement close = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/section[1]/section[2]/a"));
        close.click();

        //Arama kısmı bulunur ve  bilgisayar yazılır.
        WebElement search = driver.findElement(By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input"));
        search.sendKeys("bilgisayar");

        //Arama butonu bulunur ve tıklanır.
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div[1]/div[2]/form/div/div[2]/button"));
        searchButton.click();

    }
    public void searchPage() throws InterruptedException {
        //Ürünlerin olduğu sayfada 2. sayfayı bulana kadar scroll yapar.
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/div/div/div[2]/div/div[3]/div[4]/nav/ul/li[3]/a/span")));
        Thread.sleep(2000);

        //2.sayfa butonunu bulur ve tıklar.
        WebElement pageButton = driver.findElement((By.xpath("//*[@id=\"__next\"]/main/div[2]/div/div/div[2]/div/div[3]/div[4]/nav/ul/li[3]/a/span")));
        pageButton.click();
        Thread.sleep(2000);

        //2.sayfanın açılıp açılmadığı kontrol edilir. Açılmadıysa 2.sayfada değilsiniz hatası verir.
        Assert.assertEquals("2.sayfada değilsiniz!","https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2",driver.getCurrentUrl());

    }

    public void selectProduct() throws InterruptedException {
        //Rastgele bir ürün seçmek için
        Random random = new Random();
        int randomsayi = random.nextInt(6);
        //random sayı'ncı ürüne gider.
        //WebElement selectedproduct = driver.findElement(By.linkText("//*[@id=\"__next\"]/main/div[2]/div/div/div[2]/div/div[3]/div[3]/ul/li["+randomsayi+"])" ));

        //herhangi bir ürünü seçip tıklar.
        WebElement selectedproduct = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/div/div/div[2]/div/div[3]/div[3]/ul/li[8]/article/div"));
        //String productinfo = driver.findElement(By.id("sp-title")).getText();
        selectedproduct.click();
        Thread.sleep(4000);

    }


}
