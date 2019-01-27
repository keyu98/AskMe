package site.keyu.askme.utils.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:Keyu
 */
@Component
public class SensitiveWordFilter {

    @Autowired
    private static InitSensitiveWord words = new InitSensitiveWord();

    private static final String DEFAULT_REPLACEMENT = "**";

    public String filter(String text){
        if (text == null){
            return text;
        }
        StringBuilder newtext = new StringBuilder();
        WordNode nowNode = words.getRoot();

        int begin = 0;
        int pos = 0;

        while(pos < text.length()){
            char c = text.charAt(pos);
            nowNode = nowNode.getNext().get(c);

            if (nowNode == null){
                newtext.append(c);
                pos = begin + 1;
                begin = pos;
                nowNode = words.getRoot();
            }
            else {
                pos++;
                //find sensitive word
                if (nowNode.getEnd()){
                    newtext.append(DEFAULT_REPLACEMENT);
                    begin = pos;
                    nowNode = words.getRoot();
                }

            }


        }

        newtext.append(text.substring(begin));

        return newtext.toString();
    }

    public static void main(String[] args) {
        SensitiveWordFilter sensitiveWordFilter = new SensitiveWordFilter();
        System.out.println(sensitiveWordFilter.filter("你个大猪蹄子，大猪肘子，我草泥马，草泥马"));
    }

}
