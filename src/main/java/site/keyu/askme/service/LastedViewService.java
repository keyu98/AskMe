package site.keyu.askme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.keyu.askme.pojo.Comment;
import site.keyu.askme.pojo.CommentViewObject;
import site.keyu.askme.pojo.Question;
import site.keyu.askme.pojo.ViewObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Keyu
 */
@Service
public class LastedViewService {
     @Autowired
     QuestionService questionService;

     @Autowired
     UserService userService;

     @Autowired
     CommentService commentService;
    /**
     * 查找问题视图
     * @param offset
     * @param limit
     * @return
     */
     public List<ViewObject> getLastedQuestion(int offset, int limit){
        List<Question> questionList = questionService.findLatestQuestion(offset, limit);
        List<ViewObject> vos = new ArrayList<>();
        for (Question question:questionList){
            ViewObject vo = new ViewObject();
            vo.setQuestion(question);
            vo.setUser(userService.getUser(question.getUserId()));
            vos.add(vo);
        }
        return  vos;
    }

    /**
     * 查找问题回答视图
     * @param offset
     * @param limit
     * @return
     */
    public List<CommentViewObject> getLastedComment(int offset, int limit){
        List<Comment> commentList = commentService.getLatestComment(offset,limit);
        List<CommentViewObject> cvos = new ArrayList<>();
        for (Comment comment:commentList){
            CommentViewObject cvo = new CommentViewObject();
            cvo.setComment(comment);
            cvo.setUser(userService.getUser(comment.getUserId()));
            cvos.add(cvo);
        }

        return cvos;
    }

    /**
     * 查找特定问题的评论视图
     * @param id
     * @return
     */
    public List<CommentViewObject> getCommentsById(int id){
        List<Comment> commentList = commentService.getCommentsByEntity(id,0);
        List<CommentViewObject> cvos = new ArrayList<>();
        for (Comment comment:commentList){
            CommentViewObject cvo = new CommentViewObject();
            cvo.setComment(comment);
            cvo.setUser(userService.getUser(comment.getUserId()));
            cvos.add(cvo);
        }

        return cvos;
    }

}
