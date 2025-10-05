# TicTacToe Game ❌⭕

Bu depo, Java ile geliştirilmiş iki farklı TicTacToe (XOX) oyunu içermektedir.

## 📁 Projeler

### 1. TicTacToe.java - GUI Versiyonu

Java Swing kullanılarak geliştirilmiş görsel arayüzlü TicTacToe oyunu.

**Özellikler:**
- 🎨 Kullanıcı dostu grafik arayüz
- 🎮 Fare ile tıklayarak oynanabilir
- 🏆 Kazanan kontrolü ve oyun sonu bildirimleri
- 🔄 Yeni oyun başlatma özelliği
- 🎯 3x3 oyun tahtası
- 🌈 Kazanan kombinasyonlar için renk değişimi

  ## 📚 Oyun Kuralları

1. **Başlangıç**: Oyuncu X her zaman ilk hamleyi yapar
2. **Hamle Sırası**: Oyuncular sırayla hamle yapar
3. **Geçerli Hamle**: Sadece boş karelere hamle yapılabilir
4. **Kazanma**: Aynı simgeden 3'ü yan yana getiren oyuncu kazanır
5. **Beraberlik**: Tüm kareler dolduğunda ve kazanan yoksa oyun berabere biter
   

**Ekran Görüntüleri:**


<img width="726" height="793" alt="Winner_X" src="https://github.com/user-attachments/assets/cefc21f7-0da8-47da-be76-06705b88f9fb" />


*X oyuncusunun kazandığı durum - Kazanan kombinasyon vurgulanır*



<img width="723" height="793" alt="Tie" src="https://github.com/user-attachments/assets/4137bd87-35a0-45bd-88ae-765e28a2680a" />


*Beraberlik durumu - Game Over menüsü ile yeniden başlama veya çıkış seçeneği*


### Adımlar

1. Repository'yi klonlayın:
```bash
git clone https://github.com/ErenBalkis/TicTacToe-Game.git
```

2. Proje dizinine gidin:
```bash
cd TicTacToe-Game
```

3. Java dosyasını derleyin:
```bash
javac TicTacToe.java
```

4. Oyunu çalıştırın:
```bash
java TicTacToe
```


**Teknolojiler:**
- Java Swing
- AWT Event Handling

---

### 2. XOX_Oyunu.java - Terminal Versiyonu

Konsol üzerinden oynanan, text-based TicTacToe oyunu.

**Özellikler:**
- 💻 Terminal/konsol tabanlı arayüz
- ⌨️ Klavye ile hamle girişi
- 👥 İki oyunculu oynanış
- 📊 Anlık tahta durumu gösterimi
- ✅ Kazanan ve beraberlik kontrolü

**Kullanım:**
```bash
javac XOX_Oyunu.java
java XOX_Oyunu
```

**Teknolojiler:**
- Java Scanner
- Console I/O

---

## 🎯 Oyun Kuralları

- 3x3'lük bir tahtada oynanır
- Oyuncular sırayla X ve O işaretlerini koyar
- Yatay, dikey veya çapraz olarak 3 işaret yan yana getiren kazanır
- Tüm kareler dolarsa ve kazanan yoksa berabere biter

## 🚀 Gereksinimler

- Java JDK 8 veya üzeri

  
## 🤝 Katkıda Bulunma

Katkılarınızı bekliyorum! Katkıda bulunmak için:

1. Projeyi fork edin
2. Feature branch oluşturun (`git checkout -b feature/AmazingFeature`)
3. Değişikliklerinizi commit edin (`git commit -m 'Add some AmazingFeature'`)
4. Branch'inizi push edin (`git push origin feature/AmazingFeature`)
5. Pull Request oluşturun


 ## 🌟 Teşekkürler

Bu projeyi beğendiyseniz yıldız vermeyi unutmayın! ⭐
