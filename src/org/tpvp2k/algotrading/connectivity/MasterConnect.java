package org.tpvp2k.algotrading.connectivity;

import org.apache.http.HttpHost;

import com.rainmatter.kiteconnect.KiteConnect;
import com.rainmatter.kitehttp.SessionExpiryHook;
import com.rainmatter.kitehttp.exceptions.KiteException;
import com.rainmatter.models.UserModel;

public class MasterConnect {
	private KiteConnect kConn = null;

	public MasterConnect() {

		try{
			kConn = new KiteConnect("Yxxxx");

			// set userId
			kConn.setUserId("xxxMMx");

			//set proxy is optional, if you want to set proxy.
			kConn.setProxy(new HttpHost("host_name"));

			// Get login url
			String url = kConn.getLoginUrl();
			System.out.println(url);
			
			// Set session expiry callback.
			kConn.registerHook(new SessionExpiryHook() {

				public void sessionExpired() {
					System.out.println("session expired");
				}

			});

			// Set request token and public token which are obtained from login process.
			UserModel userModel =  kConn.requestAccessToken("xxREQxxx", "xxTOKxx");

			kConn.setAccessToken(userModel.accessToken);
			kConn.setPublicToken(userModel.publicToken);

		}catch(KiteException ex){
			kConn = null;
			System.out.println(ex);
			System.out.println("Connection not established");
			
		}

	}
	public KiteConnect getkConn() {
		if(null == kConn){
			System.out.println("Connection not established");
			System.exit(-100);
		}
		return kConn;
	}

}
