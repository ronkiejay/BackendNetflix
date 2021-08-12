package com.example.backendapi.controllers;

import com.example.backendapi.CustomizedResponse;
import com.example.backendapi.models.Scene;
import com.example.backendapi.services.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SceneController
{

    @Autowired
    private SceneService service;

    //Adding scenes to the database
    @PostMapping(value= "/scenes", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addScene(@RequestBody Scene scene)
    {
        service.insertIntoScenes(scene);

        return new ResponseEntity(scene, HttpStatus.OK);
    }

    //Get all Scenes
    @GetMapping("/scenes")
    public ResponseEntity getscenes()
    {
        var customizedResponse = new CustomizedResponse("Below is the list of the Movies and TV shows", service.getScenes());

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //No 2  Get Movies Category
    @GetMapping("/scenes/moviescategory")
    public ResponseEntity getmovieCategory()
    {
        var customizedResponse = new CustomizedResponse("Below is the list of all movies: ", service.getMovieCategory());

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //No 3  Get TV Shows Category
    @GetMapping("/scenes/showscategory")
    public ResponseEntity getshowsCategory()
    {
        var customizedResponse = new CustomizedResponse("Below is the list of all movies: ", service.getShowCategory());

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //No 4 Endpoint for getting a movie or tv show by title
    @GetMapping("/scenes/title/{title}")
    public ResponseEntity getBytitle(@PathVariable("title") String title)
    {
        CustomizedResponse customizedResponse = null;
        try
        {
            customizedResponse = new CustomizedResponse("Movie/TV Show with the title " + title, Collections.singletonList(service.getByTitle(title)));
        }
        catch (Exception e)
        {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);

            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //No 5 Get Featured Movies
    @GetMapping("/scenes/featuredmovies")
    public ResponseEntity getFeaturedmovies(@RequestParam(value="isFeatured") boolean b)
    {
        var customizedResponse = new CustomizedResponse("Below is the list of all featured movies: ", service.getFeaturedMovies(b));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //No 6 Get Featured Shows
    @GetMapping("/scenes/featuredshows")
    public ResponseEntity getFeaturedshows(@RequestParam(value="isFeatured") boolean b)
    {
        var customizedResponse = new CustomizedResponse("Below is the list of all featured TV shows: ", service.getFeaturedShows(b));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //No 7. Endpoint for a specific movie or TVshow IDs
    @GetMapping("/scenes/{id}")
    public ResponseEntity getAMovie(@PathVariable("id") String id)
    {
        CustomizedResponse customizedResponse = null;
        try
        {
            customizedResponse = new CustomizedResponse("Movie/TV Show with id " + id, Collections.singletonList(service.getAScene(id)));
        }
        catch (Exception e)
        {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);

            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //No 8. Endpoint for update
    @PutMapping(value="/scenes/{id}", consumes ={
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity updateMovie(@PathVariable("id") String id, @RequestBody Scene newScene)
    {
        CustomizedResponse customizedResponse;
        try
        {
            customizedResponse = new CustomizedResponse("Scene with the ID " + id + " was successfully updated", Collections.singletonList(service.updateSceneById(id, newScene)));
        }
        catch(Exception e)
        {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);

            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);


    }

    //No 9. Endpoint to delete a Scene
    @DeleteMapping("/scenes/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id)
    {
        CustomizedResponse customizedResponse = null;
        try
        {
            customizedResponse = new CustomizedResponse("Movie/TV Show with id " + id + " deleted", null);
            service.deleteById(id);
        }
        catch (Exception e)
        {
            //customizedResponse = new CustomizedResponse(e.getMessage(), null);

            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);

    }

}
