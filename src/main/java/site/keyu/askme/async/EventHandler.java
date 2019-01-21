package site.keyu.askme.async;

import java.util.List;

/**
 * @Author:Keyu
 */
public interface EventHandler {
    void doHandle(EventModel model);

    List<EventType> getSupportEventTypes();
    
}
