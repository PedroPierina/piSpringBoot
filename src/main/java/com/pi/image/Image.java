package com.pi.image;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "placa")
public class Image {
	@Id
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	private String fileName;

	private String fileType;

	@Lob()
//	private byte[] data;
	private Blob data;

	public Image() {

	    }

	public Image(String fileName, String fileType, Blob data) {
	        this.fileName = fileName;
	        this.fileType = fileType;
	        this.data = data;
	    }
	
	
	public String getId() {
		return id;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Blob getData() {
		return data;
	}

	public void setData(Blob data) {
		this.data = data;
	}
	
	
}
