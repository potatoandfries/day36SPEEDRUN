package day36workshop.day36workshop.repo;

public class Queries {
    
        public static final String SQL_SAVE_POST = """
            INSERT INTO posts (post_id, comments,picture)
            VALUE (?,?,?);
            """;

            public static final String SQL_READ_POST = """
            SELECT picture, comment from posts 
            Where post_id = ?;
            """;
            
            public static final String SQL_GET_ALL_POSTS="""
            SELECT * from posts;        

            """;
}
