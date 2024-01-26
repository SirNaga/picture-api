package com.example.pictureapi.Repository;

import com.example.pictureapi.dbModels.PostModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<PostModel, String> {


}
