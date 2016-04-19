package io.pivotal.gemfire.cq;

import com.gemstone.gemfire.cache.query.CqEvent;
import com.gemstone.gemfire.cache.query.CqListener;
import io.pivotal.gemfire.app.Citizen;

public class SimpleCQListener implements CqListener
{

    @Override
    public void close()
    {
        System.out.println("SimpleCQListener:Received Close Event");
    }

    @Override
    public void onError(CqEvent event)
    {
        System.out.println("SimpleCQListener:Received onError event");
        System.out.println("SimpleCQListener:Throwable: " + event.getThrowable());
    }

    @Override
    public void onEvent(CqEvent event)
    {
        System.out.println("SimpleCQListener:Received event: " + event);
        System.out.println("SimpleCQListener:Key is:  " + event.getKey());
        System.out.println("SimpleCQListener:Order is: " +  event.getNewValue());
        System.out.println("SimpleCQListener:Base Operation is:  " + event.getBaseOperation());
        System.out.println("SimpleCQListener:Query Operation is: " + event.getQueryOperation());
        System.out.println("SimpleCQListener:Cq is: " + event.getCq());
        System.out.println("SimpleCQListener:DeltaValue: " + event.getDeltaValue());
    }

}