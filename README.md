# Rick and Morty Mobil Uygulaması

Bu mobil uygulama, kullanıcının Rick and Morty lokasyonlarını ve karakterlerini listeleyebileceği ve karakter detaylarını görüntüleyebileceği bir uygulamadır. Uygulama, [Rick and Morty API](https://rickandmortyapi.com) kullanılarak yapılmıştır.

### Özellikler
--------------
- Uygulama, MVVM mimarisi kullanılarak geliştirilmiştir.
- Kullanıcı, Rick and Morty lokasyonlarını ve lokasyondaki karakterleri listeleyebilir.
- Kullanıcı karakterlerin detaylarını görüntüleyebilir.
- Uygulama toplamda 3 sayfadan oluşur; splash, ana sayfa ve karakter detay sayfaları.

### Kullanılan Kütüphaneler
--------------
* [Foundation]
  * [Android KTX](https://developer.android.com/kotlin/ktx) -  Uygulamayı daha okunaklı ve kullanımı kolay hale getirmek için kullanılır.
* [Architecture]
  * [Lifecycles](https://developer.android.com/topic/libraries/architecture/lifecycle) -  Aktivite ve fragmentlerin yaşam döngüsü yönetimini kolaylaştırmak için kullanılır.
  * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Veri akışını yönetmek ve gözlemlemek için kullanılır.
  * [Navigation](https://developer.android.com/guide/navigation) - Uygulama içi gezinti yönetimi için kullanılır.
  * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Verilerin saklanması ve UI bileşenleri arasında veri paylaşımı için kullanılır.
* [UI]
  * [Animations & Transitions](https://developer.android.com/develop/ui/views/animations) - Ekranlar arası geçişlerde animasyonlar kullanmak için kullanılır.
  * [Fragment](https://developer.android.com/guide/fragments) - Yeniden kullanılabilir bir UI bileşeni olarak kullanılır.
  * [Layout](https://developer.android.com/develop/ui/views/layout/declaring-layout) -  Kullanıcı arayüzünü tasarlamak için widget kullanılır.
* [Third Party]
  * [Glide](https://github.com/bumptech/glide) - Resim yükleme işlemleri için kullanılır.
  * [Styleabletoast](https://github.com/Muddz/StyleableToast) - Özelleştirilebilir Toast mesajları oluşturmak için kullanılır.
  * [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - Asenkron işlemler için kullanılır.
  * [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependencies Injection yapmak için kullanılır.
  * [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - Asenkron işlemler için kullanılır.
  * [Retrofit](https://square.github.io/retrofit/) - API isteklerini yapmak için kullanılır.

### Kurulum
--------------
1. Bu projeyi klonlayın ya da indirin.
```
git clone https://github.com/hasanalic/RickAndMorty.git
```
2. Android Studio'yu açın ve projeyi açın.
3. Projenin bağımlılıklarını yüklemek için Gradle projeyi senkronize edin.
4. Uygulamayı çalıştırın.

### Ekran Görüntüleri
--------------
| Splash Sayfası | Splash Sayfası - 2 | Ana Sayfa | Karakter Detay Sayfası |
| --- | --- | --- | --- |
| <img src="https://raw.githubusercontent.com/hasanalic/androidkotlin-dersleri/master/images/splash_one.png" width=200> | <img src="https://raw.githubusercontent.com/hasanalic/androidkotlin-dersleri/master/images/splash_two.png" width=200> | <img src="https://raw.githubusercontent.com/hasanalic/androidkotlin-dersleri/master/images/main_screen.png" width=200> | <img src="https://raw.githubusercontent.com/hasanalic/androidkotlin-dersleri/master/images/detail_screen.png" width=200> |
