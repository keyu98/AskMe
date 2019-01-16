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
