# SigortaKampanya

Kullanıcının sigorta kampanyalarını kısmi biçimde yönetebileceği ve izleyebileceği bir uygulama.

## Genel Kullanım

Kullanıcının erişebileceği Kampanya yapısını oluşturacak değerler:
- id: Kampanyaları birbirinden ayırmak için kullanılacak.
- kampanyaBasligi: Kampanya ismini oluşturacak.
- kampanyaDetayAciklamasi: Kampanyanın detayını içerecek.
- kampanyaKategorisi: Kampanyanın hangi sigorta türüne ait olduğunu içerecek(TSS, OSS, Hayat Sigortası, Diğer).
- kampanyaStatu: Kampanyanın durumunu gösterecek(Hayat Sigortası aktif olarak, diger sigorta tipleri de onay bekliyor durumunda döngülerine başlayacaklar. Tekrar eden girişler mükerrer statüsünü alıp tekrar değiştirilemeyecek).

Database olarak in-memory sınıfında bulunan H2 Database kullanıldı.

Maven Spring Boot projesini hayata geçirmek amacıyla kullanıldı.

Endpointlere erişim olarak Postman kullanıldı.


## Endpointler ve Fonksiyon Açıklamaları

RestController sayesinde hazırlanan endpointlerle Fonksiyonlara erişim sağlandı. Endpointlerin çalıştığı Postman üzerinden yapılan deneylerle desteklendi. Koleksiyon projenin içerisinde bulunuyor.

### Kampanya Girişi(POST)

Kampanya başlıgı, detayı ve kategorisi girilerek yeni kampanya oluşturulur. Oluşturulan kampanyanın sigorta tipine bağlı olarak kampanya statüsü belirlenir. Hayat sigortası döngüsüne aktif olarak başlar. Diğer sigortalar ise döngülerine onay bekliyor vasıtasında başlar.

```
localhost:8080/kampanya/kampanyagiris
```

### Kampanya Aktivasyonu(POST)

Bu fonksiyon kampanya id'si ve ve bir sayı alır. Alınan sayı eğer 1 ise kampanya aktive edilir, eğer hali hazırda aktif ise boş dönecektir. Alınan sayı eğer 2 ise kampanya deaktive edilir, eğer hali hazırda aktif ise boş dönecektir. Eğer id'ye ait olan kampanyanın statüsü mükerrer ise boş dönecektir.

```
localhost:8080/kampanya/kampanyastatudegistir
```

### Kampanya Durum(GET)

Tüm kampanyaların statülerini kontrol eder. Sonucunda değerleri bir string olarak çıkartır.

```
localhost:8080/kampanya/kampanyastatu
```

### Tüm Kampanyalar(GET)

Tüm kampanyaları çıkartır.

```
localhost:8080/kampanya/tumkampanyalar
```



