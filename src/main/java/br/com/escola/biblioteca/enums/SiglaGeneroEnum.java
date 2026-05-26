package br.com.escola.biblioteca.enums;


public enum SiglaGeneroEnum {
	ROMANCE("ROM"),
    DRAMA("DRM"),
    FICCAO("FIC"),
    TERROR("TER"),
    BIOGRAFIA("BIO");

    private final String sigla;

    SiglaGeneroEnum(String sigla) {
        this.sigla = sigla;
    }
    
	public String getSigla() {
		return this.sigla;
	}
}
