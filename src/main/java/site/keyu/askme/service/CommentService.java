package site.keyu.askme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import site.keyu.askme.dao.CommentDao;
import site.keyu.askme.pojo.Comment;

import java.util.List;

/**
 * @Author:Keyu
 */
@Service
public class CommentService {
    @Autowired
    CommentDao commentDao;

    /**
     * 获取评论
     * @param entityId
     * @param entityType
     * @return
     */
    public List<Comment> getCommentsByEntity(int entityId, int entityType) {
        return commentDao.selectCommentByEntity(entityId, entityType);
    }

    /**
     * 添加评论
     * @param comment
     * @return
     */
    public int addComment(Comment comment) {
        //HTML过滤
        comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
        //敏感词过滤
        //...

        //发布
        return commentDao.addComment(comment) > 0 ? comment.getId() : 0;
    }

    /**
     * 获取评论数量
     * @param entityId
     * @param entityType
     * @return
     */
    public int getCommentCount(int entityId, int entityType) {
        return commentDao.getCommentCount(entityId, entityType);
    }

    /**
     * 获取一个用户的评论
     * @param userId
     * @return
     */
    public int getUserCommentCount(int userId) {
        return commentDao.getUserCommentCount(userId);
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    public boolean deleteComment(int commentId) {
        return commentDao.updateStatus(commentId, 1) > 0;
    }

    /**
     * 根据id查找评论
     * @param id
     * @return
     */
    public Comment getCommentById(int id) {
        return commentDao.getCommentById(id);
    }

    /**
     * 查找最新评论
     * @param offset
     * @param limit
     * @return
     */
    public List<Comment> getLatestComment(int offset,int limit){
        return commentDao.selectLatestComments(offset, limit);
    }


}
