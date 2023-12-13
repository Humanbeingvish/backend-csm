package com.cognicx.AppointmentRemainder.dao;

import java.util.Optional;

import com.cognicx.AppointmentRemainder.model.User;

public interface UserDAO {
	
	Optional<User> findByUsername(String username) throws Exception;
	
	Boolean existsByUsername(String username) throws Exception;
	
    Boolean existsByEmail(String email) throws Exception;
    
    public User save(User user) throws Exception;
}