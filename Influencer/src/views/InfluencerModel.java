package views;


public class InfluencerModel {

	private String nome;
	private String cognome;
	private String linkProfilo;
	private float score;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getLinkProfilo() {
		return linkProfilo;
	}
	public void setLinkProfilo(String linkProfilo) {
		this.linkProfilo = linkProfilo;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "   "+nome+"   "+score;
	}
	
}
