package site.keyu.askme.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Keyu
 */
public class ViewObject {

    private User user;

    private Question question;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }




//    private Map<String, Object> objs = new HashMap<String, Object>();
//    public void set(String key, Object value) {
//        objs.put(key, value);
//    }
//
//    public Object get(String key) {
//        return objs.get(key);
//    }
}
