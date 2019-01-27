package site.keyu.askme.utils.filter;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author:Keyu
 */
public class InitSensitiveWord {
    private WordNode root = new WordNode();

    InitSensitiveWord(){
        readSensitiveWord();
    }

    public void add(String line){
        WordNode nowNode = root;
        for (int i = 0;i < line.length(); i++){
            Character c = line.charAt(i);

            //add word to Map
            WordNode wordNode =  nowNode.getNext().get(c);
            if(wordNode != null){

                nowNode = wordNode;
            }
            else {
                WordNode newc = new WordNode();
                //if it is the last one
                if (i == line.length() - 1){ newc.setEnd(true); }
                nowNode.addNextNode(c,newc);
                nowNode = newc;

            }
        }
    }

    public void readSensitiveWord() {

        try {
            InputStream is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("SensitiveWords.txt");
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null){
                add(line);
            }

        }catch (Exception e){
            System.out.println("ERROR: failed to read sensitive words!");
        }
    }

    public WordNode getRoot(){
        return this.root;
    }

}
