package site.keyu.askme.utils.filter;

/**
 * @Author:Keyu
 */

import java.util.HashMap;
import java.util.Map;

/**
 * WordNode
 * @Param end :If it is the last character of word,end is true
 * @Param next: it point at the next character of sensitive word
 */
public class WordNode{
    private boolean end = false;
    private Map<Character,WordNode> next = new HashMap<>();

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean getEnd(){
        return this.end;
    }

    void addNextNode(Character key, WordNode node){
        next.put(key,node);
    }

    public Map<Character,WordNode> getNext(){
        return this.next;
    }
}
