package com.sigortakampanya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigortakampanya.enums.KampanyaKategori;
import com.sigortakampanya.models.Kampanya;
import com.sigortakampanya.repository.KampanyaRepository;

@Service
public class KampanyaService {
	
	@Autowired
	private KampanyaRepository kampanyaRepository;
	
	
	public Kampanya kampanyaOlustur(String kampanyaAdı, String kampanyaDetay, KampanyaKategori kampanyaKategori) {
		// Bu fonksiyon öncelikle kampanya değerlerini alır ve yeni obje oluşturur.
		// Oluşturulan kampanyanın sigorta tipine bağlı olarak kampanya statüsü belirlenir.
		Kampanya kampanya =new Kampanya();
		kampanya.setKampanyaBasligi(kampanyaAdı);
		kampanya.setKampanyaDetayAciklamasi(kampanyaDetay);
		kampanya.setKampanyaKategorisi(kampanyaKategori);
		if(kampanyaKategori.equals(KampanyaKategori.HAYATSIGORTASI)) {
			// Hayat sigortası direkt olarak aktif olarak başlar.
			kampanya.setStatu("Aktif");
			List<Kampanya> tümKampanyalar=kampanyaRepository.findAll();
			for(Kampanya holder: tümKampanyalar) {
				// Eğer oluşturulan kampanya hali hazırda kampanya listesinde var ise statü mükerrer olur.
				if(kampanya.getKampanyaBasligi().equals(holder.getKampanyaBasligi())&&
						kampanya.getKampanyaDetayAciklamasi().equals(holder.getKampanyaDetayAciklamasi())&&
						kampanya.getKampanyaBasligi().equals(holder.getKampanyaBasligi())) {
					kampanya.setStatu("Mükerrer");
					break;
				}
			}
			
			return kampanyaRepository.save(kampanya);
			
		}
		// Hayat sigortası dışındaki sigortalar onay bekliyor statüsüne geçer.
		kampanya.setStatu("Onay Bekliyor");
		List<Kampanya> tümKampanyalar=kampanyaRepository.findAll();
		for(Kampanya holder: tümKampanyalar) {
			if(kampanya.getKampanyaBasligi().equals(holder.getKampanyaBasligi())&&
					kampanya.getKampanyaDetayAciklamasi().equals(holder.getKampanyaDetayAciklamasi())&&
					kampanya.getKampanyaBasligi().equals(holder.getKampanyaBasligi())) {
				kampanya.setStatu("Mükerrer");
				break;
			}
		}
		
		return kampanyaRepository.save(kampanya);
	}
	
	public Kampanya kampanyaAktivasyonu(int id, int statuNo) {
		// Bu fonksiyon kampanya id'si ve ve bir sayı alır.
		// Alınan sayı eğer 1 ise kampanya aktive edilir, eğer hali hazırda aktif ise boş dönecektir.
		// Alınan sayı eğer 2 ise kampanya deaktive edilir, eğer hali hazırda aktif ise boş dönecektir.
		Kampanya kampanya=(Kampanya) kampanyaRepository.findById(id).get();
		// Kampanya Mükerrer ise değiştirilmeden boş dönecek.
		if(kampanya.getStatu().equals("Mükerrer")) {
			return null;
		}
		else if(statuNo==1) {
			if(kampanya.getStatu().equals("Aktif")) {
				return null;
			}
			kampanya.setStatu("Aktif");
			return kampanyaRepository.save(kampanya);
			
		}
		else if(statuNo==2) {
			if(kampanya.getStatu().equals("Deaktif")) {
				return null;
			}
			kampanya.setStatu("Deaktif");
			return kampanyaRepository.save(kampanya);
		}
		return null;
	}
	
	
	public String kampanyaDurum() {
		// Tüm kampanyaların statüsünü çıkartan fonksiyon.
		int aktif=0;
		int deaktif=0;
		int onayBekliyor=0;
		List<Kampanya> tümKampanyalar=kampanyaRepository.findAll();
		for(Kampanya holder: tümKampanyalar) {
			if(holder.getStatu().equals("Aktif")) {
				aktif++;
			}
			else if(holder.getStatu().equals("Deaktif")) {
				deaktif++;
			}
			else if(holder.getStatu().equals("Onay Bekliyor")) {
				onayBekliyor++;
			}
			
		}
		
		return "Aktif: "+aktif+", Deaktif: "+deaktif+", Onay Bekliyor: "+onayBekliyor;
		
	}
	
	public List<Kampanya> tümKampanyalar(){
		return kampanyaRepository.findAll();
	}

}
