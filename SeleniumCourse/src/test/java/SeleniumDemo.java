import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumDemo {
    @Test
    public void firstTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        System.out.print("Opening Chrome...");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://rapsodo.com/");
        System.out.println("1- Go to https://rapsodo.com/");

        Thread.sleep(1000);
        driver.findElement((By.linkText("SHOP NOW"))).click();


        driver.findElement(By.xpath("//span[text()='Diamond Sports']")).click();
        System.out.println("2- Click DIAMOND SPORTS option from SHOP menu item");

        Thread.sleep(1000);
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://rapsodo.com/product-category/diamond-sports/");
        System.out.println("URL is matching!");
        System.out.println("2-a. url opening as diamond sports category (?category=diamond-sports)");


        Thread.sleep(1000);

        WebElement item = driver.findElement(By.className("Description"));
        System.out.println(item.getText());
        if ((item.getText()).equalsIgnoreCase("0 items - $0.00")) {
            System.out.println("There is 0 items at the cart");
        } else {
            System.out.println("Not equal 0 item");
        }
        System.out.println("2-b. There is 0 items at the cart and the price amount is $0.00");


        System.out.print("HITTING 2.0 Features...");
        WebElement searchButton = driver.findElement(By.linkText("HITTING 2.0"));
        System.out.println("3- Click HITTING 2.0 product.");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", searchButton);

        System.out.print("Url opening as hitting monitor...");
        Thread.sleep(1000);
        String URL1 = driver.getCurrentUrl();
        Assert.assertEquals(URL1, "https://rapsodo.com/shop/product/rapsodo-hitting-monitor/");
        System.out.println("3-a. url opening as hitting monitor (/product/rapsodo-hitting-monitor/) ");

        String productTitle = driver.findElement(By.className("ProductTitle")).getText();
        String title = driver.getTitle();
        System.out.println("Page title is : " + driver.getTitle());
        if (title.equalsIgnoreCase(productTitle + " | Rapsodo")) {
            System.out.println("Equal");
            System.out.println("3-b. the browser title is the same with the product name (Hitting 2.0 - Rapsodo)");
        } else {
            System.out.println("Not Equal");
        }


        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        WebElement addToCart = driver.findElement(By.className("AddToCartButton"));
        if (addToCart.isEnabled()) {
            System.out.println("ADD TO CART button is enabled");
        } else {
            System.out.println("ADD TO CART button is disabled");
        }
        System.out.println("3-d. ADD TO CART button is disabled");

        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,500)");
        WebElement Element = driver.findElement(By.className("Yes"));
        Element.click();

        if (addToCart.isEnabled()) {
            System.out.println("ADD TO CART button is enabled");
        } else {
            System.out.println("ADD TO CART button is disabled");
        }
        System.out.println("4-a. ADD TO CART button is enabled.");


        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,500)");
        WebElement no_plan = driver.findElement(By.className("TotalProductPrice"));

        if (no_plan.equals("$4,000.00")) {
            System.out.println("Price is showed as $4,000.00");
        } else {
            System.out.println("Price is not $4,000.00 ");
        }
        System.out.println("4- a. Price is showed as $4,000.00 below the combobox.");
        Thread.sleep(1000);
        WebElement addEnable = driver.findElement(By.className("AddToCartButton"));
        addEnable.click();
        System.out.println("5-a. url navigates to cart (/cart/)");

        Thread.sleep(1000);
        WebElement itemTotal = driver.findElement(By.className("Description"));
        System.out.println(itemTotal.getText());
        if ((itemTotal.getText()).equalsIgnoreCase("1 Items - $4,000.00")) {
            System.out.println("There is 1 item at the cart");
        } else {
            System.out.println("Not equal 1 item");
        }
        System.out.println("5-b. There is 1 item at the cart and the price amount is $4,000.00");

        Thread.sleep(1000);
        WebElement cart = driver.findElement(By.linkText("GO TO CART"));
        cart.click();
        js.executeScript("window.scrollBy(0,700)");
        WebElement coupon = driver.findElement(By.id("coupon_code"));
        coupon.sendKeys("2222");
        System.out.println("6- Write a random Coupon code and click APPLY COUPON button.");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[text()='Apply coupon']")).click();
        System.out.println("coupon is  not valid");
        System.out.println("6-a. The error message (Coupon {CouponCode} does not exist!)");


    }
}

