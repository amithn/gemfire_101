package io.pivotal.gemfire.app;

import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.query.Query;
import com.gemstone.gemfire.cache.query.QueryService;
import com.gemstone.gemfire.cache.query.SelectResults;

public class GemfireQueryNewApp {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Connecting to the distributed system and creating the cache.");

        ClientCache cache = new ClientCacheFactory()
                .set("cache-xml-file", "citizens_cache_without_cb.xml")
                .create();

        String queryString1 ="SELECT * FROM /citizens";
        SelectResults<Citizen> citizens = doQuery(cache, queryString1);

        for(Citizen c: citizens) {
            System.out.println("Name = " + c.getName() + " Age = " + c.getAge());
        }
    }


    public static SelectResults<Citizen> doQuery (ClientCache cache, String queryString)
    {
        QueryService qs = cache.getQueryService();
        Query q = qs.newQuery(queryString);

        SelectResults<Citizen> results = null;
        try
        {
            results = (SelectResults<Citizen>)q.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return results;
    }
}
