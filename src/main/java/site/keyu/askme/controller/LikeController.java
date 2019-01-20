package site.keyu.askme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import site.keyu.askme.pojo.Comment;
import site.keyu.askme.pojo.HostHolder;
import site.keyu.askme.service.CommentService;
import site.keyu.askme.service.LikeService;

/**
 * @Author:Keyu
 */
@Controller
public class LikeController {
    @Autowired
    LikeService likeService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    CommentService commentService;

    @RequestMapping(path = "/user/like",method = {RequestMethod.POST})
    public String likeIt(RedirectAttributes redirectAttributes,
                         @RequestParam("commentId") int commentId){

        Comment comment = commentService.getCommentById(commentId);

        long likeCount = likeService.likeIt(hostHolder.getUser().getId(), 0,commentId);

        redirectAttributes.addAttribute("id",comment.getEntityId());

        return "redirect:/qst";
    }

    @RequestMapping(path = "/user/dislike",method = {RequestMethod.POST})
    public String dislikeIt(RedirectAttributes redirectAttributes,
                            @RequestParam("commentId") int commentId){
        Comment comment = commentService.getCommentById(commentId);

        long dislikeCount = likeService.dislikeIt(hostHolder.getUser().getId(),0,commentId);

        redirectAttributes.addAttribute("id",comment.getEntityId());

        return "redirect:/qst";

    }


}
