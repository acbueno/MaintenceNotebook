package br.com.abueno.api.enums;

public enum BrandName {
	FORD("Ford"),
	FIAT("Fiat"),
	GM("Chervrolet"),
	NISSAN("Nissan"),
	HONDA("Honda");
	
    private String originBrand;

     private BrandName(String originBrand) {
    	 this.originBrand = originBrand;
	}

    public String getOriginBrand() {
        return originBrand;
    }

}
