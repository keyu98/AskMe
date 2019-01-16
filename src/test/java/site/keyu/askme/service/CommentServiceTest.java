package site.keyu.askme.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.keyu.askme.pojo.Comment;

import java.util.Date;

/**
 * @Author:Keyu
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    public void addComment(){
        Comment comment = new Comment();
        comment.setContent("内容");
        comment.setUserId(1);
        comment.setCreatedDate(new Date());
        comment.setEntityId(1);
        comment.setEntityType(0);
        comment.setStatus(0);

        commentService.addComment(comment);

    }

    @Test
    public  void findComment(){

        System.out.println(commentService.getCommentsByEntity(1,0));

    }
}
