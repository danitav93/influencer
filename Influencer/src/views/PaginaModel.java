package views;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import Utility.ConfigFileNotFoundException;
import Utility.PropertiesNotFoundException;
import Utility.PropertiesService;

public class PaginaModel implements Comparable<PaginaModel>{

	private int miPiacePagina=0;
	private int followersPagina=0;
	
	private int numeroPostUltimoPeriodo=0;
	private int condivisioniPostUltimoPeriodo=0;
	private int commentiPostUltimoPeriodo=0;
	private int numeroLikePostUltimoPeriodo=0;
	private int visualizzazioniPostUltimoPeriodo=0;

	private float mediaPostGiornaliera=0;
	private float mediaCondivisioni=0;
	private float mediaLike=0;
	private float mediaCommenti=0;
	private float mediaVisualizzazioni=0;
	
	private int ordineFacebook=0;
	
	private float finalScore=0;

	
	private String nomePagina;
	private String url;
	
	private String luogo=" ";
	
	
	
	
	public String getLuogo() {
		return luogo;
	}
	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}
	public String getNomePagina() {
		return nomePagina;
	}
	public void setNomePagina(String nomePagina) {
		this.nomePagina = nomePagina;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public float getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(float finalScore) {
		this.finalScore = finalScore;
	}
	public float getMediaVisualizzazioni() {
		return mediaVisualizzazioni;
	}
	public void setMediaVisualizzazioni(float mediaVisualizzazioni) {
		this.mediaVisualizzazioni = mediaVisualizzazioni;
	}
	public int getMiPiacePagina() {
		return miPiacePagina;
	}
	public void setMiPiacePagina(int miPiacePagina) {
		this.miPiacePagina = miPiacePagina;
	}
	public int getFollowersPagina() {
		return followersPagina;
	}
	public void setFollowersPagina(int followersPagina) {
		this.followersPagina = followersPagina;
	}
	public int getNumeroPostUltimoPeriodo() {
		return numeroPostUltimoPeriodo;
	}
	public void setNumeroPostUltimoPeriodo(int numeroPostUltimoPeriodo) {
		this.numeroPostUltimoPeriodo = numeroPostUltimoPeriodo;
	}
	public int getCondivisioniPostUltimoPeriodo() {
		return condivisioniPostUltimoPeriodo;
	}
	public void setCondivisioniPostUltimoPeriodo(int condivisioniPostUltimoPeriodo) {
		this.condivisioniPostUltimoPeriodo = condivisioniPostUltimoPeriodo;
	}
	public int getNumeroLikePostUltimoPeriodo() {
		return numeroLikePostUltimoPeriodo;
	}
	public void setNumeroLikePostUltimoPeriodo(int numeroLikePostUltimoPeriodo) {
		this.numeroLikePostUltimoPeriodo = numeroLikePostUltimoPeriodo;
	}
	public float getMediaPostGiornaliera() {
		return mediaPostGiornaliera;
	}
	public void setMediaPostGiornaliera(float mediaPostGiornaliera) {
		this.mediaPostGiornaliera = mediaPostGiornaliera;
	}
	public float getMediaCondivisioni() {
		return mediaCondivisioni;
	}
	public void setMediaCondivisioni(float mediaCondivisioni) {
		this.mediaCondivisioni = mediaCondivisioni;
	}
	public float getMediaLike() {
		return mediaLike;
	}
	public void setMediaLike(float mediaLike) {
		this.mediaLike = mediaLike;
	}
	public int getCommentiPostUltimoPeriodo() {
		return commentiPostUltimoPeriodo;
	}
	public void setCommentiPostUltimoPeriodo(int commentiPostUltimoPeriodo) {
		this.commentiPostUltimoPeriodo = commentiPostUltimoPeriodo;
	}
	public int getVisualizzazioniPostUltimoPeriodo() {
		return visualizzazioniPostUltimoPeriodo;
	}
	public void setVisualizzazioniPostUltimoPeriodo(int visualizzazioniPostUltimoPeriodo) {
		this.visualizzazioniPostUltimoPeriodo = visualizzazioniPostUltimoPeriodo;
	}
	public float getMediaCommenti() {
		return mediaCommenti;
	}
	public void setMediaCommenti(float mediaCommenti) {
		this.mediaCommenti = mediaCommenti;
	}
	
	@Override
	public int compareTo(PaginaModel o) {
		return Float.compare(this.finalScore, o.finalScore);
	}
	
	
	public String toString() {
		return nomePagina;
	}
	
	public String toExportString() throws PropertiesNotFoundException, ConfigFileNotFoundException  {
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar1=Calendar.getInstance(TimeZone.getDefault());
		Calendar calendar2=Calendar.getInstance(TimeZone.getDefault());
		calendar2.add(Calendar.DAY_OF_MONTH, - PropertiesService.getIntProperty("daysCheck"));
		
		String  toReturn="Powered by Nodelab Developement Team\n";
		toReturn=toReturn+"Export dati pagina\n\n";
		toReturn=toReturn+"Nome: "+nomePagina+"\n";
		toReturn=toReturn+"Url: "+url+"\n";
		toReturn=toReturn+"Total score: "+finalScore+"\n";
		toReturn=toReturn+"Media commenti: "+mediaCommenti+"\n";
		toReturn=toReturn+"Media condivisioni: "+mediaCondivisioni+"\n";
		toReturn=toReturn+"Media like: "+mediaLike+"\n";
		toReturn=toReturn+"Media visualizzazioni: "+mediaVisualizzazioni+"\n";
		toReturn=toReturn+"Mi piace: "+miPiacePagina+"\n";
		toReturn=toReturn+"Followers: "+followersPagina+"\n";
		toReturn=toReturn+"Media post al giorno: "+mediaPostGiornaliera+"\n";
		toReturn=toReturn+"Ordine facebook: "+ordineFacebook+"\n";
		toReturn=toReturn+"Luogo: "+luogo+"\n";
		toReturn=toReturn+"\nPeriodo di riferimento: "+format1.format(calendar2.getTime())+"  -  "+format1.format(calendar1.getTime());

		return toReturn;
	}
	public int getOrdineFacebook() {
		return ordineFacebook;
	}
	public void setOrdineFacebook(int ordineFacebook) {
		this.ordineFacebook = ordineFacebook;
	}
	
	
	
	
	
	
}
