package com.sigortakampanya.models;

import com.sigortakampanya.enums.KampanyaKategori;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="kampanya")
public class Kampanya {
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private int id;
	@Column
	@Size(min=10,max=50)
	private String kampanyaBasligi;
	@Column
	@Size(min=20,max=200)
	private String kampanyaDetayAciklamasi;
	@Column
	@Enumerated(EnumType.ORDINAL)
	private KampanyaKategori kampanyaKategorisi;
	@Column
	private String statu;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKampanyaBasligi() {
		return kampanyaBasligi;
	}
	public void setKampanyaBasligi(String kampanyaBasligi) {
		this.kampanyaBasligi = kampanyaBasligi;
	}
	public String getKampanyaDetayAciklamasi() {
		return kampanyaDetayAciklamasi;
	}
	public void setKampanyaDetayAciklamasi(String kampanyaDetayAciklamasi) {
		this.kampanyaDetayAciklamasi = kampanyaDetayAciklamasi;
	}
	public KampanyaKategori getKampanyaKategorisi() {
		return kampanyaKategorisi;
	}
	public void setKampanyaKategorisi(KampanyaKategori kampanyaKategorisi) {
		this.kampanyaKategorisi = kampanyaKategorisi;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	
	
	
	
	
	

}
