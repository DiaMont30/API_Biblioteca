package br.com.escola.biblioteca.enums;


public enum SiglaGenero {
	ROMANCE("ROM"),
    DRAMA("DRM"),
    FICCAO("FIC"),
    TERROR("TER"),
    BIOGRAFIA("BIO");

    private final String sigla;

    SiglaGenero(String sigla) {
        this.sigla = sigla;
    }
    
	public String getSiglaAbreviada() {
		return this.sigla;
	}
}
