package site.keyu.askme.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.keyu.askme.pojo.Question;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author:Keyu
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceTest {
    @Autowired
    QuestionService questionService;

    @Test
    public void postQuestionTest(){

        for (int n = 0;n < 10;n ++){
            Question question = new Question();
            question.setTitle("问题题目" + n);
            question.setContent("问题内容" + n);
            question.setCreatedDate(new Date());
            question.setUserId(2);
            question.setCommentCount(0);
            question.setStatus(0);
            questionService.postQuestion(question);
        }
    }

    @Test
    public void postOneQuestionTest(){


        Question question = new Question();
        question.setTitle("有什么笑话可以笑一辈子？");
        question.setContent("打的去朋友家，路上瞌睡了一小会，本来30元的路程被司机绕成80元！我豪气地说:“大哥，给你100元不用找了！”司机到处看了看车上说:“钱呢？放哪了？”我下车就跑，冲司机喊到:“我都跟你说了不用找了，找也找不到的！");
        question.setCreatedDate(new Date());
        question.setUserId(2);
        question.setCommentCount(0);
        question.setStatus(0);
        questionService.postQuestion(question);

    }

    @Test
    public void findQuestion(){

        Question question = questionService.findQuestionById(3);

        if (question != null){
            System.out.println(question);
        }

        else {
            System.out.println("failed to find !");
        }

    }

    @Test
    public void findQuestions(){
        List<Question> questions = questionService.findLatestQuestion(2,0,10);


        System.out.println(questions);

    }
}
