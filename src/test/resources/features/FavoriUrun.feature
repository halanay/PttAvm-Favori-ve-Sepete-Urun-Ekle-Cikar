Feature: PttAVM-Favori ve Sepete Ekleme Çıkarma Test İşlemi

  @wip #1. Anasayfa Açılır ve kontrol edilir
  Scenario: Anasayfa Açılır
    Given user go to the home page
    When "Anasayfa" kontrol edilir
    And user go to the login page

 @wip #2-3 Login Sayfasına gidilir
  Scenario: Login İşlemi Yapılır
    And user enter correct credentials
    And "login işlemi" kontrol edilir

  @wip #5. Arama kutusuna ürünün ismi yazılıp arama tuşuna basılır
  Scenario: Arama  İşlemi Yapılır
   And user "Iphone" kelimesini aratir
   And "Ürün" kontrol edilir

   #favoriye ekleme
  @wip #6-7 Ürün favoriye eklenir
  Scenario: Favoriye Ekleme-Çıkarma İşlemi
  And Sayfadaki 1. ürün açılır
 And Sayfadaki 1. ürün favorilere eklenir
 And "favorilerim sayfası" kontrol edilir

   #8. Ürün Favoriden Kaldırılır
   Then Eklenen ürün favorilerden silinir
   And "silme işlemi" kontrol edilir


  @wip #9-10 Ürün sepete eklenir-silinir
  Scenario: Sepete Ekleme-Çıkarma İşlemi
  #Bilgisayar arama işlemi
   And user "Bilgisayar" kelimesini aratir
   And "Ürün" kontrol edilir
   #Sepete ekleme
   And Sayfadaki 2. ürün açılır
   And Sayfadaki 2. ürün sepete eklenir
   #And "Sepetim" sayfasına gidilir
   And "Sepetim sayfası" kontrol edilir

   #Sepeti kotrol et-çıkar
   Then Eklenen ürün sepetten silinir
   #And "silme işlemi" kontrol edilir

   @wip #11 Üye Logout işlemi yapılır
   Scenario: Logout İşlemi
   Then Üye çıkış işlemi yapılır