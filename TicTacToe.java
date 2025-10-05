package tictactoegame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * TicTacToe (XOX) oyunu sınıfı
 * İki oyuncu için grafiksel arayüzlü bir XOX oyunu
 */
public class TicTacToe {

    // Oyun penceresi boyutları
    int boardWidth = 600;   // Pencere genişliği
    int boardHeight = 650;  // Pencere yüksekliği (50 piksel üst başlık için ekstra)

    // Ana pencere ve UI bileşenleri
    JFrame frame = new JFrame("Tic-Tac-Toe");  // Ana oyun penceresi
    JLabel textLabel = new JLabel();            // Üstteki durum yazısı (kimin sırası, kazanan vb.)
    JPanel textPanel = new JPanel();            // Durum yazısını tutan panel
    JPanel boardPanel = new JPanel();           // Oyun tahtasını tutan panel

    // Oyun tahtası ve oyuncu bilgileri
    JButton[][] board = new JButton[3][3];  // 3x3'lük oyun tahtası (her hücre bir buton)
    String playerX = "X";                    // Birinci oyuncu işareti
    String playerO = "O";                    // İkinci oyuncu işareti
    String currentPlayer = playerX;          // Şu anki sıradaki oyuncu (X ile başlar)

    // Oyun durumu değişkenleri
    boolean gameOver = false;  // Oyun bitmiş mi kontrolü
    int turns = 0;             // Toplam hamle sayısı (beraberlik kontrolü için)

