package day36workshop.day36workshop.repo;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import day36workshop.day36workshop.model.Image;
import day36workshop.day36workshop.model.Post;

@Repository
public class PostRepo {

    @Autowired
    private JdbcTemplate template;

    //Basic crud

    public boolean SavePost(String post_id, String comments,InputStream is , String contentType){
        return template.update(Queries.SQL_SAVE_POST, post_id,comments,is,contentType)> 0;

    }

    public Optional<Image> getPicture(String post_id){
        return template.query(Queries.SQL_READ_POST, (ResultSet rs) -> {
            if (!rs.next()) {
                System.out.println("Its empty");
                return Optional.empty();
            }
            return Optional.of(new Image(rs.getBytes("picture"), post_id));
        });
    }
    
    public List<Post> getAllPosts() {
        List<Post> posts = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_GET_ALL_POSTS);
        while (rs.next()) {
        // memorise this is the same throughout
            posts.add(
            new Post
            (rs.getString("post_id"), rs.getString("comments")));
        }
        return posts;
    }
}
