package renklerOyunu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnaOyun extends JFrame implements ActionListener
{
	JPanel[] paneller;
	static JButton[] butonlar;
	JButton[] secenekler;
	Siseler[] siseler;
	static Renkler[] renkler;
	boolean basildi;
	Color secilenRenk;
	JButton secilenButon;
	Color[] kullanilacakRenkler = {Color.RED, Color.BLUE, Color.GREEN, Color.PINK, Color.YELLOW, Color.CYAN};
	int[] konumlar = {10, 12, 14, 16, 55, 57, 59, 61};
	boolean oyunBitti;
	
	public AnaOyun(int en, int boy)
	{
		super("Renkleri Düzenleme Oyunu");
		this.setSize(new Dimension(en, boy));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		
		paneller = new JPanel[2];
		for(int i=0 ; i<paneller.length ; i++) {
			paneller[i] = new JPanel();
			paneller[i].setSize(en, (i==0)? 682 : boy);
			paneller[i].setLayout((i==0)? new GridLayout(11, 9) : new FlowLayout());
			paneller[i].setBounds(0, 682*i, en-10, (i==0)? 682 : boy-682);
			this.add(paneller[i]);
		}
		paneller[1].setBackground(Color.BLACK);
		
		butonlar = new JButton[99];
		for(int i=0 ; i<butonlar.length ; i++) {
			butonlar[i] = new JButton();
			butonlar[i].addActionListener(this);
			butonlar[i].setBackground(Color.DARK_GRAY);
			butonlar[i].setBorderPainted(false);
			paneller[0].add(butonlar[i]);
		}
		
		renkler = new Renkler[kullanilacakRenkler.length];
		for(int i=0 ; i<renkler.length ; i++) {
			renkler[i] = new Renkler(kullanilacakRenkler[i], 4);
		}
		
		siseler = new Siseler[8];
		for(int i=0 ; i<siseler.length ; i++) {
			siseler[i] = new Siseler(4, konumlar[i]);
			siseler[i].olustur();
		}
		
		String[] yazilanlar = {"Yeni Oyun Başlat", "Kontrol Et", "Çıkış Yap"};
		secenekler = new JButton[3];
		for(int i=0 ; i<secenekler.length ; i++) {
			secenekler[i] = new JButton(yazilanlar[i]);
			secenekler[i].addActionListener(this);
			secenekler[i].setBackground(Color.RED);
			secenekler[i].setForeground(Color.WHITE);
			secenekler[i].setPreferredSize(new Dimension(150, 60));
			paneller[1].add(secenekler[i]);
		}		
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton basilan = (JButton)e.getSource();
		
		if(basilan == secenekler[0]) {
			Siseler.siseSayisi = 0;
			for(int i=0 ; i<renkler.length ; i++) {
				renkler[i] = new Renkler(kullanilacakRenkler[i], 4);
			}
			for(int i=0 ; i<siseler.length ; i++) {
				siseler[i] = new Siseler(4, konumlar[i]);
				siseler[i].olustur();
			}
			oyunBitti = false;
		}else if(basilan == secenekler[1]) {
			int sayac = 0;
			for(int i=0 ; i<siseler.length ; i++) {
				if(siseler[i].kontrol())
					sayac++;
			}
			if(sayac == siseler.length) {
				JOptionPane.showMessageDialog(null, "Tebrikler, oyunu başarı ile tamamladınız.");
				oyunBitti = true;
			}else
				JOptionPane.showMessageDialog(null, "Oyunun tamamlanması için tüm şişeler aynı renk ile dolu olmalıdır.");
		}else if(basilan == secenekler[2]) {
			System.exit(JFrame.EXIT_ON_CLOSE);
		}else {
			if(!oyunBitti) {
				if(basildi) {
					if(basilan.getBackground() == Color.WHITE) {					
						if(butonlar[getIndex(basilan)+9].getBackground() == Color.WHITE) {
							JOptionPane.showMessageDialog(null, "Renkleri şişenin yerleştirilebilir en alt konumuna yerleştirebilirsiniz.");
						}else if((butonlar[getIndex(basilan)+9].getBackground() != secilenRenk && butonlar[getIndex(basilan)+9].getBackground() != Color.DARK_GRAY)) {
							JOptionPane.showMessageDialog(null, "Farklı renkleri üst üste konumlandıramazsınız.");
						}else {
							basilan.setBackground(secilenRenk);
							secilenButon.setBackground(Color.WHITE);
							secilenRenk = null;
							secilenButon = null;
							basildi = false;
						}					
					}else {
						if(basilan.getBackground() != Color.DARK_GRAY) {
							secilenRenk = basilan.getBackground();
							secilenButon = basilan;
						}else {
							JOptionPane.showMessageDialog(null, "Renkleri şişelere yerleştirmelisiniz.");
						}
					}
				}else {
					for(int i=0 ; i<kullanilacakRenkler.length ; i++) {
						if(basilan.getBackground() == kullanilacakRenkler[i] && 
								(butonlar[getIndex(basilan)-9].getBackground() == Color.WHITE || butonlar[getIndex(basilan)-9].getBackground() == Color.DARK_GRAY)) {
							secilenRenk = kullanilacakRenkler[i];
							secilenButon = basilan;
							basildi = true;
						}
					}
				}
			}else
				JOptionPane.showMessageDialog(null, "Oyunu tamamladınız yeni oyun başlatmak için aşağıdaki 'Yeni Oyun Başlat' butonuna basabilir veya "
						+ "oyundan çıkmak için 'Çıkış Yap' butonunu kullanabilirsiniz.");
		}
	}
	
	public int getIndex(JButton buton) 
	{
		int k = -1;
		for(int i=0 ; i<butonlar.length ; i++) {
			if(butonlar[i] == buton) {
				k = i;
			}
		}
		return k;
	}
}
