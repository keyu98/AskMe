package site.keyu.askme.async;

/**
 * @Author:Keyu
 */
public enum  EventType {
    LIKE(0),
    ADOPT(1),
    COMMENT(2);

    private int value;
    EventType(int value){
        this.value = value;
    }

    public int getValue(){return value;}
}
