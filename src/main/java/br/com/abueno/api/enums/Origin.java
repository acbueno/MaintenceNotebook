package br.com.abueno.api.enums;

public enum Origin {
	BRAZIL("Brasil"),
	USA("Estados Unidos"),
	JAPON("Japão"),
	ITALY("Italia");
	
	private String country;
	
	Origin(String country) {
		this.country = country;
	}

}
