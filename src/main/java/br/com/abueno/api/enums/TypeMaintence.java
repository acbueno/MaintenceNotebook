package br.com.abueno.api.enums;

public enum TypeMaintence {
	PREVENTIVE("preventive"), REPAIRING("Repairing");

	private String typeRepair;

	private TypeMaintence(String typeRepair) {
		this.typeRepair = typeRepair;
	}

	public String getTypeRepair() {
		return typeRepair;
	}

	public void setTypeRepair(String typeRepair) {
		this.typeRepair = typeRepair;
	}
	

}
