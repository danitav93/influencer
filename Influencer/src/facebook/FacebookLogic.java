package facebook;

import java.util.ArrayList;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.User;

import views.InfluencerModel;

public class FacebookLogic {

	public static List<InfluencerModel> getListOfInfluencers() {

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
	}


}