    /**
     * TicTacToe constructor - Oyunu başlatır ve arayüzü oluşturur
     */
    TicTacToe() {
        // === ANA PENCERE AYARLARI ===
        frame.setVisible(true);                              // Pencereyi görünür yap
        frame.setSize(boardWidth, boardHeight);              // Pencere boyutunu ayarla
        frame.setLocationRelativeTo(null);                   // Pencereyi ekranın ortasına yerleştir
        frame.setResizable(true);                            // Pencere boyutu değiştirilebilir
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X'e basınca programı kapat
        frame.setLayout(new BorderLayout());                 // BorderLayout kullan (Kuzey-Güney-Doğu-Batı-Merkez)
        
        // === ÜST BAŞLIK PANELİ (Durum Yazısı) ===
        textLabel.setBackground(Color.CYAN);                         // Arka plan rengi açık mavi
        textLabel.setForeground(Color.WHITE);                        // Yazı rengi beyaz
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));         // Yazı tipi: Arial, kalın, 50 punto
        textLabel.setHorizontalAlignment(JLabel.CENTER);             // Yazıyı ortala
        textLabel.setText("Tic-Tac-Toe");                            // Başlangıç yazısı
        textLabel.setOpaque(true);                                   // Arka plan renginin görünmesi için gerekli

        textPanel.setLayout(new BorderLayout());  // Panel düzeni
        textPanel.add(textLabel);                 // Label'ı panel'e ekle
        frame.add(textPanel, BorderLayout.NORTH); // Panel'i pencerenin üst kısmına ekle

        // === OYUN TAHTASI PANELİ ===
        boardPanel.setLayout(new GridLayout(3, 3));  // 3x3 ızgara düzeni
        boardPanel.setBackground(Color.CYAN);        // Arka plan rengi
        frame.add(boardPanel);                       // Panel'i pencerenin merkezine ekle

        // === OYUN TAHTASI BUTONLARINI OLUŞTUR ===
        // İç içe döngülerle 3x3 = 9 buton oluşturuyoruz
        for (int r = 0; r < 3; r++) {           // Satır döngüsü (row)
            for (int c = 0; c < 3; c++) {       // Sütun döngüsü (column)
                JButton cubicle = new JButton();  // Yeni buton oluştur
                board[r][c] = cubicle;            // Butonu diziye kaydet
                boardPanel.add(cubicle);          // Butonu panel'e ekle
                
                // Buton stil ayarları
                Color customBlue = new Color(33, 150, 243);      // Özel mavi renk (RGB değerleri)
                cubicle.setBackground(customBlue);               // Arka plan rengi
                cubicle.setForeground(Color.white);              // Yazı rengi beyaz
                cubicle.setFont(new Font("Arial", Font.BOLD, 120)); // X ve O yazıları için büyük font
                cubicle.setFocusable(false);                     // Buton seçildiğinde çerçeve olmasın

                // === BUTONA TIKLANMA OLAYINI EKLE ===
                cubicle.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Oyun bittiyse hiçbir şey yapma
                        if (gameOver) {
                            return;
                        }
                        
                        // Tıklanan butonu al
                        JButton cubicle = (JButton) e.getSource();
                        
                        // Eğer buton boşsa (üzerinde X veya O yoksa)
                        if (cubicle.getText() == "") {
                            cubicle.setText(currentPlayer);  // Şu anki oyuncunun işaretini koy
                            turns++;                         // Hamle sayısını artır
                            checkWinner();                   // Kazanan var mı kontrol et
                            
                            // Eğer oyun bitmediyse
                            if (!gameOver) {
                                // Sırayı diğer oyuncuya geçir
                                if (currentPlayer == playerX) {
                                    currentPlayer = playerO;  // X'ten O'ya geç
                                } else {
                                    currentPlayer = playerX;  // O'dan X'e geç
                                }
                                // Durum yazısını güncelle
                                textLabel.setText(currentPlayer + "'s turn:");
                            }
                        }
                    }
                });
            }
        }
    }

    /**
     * Kazanan kontrolü yapan metod
     * Yatay, dikey ve çapraz kontroller yapar
     * Ayrıca beraberlik durumunu da kontrol eder
     */
    void checkWinner() {
        // === YATAY (HORIZONTAL) KONTROL ===
        // Her satırı kontrol et
        for (int r = 0; r < 3; r++) {
            // Eğer satırın ilk hücresi boşsa, bu satırı atla
            if (board[r][0].getText() == "") {
                continue;
            }

            // Satırdaki 3 hücre de aynı mı? (XXX veya OOO)
            if (board[r][0].getText() == board[r][1].getText()
                    && board[r][1].getText() == board[r][2].getText()) {
                // Kazanan hücreleri vurgula
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;              // Oyunu bitir
                showGameOverDialog();         // Bitiş dialogunu göster
                return;
            }
        }
        
        // === DİKEY (VERTICAL) KONTROL ===
        // Her sütunu kontrol et
        for (int c = 0; c < 3; c++) {
            // Eğer sütunun ilk hücresi boşsa, bu sütunu atla
            if (board[0][c].getText() == "") {
                continue;
            }

            // Sütundaki 3 hücre de aynı mı?
            if (board[0][c].getText() == board[1][c].getText()
                    && board[1][c].getText() == board[2][c].getText()) {
                // Kazanan hücreleri vurgula
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;              // Oyunu bitir
                showGameOverDialog();         // Bitiş dialogunu göster
                return;
            }
        }
        
        // === SOL ÇAPRAZ (LEFT DIAGONAL) KONTROL ===
        // Sol üstten sağ alta: [0,0], [1,1], [2,2]
        if (board[0][0].getText() == board[1][1].getText()
                && board[1][1].getText() == board[2][2].getText()
                && board[0][0].getText() != "") {
            // Kazanan hücreleri vurgula
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;              // Oyunu bitir
            showGameOverDialog();         // Bitiş dialogunu göster
            return;
        }
        
        // === SAĞ ÇAPRAZ (RIGHT DIAGONAL) KONTROL ===
        // Sağ üstten sol alta: [0,2], [1,1], [2,0]
        if (board[0][2].getText() == board[1][1].getText()
                && board[1][1].getText() == board[2][0].getText()
                && board[0][2].getText() != "") {
            // Kazanan hücreleri tek tek vurgula
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;              // Oyunu bitir
            showGameOverDialog();         // Bitiş dialogunu göster
            return;
        }
        
        // === BERABERLİK KONTROLÜ ===
        // 9 hamle yapıldıysa ve kazanan yoksa berabere
        if (turns == 9) {
            // Tüm hücreleri beraberlik rengiyle boya
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;              // Oyunu bitir
            showGameOverDialog();         // Bitiş dialogunu göster
            return;
        }
    }

    /**
     * Oyun bittiğinde gösterilecek dialog penceresini oluşturur
     * Restart ve Exit butonlarını içerir
     */
    void showGameOverDialog() {
        // Modal dialog oluştur (kullanıcı bu pencereyi kapatana kadar diğer işlemler yapılamaz)
        JDialog dialog = new JDialog(frame, "Game Over", true);
        dialog.setLayout(new GridLayout(2, 1, 10, 10));  // 2 satır, 1 sütun, aralarında 10px boşluk
        dialog.setSize(250, 150);                         // Dialog boyutu
        dialog.setLocationRelativeTo(frame);              // Ana pencerenin ortasında göster
        
        // === RESTART BUTONU ===
        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.BOLD, 20));      // Font ayarları
        restartButton.setBackground(new Color(76, 175, 80));          // Yeşil arka plan
        restartButton.setForeground(Color.WHITE);                     // Beyaz yazı
        restartButton.setFocusable(false);                            // Seçim çerçevesi olmasın
        
        // Restart butonuna tıklanınca
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();  // Dialog'u kapat
                restartGame();     // Oyunu yeniden başlat
            }
        });
        
        // === EXIT BUTONU ===
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));         // Font ayarları
        exitButton.setBackground(new Color(244, 67, 54));             // Kırmızı arka plan
        exitButton.setForeground(Color.WHITE);                        // Beyaz yazı
        exitButton.setFocusable(false);                               // Seçim çerçevesi olmasın
        
        // Exit butonuna tıklanınca
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Programı kapat (çıkış kodu 0 = normal çıkış)
            }
        });
        
        // Butonları dialog'a ekle
        dialog.add(restartButton);
        dialog.add(exitButton);
        dialog.setVisible(true);  // Dialog'u göster
    }
    
    /**
     * Oyunu yeniden başlatır
     * Tüm değişkenleri ve tahtayı sıfırlar
     */
    void restartGame() {
        // === OYUN DURUMUNU SIFIRLA ===
        gameOver = false;           // Oyun devam ediyor
        currentPlayer = playerX;    // X ile başla
        turns = 0;                  // Hamle sayısını sıfırla
        
        // === TAHTAYI TEMİZLE ===
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");  // Hücredeki yazıyı sil (X veya O'yu kaldır)
                
                // Hücre renklerini başlangıç haline getir
                Color customBlue = new Color(33, 150, 243);  // Özel mavi renk
                board[r][c].setBackground(customBlue);       // Arka plan maviye dön
                board[r][c].setForeground(Color.white);      // Yazı rengi beyaz
            }
        }
        
        // === BAŞLIK YAZISINI GÜNCELLE ===
        textLabel.setText("Tic-Tac-Toe");       // Başlık yazısını varsayılana dön
        textLabel.setBackground(Color.CYAN);    // Başlık arka planı cyan
    }

    /**
     * Kazanan hücrenin görünümünü ayarlar
     * @param cubicle Kazanan hücre butonu
     */
    void setWinner(JButton cubicle) {
        cubicle.setForeground(Color.green);     // Yazıyı yeşil yap (kazanan işareti)
        cubicle.setBackground(Color.CYAN);      // Arka planı cyan yap
        textLabel.setText(currentPlayer + " is the winner!");  // Kazanan mesajını göster
    }

    /**
     * Beraberlik durumunda hücrenin görünümünü ayarlar
     * @param cubicle Beraberlik hücre butonu
     */
    void setTie(JButton cubicle) {
        cubicle.setForeground(Color.orange);      // Yazıyı turuncu yap
        cubicle.setBackground(Color.LIGHT_GRAY);  // Arka planı açık gri yap
        textLabel.setText("Tie!");                // Beraberlik mesajını göster
    }
}
