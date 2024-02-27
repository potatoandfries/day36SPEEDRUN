package day36workshop.day36workshop.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// careful dont use the tomcat one.
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import day36workshop.day36workshop.model.Image;
import day36workshop.day36workshop.service.PostService;
import jakarta.json.Json;
import jakarta.json.JsonArray;


@RestController
@RequestMapping(path="/api")
public class PostController {
    
    @Autowired
    private PostService svc;

    @PostMapping(path = "/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPost(
        @RequestPart String comments,
        @RequestPart MultipartFile postImg) {

    // FOR POST : @RequestPart
    // For GET  : @RequestParam but URL /posts?sort=asc.
    // FOR GET  : @PathVariable  URL /post/image/{post_id}
    try {
        System.out.println("Hello");
        boolean result = svc.SavePost(comments, postImg.getInputStream(), postImg.getContentType());

        if (result) {
            return ResponseEntity.status(201).body(null);

        } else {
            return ResponseEntity.status(500)
                    .body(Json.createObjectBuilder()
                            .add("Message", "error creating posts")
                            .build()
                            .toString());
        }
        } catch (Exception e) {
        // Return a 500 Internal Server Error response
        return ResponseEntity.status(500)
                .body(Json.createObjectBuilder()
                        .add("Message", "Internal server error")
                        .build()
                        .toString());
            }
        }
    
        @GetMapping(path = "/post/image/{post_id}")
        public ResponseEntity<byte[]> getPicture(@PathVariable String post_id) {
            Optional<Image> opt = svc.getPicture(post_id);
        
            if (opt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        
            Image image = opt.get();
            return ResponseEntity.ok()
                    .header("Content-Type", image.mediatype())
                    .body(image.content());
        }

        @GetMapping(path="/posts")
        public ResponseEntity<String> getAllPosts(){

            JsonArray posts = svc.getAllPosts();
            System.out.println("this is the problem");
            return ResponseEntity.status(200)
                .body(posts.toString());
        }
    }