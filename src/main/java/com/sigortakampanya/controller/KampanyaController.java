package com.sigortakampanya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sigortakampanya.helper.StatuHelper;
import com.sigortakampanya.models.Kampanya;
import com.sigortakampanya.service.KampanyaService;

@RestController
@RequestMapping(value = "/kampanya")
public class KampanyaController {
	
	@Autowired
	private KampanyaService kampanyaService;
	
	@PostMapping
	@RequestMapping(value = "/kampanyagiris")
	public Kampanya kampanyaGiris(@RequestBody Kampanya kampanya) {
		return kampanyaService.kampanyaOlustur(kampanya.getKampanyaBasligi(), kampanya.getKampanyaDetayAciklamasi(), kampanya.getKampanyaKategorisi());
		
	}
	
	@PostMapping
	@RequestMapping(value = "/kampanyastatudegistir")
	public Kampanya kampayaAktivasyon(@RequestBody StatuHelper helper) {
		return kampanyaService.kampanyaAktivasyonu(Integer.parseInt(helper.getId()), Integer.parseInt(helper.getStatu()));
	}
	
	@GetMapping
	@RequestMapping(value = "/kampanyastatu")
	public String kampanyaStatu() {
		return kampanyaService.kampanyaDurum();
	}
	
	@GetMapping
	@RequestMapping(value = "/tumkampanyalar")
	public List<Kampanya> tumKampanya() {
		return kampanyaService.tümKampanyalar();
	}
	

}
