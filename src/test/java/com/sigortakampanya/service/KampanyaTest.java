package com.sigortakampanya.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.sigortakampanya.enums.KampanyaKategori;
import com.sigortakampanya.models.Kampanya;
import com.sigortakampanya.repository.KampanyaRepository;

@SpringBootTest
@ContextConfiguration
public class KampanyaTest {
	
	@InjectMocks
	private KampanyaService kampanyaService;
	
	@Mock
	private KampanyaRepository kampanyaRepository;
	
	private Kampanya hayatSigortasiOrnek() {
		Kampanya kampanya=new Kampanya();
		kampanya.setKampanyaBasligi("Hayat sigortası");
		kampanya.setKampanyaDetayAciklamasi("Hayat sigortasının bu sefer olan kampanyası herkesi sasırtacak.");
		kampanya.setKampanyaKategorisi(KampanyaKategori.HAYATSIGORTASI);
		return kampanya;
	}
	
	private Kampanya digerSigortalarOrnek() {
		Kampanya kampanya=new Kampanya();
		kampanya.setKampanyaBasligi("TSS sigortası");
		kampanya.setKampanyaDetayAciklamasi("TSS sigortasının bu sefer olan kampanyası herkesi sasırtacak emin olabilirsiniz.");
		kampanya.setKampanyaKategorisi(KampanyaKategori.TSS);
		kampanya.setId(21241);
		return kampanya;
	}
	
	private List<Kampanya> kampanyaListesiOlustur() {
		Kampanya kampanya=new Kampanya();
		kampanya.setKampanyaBasligi("TSS sigortası");
		kampanya.setKampanyaDetayAciklamasi("TSS sigortasının bu sefer olan kampanyası herkesi sasırtacak emin olabilirsiniz.");
		kampanya.setKampanyaKategorisi(KampanyaKategori.OSS);
		kampanya.setStatu("Onay Bekliyor");
		List<Kampanya> kampanyaListesi=new ArrayList<Kampanya>();
		kampanyaListesi.add(kampanya);
		
		return kampanyaListesi;
	}
	
	@Test
	void tum_kampanya_ornekleri() {
		
		List <Kampanya> kampanyaListesi = kampanyaListesiOlustur();
		
		Mockito.when(kampanyaRepository.findAll()).thenReturn(kampanyaListesi);
		
		
		
		List<Kampanya> response=kampanyaService.tümKampanyalar();
		
		verify(kampanyaRepository,times(1)).findAll();
		
		assertThat(response).isEqualTo(kampanyaListesi);
		
		
		
	}
	
	@Test
	void kampanya_aktivasyon_ornegi() {
		//Kampanya hayatSigortası=hayatSigortasiOrnek();
		Kampanya digerSigortasi=digerSigortalarOrnek();
		Kampanya digerSigortasiCikis=digerSigortalarOrnek();
		digerSigortasi.setStatu("Aktif");
		digerSigortasiCikis.setStatu("Onay Bekliyor");
		digerSigortasiCikis.setId(1421241);
		
		Mockito.when(kampanyaRepository.save(Mockito.any())).thenReturn(digerSigortasi);
		// Burası itibari ile tam bitmedi.
		
		Kampanya response= kampanyaService.kampanyaAktivasyonu(digerSigortasiCikis.getId(), 1);
		
		verify(kampanyaRepository,times(1)).save(Mockito.any());
		
		assertThat(response.getStatu()).isEqualTo(digerSigortasi.getStatu());
		
		
		
	}
	
	@Test
	void kampanya_olusturma_ornegi_hayat_sigortası() {
		Kampanya hayatSigortasi=hayatSigortasiOrnek();
		
		Mockito.when(kampanyaRepository.save(Mockito.any())).thenReturn(hayatSigortasi);
		
		Kampanya response=kampanyaService.kampanyaOlustur(hayatSigortasi.getKampanyaBasligi(), hayatSigortasi.getKampanyaDetayAciklamasi(), hayatSigortasi.getKampanyaKategorisi());
		
		verify(kampanyaRepository,times(1)).save(Mockito.any());
		
		assertThat(response).isEqualTo(hayatSigortasi);
		
	}
	
	@Test
	void kampanya_olusturma_ornegi_diger_sigortası() {
		Kampanya digerSigortasi=digerSigortalarOrnek();
		
		Mockito.when(kampanyaRepository.save(Mockito.any())).thenReturn(digerSigortasi);
		
		Kampanya response=kampanyaService.kampanyaOlustur(digerSigortasi.getKampanyaBasligi(), digerSigortasi.getKampanyaDetayAciklamasi(), digerSigortasi.getKampanyaKategorisi());
		
		verify(kampanyaRepository,times(1)).save(Mockito.any());
		
		assertThat(response).isEqualTo(digerSigortasi);
		
	}

}
