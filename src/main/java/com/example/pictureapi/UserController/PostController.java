package com.example.pictureapi.UserController;

import com.example.pictureapi.Repository.PostRepository;
import com.example.pictureapi.dbModels.PostModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<PostModel>> getAllUsers() {
        LOG.info("Getting all posts.");
        return new ResponseEntity<>(postRepository.findAll() , HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<PostModel> addNewPost(@RequestBody PostModel post) {
        LOG.info("Saving post.");
        try {
            postRepository.save(post);
            return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
        }catch (Exception e){

            return new ResponseEntity<>(post, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @CrossOrigin
    @PutMapping("/update/{id}")
    ResponseEntity<PostModel> updatePost(@RequestBody PostModel newPost, @PathVariable String id) {
            LOG.info("Updating Post with id:" + id);
            var exisitingPostOptional = postRepository.findById(id);
            if (exisitingPostOptional.isPresent()){
                var exisitingPost = exisitingPostOptional.get();
                exisitingPost.setComments(newPost.getComments());
                return new ResponseEntity<>(postRepository.save(exisitingPost), HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(postRepository.save(newPost), HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    ResponseEntity<PostModel> editPost(@RequestBody PostModel newPost, @PathVariable String id) {
            LOG.info("Updating Post details with id:" + id);
            return new ResponseEntity<>(postRepository.save(newPost), HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    ResponseEntity<Optional<PostModel>> getPost(@PathVariable String id) {
            LOG.info("find Post with id:" + id);
            return new ResponseEntity<>(postRepository.findById(id), HttpStatus.ACCEPTED);
    }


}
