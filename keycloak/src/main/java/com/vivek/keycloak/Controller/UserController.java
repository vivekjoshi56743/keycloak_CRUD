package com.vivek.keycloak.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.keycloak.entity.Auth;
import com.vivek.keycloak.entity.User;
import com.vivek.keycloak.service.UserService;

@RestController
public class UserController {
	
	
	
	@Autowired
	private UserService service;
	
    @GetMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody Auth auth ) {
    	
    	return service.getToken(auth);
    }
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(@RequestHeader("Authorization") String authorizationHeader){
    	
    	return service.getUsers(authorizationHeader);
    }
    
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestHeader("Authorization") String authorizationHeader,@RequestBody User user){
    	
    	return service.addUser(authorizationHeader,user);
    	
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@RequestHeader("Authorization") String authorizationHeader,@PathVariable("id") String id,@RequestBody String user){
		return service.updateUser(authorizationHeader,id,user);
    	
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@RequestHeader("Authorization") String authorizationHeader,@PathVariable("id") String id){
		return service.deleteUser(authorizationHeader,id);
    	
    }
    
}

