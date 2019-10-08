package com.pi.placa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
		return "Placa [id=" + id + ", placa=" + placa + ", codInfra=" + codInfra + ", desc=" + desc + "]";
	}
	
	
}
