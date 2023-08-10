package com.vivek.keycloak.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.vivek.keycloak.entity.Auth;
import com.vivek.keycloak.entity.User;

@Service
public class UserService {
	
	public ResponseEntity<String> getToken(Auth auth) {
		 String tokenUrl = "http://localhost:8080/realms/realm3/protocol/openid-connect/token";

	        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	        params.add("grant_type", auth.getGrant_type());
	        params.add("client_id", auth.getClient_id());
	        params.add("client_secret", auth.getClient_secret());

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

	        RestTemplate restTemplate = new RestTemplate();

	        ResponseEntity<String> response = restTemplate.exchange(
	            tokenUrl,
	            HttpMethod.POST,
	            entity,
	            String.class
	        );
	        
	        if(response.getStatusCode().is2xxSuccessful()) {
	        	
	        JsonParser jsonParser = JsonParserFactory.getJsonParser();
	        Map<String, Object> jsonObject = jsonParser.parseMap(response.getBody());
	        
	        String token= (String) jsonObject.get("access_token");
	        
	        
	        return ResponseEntity.ok(token);
	        
	        }
	        else {
	        	return response;
	        }
	}
	
	

	public ResponseEntity<List<User>> getUsers(String token) {
		 String userUrl = "http://localhost:8080/admin/realms/realm3/users";

	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization", "Bearer " + token);

	        HttpEntity<Object> entity = new HttpEntity<>(headers);

	        RestTemplate restTemplate = new RestTemplate();

	        try {
	            ResponseEntity<User[]> response = restTemplate.exchange(
	                userUrl,
	                HttpMethod.GET,
	                entity,
	                User[].class
	            );

	            List<User> userList = Arrays.asList(response.getBody());

	            
	            return new ResponseEntity<List<User>>(userList, response.getStatusCode());
	            
	        } catch (HttpClientErrorException e) {
	            return new ResponseEntity<>(e.getStatusCode());
	        }
	}



	public ResponseEntity<String> addUser(String token,User user) {
		
		String addUrl="http://localhost:8080/admin/realms/realm3/users";
		 HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        
	        headers.set("Authorization", "Bearer " + token);
	        
	        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
	        
	        RestTemplate restTemplate = new RestTemplate();
	        

	        
	        try {
	            ResponseEntity<String> response = restTemplate.exchange(
	            	addUrl,
	                HttpMethod.POST,
	                requestEntity,
	                String.class
	            );

	            return response;
	            
	        } catch (HttpClientErrorException e) {
	            return new ResponseEntity<>(e.getMessage(),e.getStatusCode());
	            
	        }
	}



	public ResponseEntity<String> updateUser(String token,String id, String user) {
		String baseUrl="http://localhost:8080/admin/realms/realm3/users/";
		String updateUrl =baseUrl + id;
        
		 HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization", "Bearer " + token);

	        HttpEntity<Object> entity = new HttpEntity<>(user,headers);

	        RestTemplate restTemplate = new RestTemplate();

	        try {
	            ResponseEntity<String> response = restTemplate.exchange(
	                updateUrl,
	                HttpMethod.PUT,
	                entity,
	                String.class
	            );

	            return response;
	            
	        } catch (HttpClientErrorException e) {
	            return new ResponseEntity<String>(e.getMessage(),e.getStatusCode());
	            
	        }
		
	}



	public ResponseEntity<String> deleteUser(String token, String id) {
		
		String baseUrl="http://localhost:8080/admin/realms/realm3/users/";
		String deleteUrl = baseUrl + id;
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                deleteUrl,
                HttpMethod.DELETE,
                requestEntity,
                String.class
            );

            return response;

        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getMessage(),e.getStatusCode());
        }
    
	}
}
