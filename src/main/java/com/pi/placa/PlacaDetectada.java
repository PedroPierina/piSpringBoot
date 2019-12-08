package com.pi.placa;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "placaDetectada")
public class PlacaDetectada {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty(message = "Placa � um campo obrigatorio")
	private String placa;
	@PositiveOrZero(message = "Codigo da Infra��o � um campo obrigatorio")
	private int codInfra;
	@NotEmpty(message = "Descri��o da Infra��o � um campo obrigatorio")
	private String desc;
	
	private String longitude;
	
	private String latitude;
	
	@Lob
	private byte[] base64Image;
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	
	public byte[] getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(byte[] string) {
		this.base64Image = string;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public int getCodInfra() {
		return codInfra;
	}
	public void setCodInfra(int codInfra) {
		this.codInfra = codInfra;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return "PlacaDetectada [id=" + id + ", placa=" + placa + ", codInfra=" + codInfra + ", desc=" + desc
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", base64Image="
				+ Arrays.toString(base64Image) + "]";
	}
	
}
