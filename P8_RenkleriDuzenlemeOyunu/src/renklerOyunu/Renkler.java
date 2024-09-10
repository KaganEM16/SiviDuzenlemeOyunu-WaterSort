package renklerOyunu;

import java.awt.Color;

public class Renkler 
{
	public int renkSayisi;
	Color renk;	
	
	public Renkler(Color renk, int renkSayisi)
	{
		this.renk = renk;
		this.renkSayisi = renkSayisi;
	}
	
	public void kullan(int konum) {
		AnaOyun.butonlar[konum].setBackground(renk);
		renkSayisi--;
	}

}
