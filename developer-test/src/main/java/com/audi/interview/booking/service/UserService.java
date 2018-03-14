package com.audi.interview.booking.service;

import com.audi.interview.booking.jpa.domain.User;
import com.audi.interview.booking.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        Assert.notNull(user, "User must not be null");
        return userRepository.saveAndFlush(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        Assert.notNull(id, "Id must not be null");
        return userRepository.findOne(id);
    }

    public User findByEmail(String email) {
        Assert.hasLength(email, "Email must not be empty");
        return userRepository.findByEmail(email);
    }
    
    public List<User> findByLastName(String lastName){
    	Assert.hasLength(lastName, "Lastname must not be null");
    	List<User> foundUsers = new ArrayList<User>();
    	List<User> allUsers= userRepository.findAll();
    	allUsers.forEach(user -> {
    		if(lastName.equals(user.getLastName())) {
    			foundUsers.add(user);
    		}
    		});
    	return foundUsers;
    }

}