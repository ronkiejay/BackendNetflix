package com.example.backendapi.models;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface SceneRepository  extends MongoRepository<Scene, String>
{
    Optional<Scene> findByTitle(String title);


}
