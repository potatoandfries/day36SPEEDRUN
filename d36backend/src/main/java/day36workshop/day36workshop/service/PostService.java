package day36workshop.day36workshop.service;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import day36workshop.day36workshop.model.Image;
import day36workshop.day36workshop.model.Post;
import day36workshop.day36workshop.repo.PostRepo;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

@Service
public class PostService {

    @Autowired
    private PostRepo repo;

    //remember everything is rest now
    public boolean SavePost(String comments,InputStream is, String contentType){
        String post_id = UUID.randomUUID().toString().substring(0,8);
        System.out.println("saving");
        return repo.SavePost(post_id, comments, is, contentType);
    }

    public Optional<Image> getPicture(String post_id){
        return repo.getPicture(post_id);
    }

    public JsonArray getAllPosts() {
        List<Post> posts = repo.getAllPosts();
        JsonArrayBuilder builder = Json.createArrayBuilder();
        posts.forEach(post -> {
            builder.add(
                Json.createObjectBuilder()
                    .add("post_id", post.id())
                    .add("comments", post.comments())
                   
            );
        });
        return builder.build();
    }
}
