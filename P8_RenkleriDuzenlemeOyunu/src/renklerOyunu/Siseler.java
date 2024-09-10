package renklerOyunu;

import java.awt.Color;
import java.util.Random;

public class Siseler
{
	int boy, konum;
	static int siseSayisi = 0;
	
	public Siseler(int boy, int konum) 
	{
		this.boy = boy;
		this.konum = konum;		
	}
	
	public void olustur()
	{		
		int k = konum;
		Random random = new Random();		
		
		for(int i=0 ; i<boy ; i++)
		{
			if(siseSayisi < 6) {
				int r = random.nextInt(AnaOyun.renkler.length);
				if(AnaOyun.renkler[r].renkSayisi != 0) {					
					AnaOyun.renkler[r].kullan(k);
				}else {
					int s = 0;
					while(true) {						
						if(AnaOyun.renkler[s].renkSayisi != 0) {
							AnaOyun.renkler[s].kullan(k);
							break;
						}else 
							s++;
					}
				}
			}else {
				AnaOyun.butonlar[k].setBackground(Color.WHITE);				
			}
			k += 9;
		}
		
		siseSayisi++;
	}
	
	public boolean kontrol() 
	{
		int k = konum, sayac = 0;
		Color renk = AnaOyun.butonlar[k].getBackground();
		k += 9;
		
		for(int i=0 ; i<3 ; i++) {
			if(AnaOyun.butonlar[k].getBackground() == renk) 
				sayac++;
			k += 9;
		}
		
		if(sayac == 3)
			return true;
		else
			return false;
	}
}
