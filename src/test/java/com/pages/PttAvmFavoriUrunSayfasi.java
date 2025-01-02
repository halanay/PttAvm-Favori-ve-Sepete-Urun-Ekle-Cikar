package com.pages;

import com.utilities.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PttAvmFavoriUrunSayfasi {

    public PttAvmFavoriUrunSayfasi(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // Login locatorları
    @FindBy(xpath = "//*[@id=\"main\"]/div[4]/div[2]/div/button")
    public WebElement cereziKabulEt;

    @FindBy(xpath = "//*[@id=\"main-page-login\"]")
    public WebElement girisYapYazi;

    @FindBy(id="login-email")
    public WebElement inputEmail;

    @FindBy(id="login-password")
    public WebElement inputPassword;

    @FindBy(xpath = "//*[@id=\"main-page-side-bar-login\"]/div[2]")
    public WebElement girisYap;



    // arama locator ları
    @FindBy (id = "search-main-input")
    public WebElement searchInputBox;

    @FindBy (xpath = "//button[@aria-label='Ara']") // //a[@class='searchBtn']
    public WebElement searchButton;

    @FindBy(xpath = "//div[@class=\"flex flex-row flex-wrap\"]//h2")
    public List<WebElement> arananUrunler;

    //favoriye ekleme locatorları
    @FindBy(xpath = "//span[.='Favorilere Ekle']")
    public WebElement favoriyeEkle;

    @FindBy(xpath = "//h1[@class=\"text-lg font-medium break-word\"]")
    public WebElement urunIsmi;

    @FindBy(xpath = "//a[@aria-label=\"Logo\"]")
    public WebElement logo;
    //sepete ekleme locatorı
    @FindBy(xpath = "//*[@id=\"product-detail-add-to-cart\"]/div[2]")
    public WebElement sepeteEkle;

    //Favori sayfasına gidiş locator
    @FindBy(xpath = "//span[.='Hesabım']")
    public WebElement hesabim;

    @FindBy (xpath = "//span[.='Listelerim']")
    public WebElement listelerim;

    //Sepetim sayfasına gidiş locator
    @FindBy(xpath = "//span[.='Sepetim']")
    public WebElement sepetim;
    //sepetteki ürün adet sayısı
    @FindBy(xpath = "//*[@id=\"main\"]//label/input")
    public WebElement sepetUrunAdedi;

    @FindBy(partialLinkText = "Ekstra Garanti İstemiyorum")
    public WebElement ekstraGarantiIstemeKapat;

    @FindBy(xpath = "//div[8]/button")
    public WebElement anladimButonu;

    @FindBy(xpath = " //label[@class=\"leading-8 items-center flex\"]/input")
    public WebElement sepettekiUrunSayisi;

    @FindBy(xpath = "//h1/div/button")
    public WebElement sepetiTemizle;

    @FindBy(xpath = "//button[@class=\"remove\"]")
    public WebElement urunleriSepettenKaldir;

    @FindBy(xpath = "//*[@id=\"main\"]/div[3]/div/div/div/div/div[1]/div/div/span")
    public WebElement sepetBos;

    //Favori sayısı
    @FindBy (xpath = "//span[@class=\"md:text-base\"]")
    public WebElement FavoriSayisiText;


    //Favorideki ürün
    @FindBy(xpath = "//div[@class=\"product-list-box-container transition-all duration-250\"]/a")
    public WebElement favoriUrun;


    //Favori silme
    @FindBy(xpath = "//i[@class=\"eptticon-trash\"]")
    public WebElement deleteButton;

    // çıkış yap butonu
    @FindBy(xpath = "//*[@id=\"main\"]/div[6]/div[2]/div/ul/li[12]/span/div/span")
    public WebElement logoutButton;
}
