package com.ira.couponSpring.Security;

import com.ira.couponSpring.Facade.ClientFacade;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
	
	
	private ClientFacade facade;
	private long timestamp;
	
	
}
