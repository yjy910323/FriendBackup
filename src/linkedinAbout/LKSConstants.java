package linkedinAbout;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.oauth.OAuthService;

public class LKSConstants {
	//linkedin service
	public static final  OAuthService service = new ServiceBuilder().provider(LinkedInApi.class).apiKey("a2f5jedymg68").apiSecret("heJkIFSpkO2ZVjlo").scope("r_fullprofile,r_emailaddress,r_network,w_messages").build();
	
}
