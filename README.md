# SigortaKampanya

Kullanıcının sigorta kampanyalarını kısmi biçimde yönetebileceği ve izleyebileceği bir uygulama.

## Genel Kullanım

Kullanıcının erişebileceği Kampanya yapısını oluşturacak değerler:
- id: Kampanyaları birbirinden ayırmak için kullanılacak.
- kampanyaBasligi: Kampanya ismini oluşturacak.
- kampanyaDetayAciklamasi: Kampanyanın detayını içerecek.
- kampanyaKategorisi: Kampanyanın hangi sigorta türüne ait olduğunu içerecek(TSS, OSS, Hayat Sigortası, Diğer).
- kampanyaStatu: Kampanyanın durumunu gösterecek(Hayat Sigortası aktif olarak, diger sigorta tipleri de onay bekliyor durumunda döngülerine başlayacaklar. Tekrar eden girişler mükerrer statüsünü alıp tekrar değiştirilemeyecek).


## Endpointler ve Fonksiyon Açıklamaları
