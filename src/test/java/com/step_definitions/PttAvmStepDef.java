package com.step_definitions;
import com.pages.PttAvmFavoriUrunSayfasi;
import com.utilities.BrowserUtils;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PttAvmStepDef {

    PttAvmFavoriUrunSayfasi PttAvmLocators =new PttAvmFavoriUrunSayfasi();
    Actions actions = new Actions(Driver.getDriver());
    String expectedTitle;
    String expected;
    String actual;
    String product;
    WebDriverWait wait;

    //1. Anasayfa Açılır ve kontrol edilir
    @Given("user go to the home page")
    public void user_go_to_the_home_page() throws InterruptedException {
        Driver.getDriver().get(ConfigurationReader.getProperty("login_url"));
        Thread.sleep(1000);
    }

    //2. Login Sayfasına gidilir
    @And("user go to the login page")
    public void user_go_to_the_login_page(){
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3));
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PttAvmLocators.cereziKabulEt.click();
        PttAvmLocators.girisYapYazi.click();
    }

    //3. Login işlemi yapılır
    @And("user enter correct credentials")
    public void user_enter_correct_credentials()  {
        PttAvmLocators.inputEmail.sendKeys(ConfigurationReader.getProperty("email"));
        PttAvmLocators.inputPassword.sendKeys(ConfigurationReader.getProperty("password"));
        PttAvmLocators.girisYap.click();
    }

    //4. kontol etme metodu. Switch Case ile bütün kontroller yaptırılır
    @And("{string} kontrol edilir")
    public void kontrolEdilir(String islem) {
        switch (islem) {
            case "Ana sayfa":
                expectedTitle = ConfigurationReader.getProperty("anasayfa");
                String actualTitle = Driver.getDriver().getTitle();
                Assert.assertEquals("Başlık uyuşmuyor!", expectedTitle, actualTitle);
                System.out.println("Anasayfa açıldı");
                System.out.println("title = "+Driver.getDriver().getTitle());
                //Assert.assertEquals("PttAVM.com – Güvenli Alışveriş Merkezi", Driver.getDriver().getTitle());
                break;
            case "login işlemi":
                // Kullanıcı giriş işlemini tamamladıktan sonra çıkış yap butonu görünür hale gelir.
                Assert.assertTrue("Çıkış yap butonunu kontrol et!", PttAvmLocators.logoutButton.isDisplayed());
                System.out.println("login işlemi başarılı olarak yapıldı");
                break;
            case "Ürün":
               // for (WebElement eachProduct : PttAvmLocators.arananUrunler) {
               //     Assert.assertTrue("Ürün adı sayfadaki her üründe yer almamaktadır!", eachProduct.getText().toLowerCase().contains(product.toLowerCase()));
               //     Assert.assertTrue("",eachProduct.getText().toLowerCase().contains(product.toLowerCase()));
               // }
                break;
            case "açılan pencere":
                expectedTitle = product + " - PttAVM.com - 2024 ";
                //BrowserUtils.verifyTitle(expectedTitle);
                break;
            case "favorilerim sayfası":
                PttAvmLocators.logo.click();
                wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(2));
                PttAvmLocators.hesabim.click();
                PttAvmLocators.listelerim.click();

                wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
                BrowserUtils.waitForClickAbility(PttAvmLocators.FavoriSayisiText,10).click();
                expected = "Favori Listem listesinde 1 ürün gösteriliyor";
                actual = PttAvmLocators.FavoriSayisiText.getText();
                Assert.assertEquals("Favoriler Sayfasına Ulaşılamadı",expected.trim().toLowerCase() ,actual.trim().toLowerCase());
                System.out.println("Favoriler Sayfası Açıldı");
                break;
            case "Sepetim sayfası":
                wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(2));
                PttAvmLocators.hesabim.click();
                PttAvmLocators.sepetim.click();
                wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(PttAvmLocators.anladimButonu));
                PttAvmLocators.anladimButonu.click();
                //wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(PttAvmLocators.sepetUrunAdedi));
                Assert.assertTrue("Sepeti Temizle Yazısını kontrol et!",PttAvmLocators.sepetiTemizle.isDisplayed());
                System.out.println("Sepetim Sayfası Açıldı");
                break;
            case "silme işlemi":
                expected = "Favori Listem listesinde 0 ürün gösteriliyor";
                wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
                boolean isUpdated = wait.until(ExpectedConditions.textToBePresentInElement(PttAvmLocators.FavoriSayisiText, "0 ürün gösteriliyor"));
                actual = PttAvmLocators.FavoriSayisiText.getText();
                Assert.assertEquals("Ürün Silinemedi",expected.trim().toLowerCase() ,actual.trim().toLowerCase());
                System.out.println("Ürün Başarı ile Silindi");
                break;
        }
    }

    //5. Arama kutusuna ürünün ismi yazılıp arama tuşuna basılır
    @And("user {string} kelimesini aratir")
    public void user_kelimesini_aratir(String arananKelime) {
        this.product=arananKelime;
        actions.click(PttAvmLocators.searchInputBox).perform();
        //arama.searchInputBox.clear();
        PttAvmLocators.searchInputBox.sendKeys(arananKelime, Keys.ENTER);
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3));
        System.out.println(arananKelime + "kelimesi aratıldı");
    }

    //6. Favoriye eklenecek ürün açılır
    @And("Sayfadaki {int}. ürün açılır")
    public void sayfadaki_ürün_açılır(Integer ürünSırası) {
        BrowserUtils.waitForClickAbility(PttAvmLocators.arananUrunler.get(0), 10);
        PttAvmLocators.arananUrunler.get(ürünSırası).click();
        System.out.println("Sayfadaki "+ürünSırası+". ürün sayfası açıldı");

    }
    //7. Ürün favoriye eklenir
    @And("Sayfadaki {int}. ürün favorilere eklenir")
    public void sayfadaki_ürün_favorilere_eklenir(Integer ürünSırası) {
        BrowserUtils.waitForClickAbility(PttAvmLocators.favoriyeEkle,10).click();
        PttAvmLocators.favoriyeEkle.click();
        System.out.println("Sayfadaki "+ürünSırası+". ürün olan "+PttAvmLocators.urunIsmi.getText()+" favoriye eklendi");
    }

    //8. Ürün Favoriden Kaldırılır
    @And("Eklenen ürün favorilerden silinir")
    public void eklenen_ürün_favorilerden_silinir() {
        BrowserUtils.hover(PttAvmLocators.favoriUrun);
        PttAvmLocators.deleteButton.click();
    }

    //9. Ürün sepete eklenir
    @And("Sayfadaki {int}. ürün sepete eklenir")
    public void sayfadaki_ürün_sepete_eklenir(Integer ürünSırası) {
        BrowserUtils.waitForClickAbility(PttAvmLocators.sepeteEkle,10).click();
        PttAvmLocators.sepeteEkle.click();
        System.out.println("Sayfadaki "+ürünSırası+". ürün olan "+PttAvmLocators.urunIsmi.getText()+" sepete eklendi");
        Driver.getDriver().navigate().refresh();
    }

    //10. Ürün sepetten silinir
    @Then("Eklenen ürün sepetten silinir")
    public void eklenenÜrünSepettenSilinir() {
        PttAvmLocators.sepetiTemizle.click();
        wait.until(ExpectedConditions.elementToBeClickable(PttAvmLocators.urunleriSepettenKaldir));
        PttAvmLocators.urunleriSepettenKaldir.click();
        System.out.println("Sepet Temizlendi");
        expected="Sepetinizde ürün bulunmamaktadır.";
        actual = PttAvmLocators.sepetBos.getText();
        Assert.assertEquals("Sepet Boş Değil",expected.trim().toLowerCase() ,actual.trim().toLowerCase());
        System.out.println(actual);
        wait.until(ExpectedConditions.elementToBeClickable(PttAvmLocators.hesabim));
    }

    //. 11 Üye Logout işlemi yapılır
    @Then("Üye çıkış işlemi yapılır")
    public void üye_çıkış_işlemi_yapılır() throws InterruptedException {
        PttAvmLocators.hesabim.click();
        //WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.elementToBeClickable(PttAvmLocators.logoutButton));
        Assert.assertTrue("Çıkış yap butonunu kontrol et!", PttAvmLocators.logoutButton.isDisplayed());
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", PttAvmLocators.logoutButton);
        System.out.println("Sistemden çıkış yapıldı");
        Driver.closeDriver();
    }
}






