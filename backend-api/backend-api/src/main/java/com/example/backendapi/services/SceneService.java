package com.example.backendapi.services;

import com.example.backendapi.models.SceneRepository;
import com.example.backendapi.models.Scene;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SceneService
{

    @Autowired
    private SceneRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    // No 1. insert into Scenes
    public void insertIntoScenes(Scene scene)
    {
        repository.insert(scene);
    }


    //Get all Scenes
    public List<Scene> getScenes()
    {
        return repository.findAll();
    }

    //No 2 All Movies in the database

    public List<Scene> getMovieCategory()
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").all("movie"));
        //if(Scene.class)
        List <Scene> movies = mongoTemplate.find(query, Scene.class);

        return movies;
    }

    //No 3 All Shows in the database
    public List<Scene> getShowCategory()
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").all("show"));
        //if(Scene.class)
        List <Scene> shows = mongoTemplate.find(query, Scene.class);

        return shows;
    }

    //No 4 Get a movie by its title
    public Optional <Scene> getByTitle(String title) throws Exception
    {
        Optional <Scene> scene = repository.findByTitle(title);

        if(!scene.isPresent())
        {
            throw new Exception("Movie/TV show with title " + title + " is not found");
        }

        return scene;
    }

    //No 5 Get featured Movies
    public List<Scene> getFeaturedMovies( boolean b)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").all("movie").and("isFeatured").is(b));
        //if(Scene.class)
        List <Scene> movies = mongoTemplate.find(query, Scene.class);

        return movies;
    }

    //No 6 Get featured TV Shows
    public List<Scene> getFeaturedShows( boolean b)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").all("show").and("isFeatured").is(b));

        List <Scene> shows = mongoTemplate.find(query, Scene.class);

        return shows;
    }

    //No 7. Endpoint for a specific movie or TVshow
    public Optional <Scene> getAScene(String id) throws Exception
    {
        Optional <Scene> scene = repository.findById(id);

        if(!scene.isPresent())
        {
            throw new Exception("Movie with id " + id + " is not found");
        }

        return scene;
    }

    //No 8: Endpoint for updating specific movie
    public Scene updateSceneById(String id, Scene newSceneData)
    {
        Optional <Scene> scene = repository.findById(id);

        scene.get().setTitle(newSceneData.getTitle());
        scene.get().setDescription(newSceneData.getDescription());
        scene.get().setSellingPrice(newSceneData.getSellingPrice());
        scene.get().setCategory(newSceneData.getCategory());
        scene.get().setImage(newSceneData.getImage());
        scene.get().setRentingPrice(newSceneData.getRentingPrice());
        scene.get().setIsFeatured(newSceneData.getIsFeatured());
        scene.get().setIsHero(newSceneData.getIsHero());

        Scene updateScene = repository.save(scene.get());
        return updateScene;

    }


    //No 9. Endpoint to delete specific id if present
    public void deleteById(String id) throws Exception
    {


        repository.deleteById(id);
    }

}
