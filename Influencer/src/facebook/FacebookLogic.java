package facebook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



import views.InfluencerModel;

public class FacebookLogic {

	/*public static List<InfluencerModel> getListOfInfluencers() {

		ArrayList<InfluencerModel> list= new ArrayList<>();


		FacebookClient facebookClient = new DefaultFacebookClient(Constants.MY_ACCESS_TOKEN, Constants.MY_APP_SECRET,Version.LATEST);

		//User user = facebookClient.fetchObject("me", User.class,Parameter.with("fields", "id,first_name,last_name,link"));


		Connection<User> publicSearch =facebookClient.fetchConnection("search", User.class, Parameter.with("q", "coffee"), Parameter.with("type", "user"));

		for (List<User> userListFeeded : publicSearch) {

			// Iterate over the list of contained data 
			// to access the individual object
			for (User user : userListFeeded) {
				InfluencerModel influencer= new InfluencerModel();
				influencer.setNome(user.getFirstName());
				influencer.setCognome(user.getLastName());
				influencer.setLinkProfilo(user.getLink());
				influencer.setScore(12.5f);
				list.add(influencer);
			}
		}

		return list;
	}*/

	public static List<InfluencerModel> getListOfInfluencers(String searchQuery) {
		
		ArrayList<InfluencerModel> list= new ArrayList<>();

		try {
			
			Document doc= Jsoup.connect("https://www.facebook.com") 
					   .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
		               .referrer("http://www.google.com") 
		               .timeout(1000*5) //it's in milliseconds, so this means 5 seconds.              
		               .get();

		
			Elements pages = doc.getElementsByClass(Constants.FACEBOOK_SEARCH_PAGINE_FIRST_LEVEL);
			
			for (Element page: pages) {
				
				InfluencerModel influencer = new InfluencerModel();
				
				list.add(influencer);
				
				influencer.setNome(page.getElementsByClass(Constants.FACEBOOK_SEARCH_NOME_PAGINE).first().select("span").text());
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();

			return null;
		}
		
		
		
		return list;

		
		
	}

}
