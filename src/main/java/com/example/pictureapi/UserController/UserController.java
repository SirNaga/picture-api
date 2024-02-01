package com.example.pictureapi.UserController;
import com.example.pictureapi.Repository.UserRepository;
import com.example.pictureapi.dbModels.UserModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<UserModel> getAllUsers() {
        LOG.info("Getting all users.");
        return userRepository.findAll();
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public UserModel getUser(@PathVariable String email) {
        LOG.info("Getting user with Email: {}.", email);
        UserModel user = null;
        try {
            return userRepository.findByEmail(email);
        }catch (Exception e){
            return null;
        }

    }


    @PostMapping("/create")
    public ResponseEntity<UserModel> addNewUsers(@RequestBody UserModel user) {
        LOG.info("Saving user.");
        try {
            userRepository.save(user);
            return new ResponseEntity<UserModel>(user, HttpStatus.ACCEPTED);
        }catch (Exception e){

            return new ResponseEntity<UserModel>(user, HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
