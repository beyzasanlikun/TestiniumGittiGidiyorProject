package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class BasketPage {

    WebDriver driver;
    String url = "https://www.gittigidiyor.com/";

    public BasketPage(WebDriver driver) { this.driver = driver;}

    public void addtoBasketandCompare () throws InterruptedException {
        //indirimli tutarı buluyor. İndirimsiz tutar için sp-price-highPrice yazılmalı.
        //Seçilen ürün fiyatı karşılaştırma yapmak için kaydedilir.
        String productPrice = driver.findElement(By.id("sp-price-lowPrice")).getText();

        //sepete ekle butonunu buluncaya kadar scroll yapar. Favorilere ekle kısmını inspect yaptım.
        JavascriptExecutor jse2 = (JavascriptExecutor) driver;
        jse2.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//*[@id=\"CountSelect\"]/li[3]/a")));
        Thread.sleep(4000);

        //ürün sepete eklenir.
        WebElement addbasket = driver.findElement(By.id("add-to-basket"));
        addbasket.click();
        Thread.sleep(4000);

        // Sepetim PopUp'ını açar.
        driver.findElement(By.xpath("//*[@class='basket-container robot-header-iconContainer-cart']/a")).click();

        // Sepet fiyatı karşılaştırma yapmak için kaydedilir.
        String basketPrice = driver.findElement(By.xpath("//*[@class='total-price']")).getText();

        //Sepetin ve ürünün fıyatı karşılaştırılır.
        Assert.assertEquals("eşit değil", productPrice, basketPrice);

    }

    public void changeAmount () throws InterruptedException {
        //Adet değiştirmek için amount kısmı açılır.
        WebElement amount = driver.findElement(By.xpath("//*[@class='amount']"));
        amount.click();

        //Adet 2'ye çıkarılır.
        WebElement amount2 = driver.findElement(By.xpath("//*[@class='amount']/option[2]"));
        amount2.click();
        Thread.sleep(4000);

        //Adedin 2 olduğunu doğrulamak için kaydedilir.
        String selectedAmount = driver.findElement(By.xpath("//*[@class='gg-d-16 gg-m-14 detail-text']")).getText();

        //Amount kısmı kapatılır.
        driver.findElement(By.xpath("//*[@class='cart-summary-title']")).click();
        Thread.sleep(2000);

        //sepet tutarı ve ürün tutarı karşılaştırlır.
        Assert.assertEquals("Sepet Tutarı (2 Adet)", selectedAmount);

    }

    public void deleteProduct () {
        //delete butonuna tıklanır.
        WebElement delete = driver.findElement(By.xpath("//*[@class='gg-icon gg-icon-bin-medium']"));
        delete.click();

        //sepetinizde ürün bulunmamaktadır yazısı gelene kadar bekler.
        WebDriverWait wait3 = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"empty-cart-container\"]/div[1]/div[1]/div/div[2]/h2")));

        //en son sepetin boş olduğu kontrol edilir.
        String productPiece = driver.findElement(By.xpath("//*[@id=\"empty-cart-container\"]/div[1]/div[1]/div/div[2]/h2")).getText();
        String product ="Sepetinizde ürün bulunmamaktadır.";
        Assert.assertEquals("Sepette ürün yok",productPiece,product);


    }


}
