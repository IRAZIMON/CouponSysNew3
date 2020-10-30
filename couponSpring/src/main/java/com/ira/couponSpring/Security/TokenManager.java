package com.ira.couponSpring.Security;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ira.couponSpring.Exceptions.TokenNotExistException;
import com.ira.couponSpring.Facade.ClientFacade;

import lombok.Data;


@Component
@Data
public class TokenManager {

	
	
	private Map<String,UserData> map= new HashMap<String, UserData>() ;
	
	
	public String addToken(ClientFacade facade) {
		System.out.println("start tokenmanager  ");
		String token=UUID.randomUUID().toString();
	    map.put(token,new UserData(facade,System.currentTimeMillis()));
		return token;
		
	}
	
	
		public long getTimeStamp(String token) {
		return map.getOrDefault(token, null).getTimestamp();
	}
	
	
	public ClientFacade getClientFacade (String token) {
		return map.getOrDefault(token, null).getFacade();
		
	}
	
	
	public boolean isTokenExist(String token) throws TokenNotExistException {
		UserData userData=map.get(token);
		if(userData!=null) {
		return true; 
	}
	
		throw new TokenNotExistException("Sorry...but something went wrong....");
		
	}

	public void removeExpiredToken() {
		for (Map.Entry<String, UserData> entry : map.entrySet()) {
			UserData userData = entry.getValue();
			Date now = new Date(System.currentTimeMillis()+ TimeUnit.MINUTES.toMillis(30));
			Date other = new Date(userData.getTimestamp());
			if(now.before(other)) {
				String token = entry.getKey();
				map.remove(token);
			}
		}
		
	}


	public void removeToken(String token) {
		map.remove(token);
		System.out.println("token"+token +"was removed");
		
		System.out.println("end tokenmanager  ");
	}


	

}
