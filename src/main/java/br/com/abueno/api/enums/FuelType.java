package br.com.abueno.api.enums;

public enum FuelType {
	
	GAS(1, "GASOLINA", "GAS_KEY"), 
	ALCOHOL(2, "Alcohol", "ALCOHOL_KEY"), 
	FLEX(3, "FLEX", "FLEX_KEY");

	private int id;
	private String name;
	private String nameKey;

	FuelType(int id, String name, String nameKey) {
		this.id = id;
		this.name = name;
		this.nameKey = nameKey;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNameKey() {
		return nameKey;
	}

}
