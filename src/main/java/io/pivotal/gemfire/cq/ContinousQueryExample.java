package io.pivotal.gemfire.cq;

import com.gemstone.gemfire.GemFireCheckedException;
import com.gemstone.gemfire.GemFireException;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.Pool;
import com.gemstone.gemfire.cache.client.PoolManager;
import com.gemstone.gemfire.cache.query.CqAttributes;
import com.gemstone.gemfire.cache.query.CqAttributesFactory;
import com.gemstone.gemfire.cache.query.QueryService;

/**
 * This is an example of running a Continous Query on a Gemfire region. In this example the region is citizens.
 * Before running the example you would need to create the "citizens" region using gfsh.
 *
 * Look here @see <a href="https://github.com/amithn/gemfire_101"> Gemfire_101</a>} on how to do it.
 *
 * 1) Run "ContinousQueryExample" - it will register the callback handler and wait for insert/updates on the
 *    Citizens region where age > 20.
 *
 * 2) Run CitizenRecordAgeGt20Inserter - which will insert records matching the Continous Query and
 *    trigger callbacks in SimpleCQListener
 *
 */

public class ContinousQueryExample {

    public static void main(String[] args) throws InterruptedException, GemFireCheckedException {
        System.out.println("Hello Gemfire");
        System.out.println("\nConnecting to the distributed system and creating the cache.");

        // Although cache is unused this is required to create the pool (continous_query_pool) defined in
        // resources/continous_query_citizen.xml

        ClientCache cache = new ClientCacheFactory()
                .set("cache-xml-file", "continous_query_citizen.xml")
                .create();

        registerCq();

        while(true) {
            System.out.println("Continous'ly  waiting for updates ...");
            Thread.sleep(1000);
        }
    }

    public static void registerCq() throws GemFireException, GemFireCheckedException {
        // Get a reference to the pool
        Pool myPool = PoolManager.find("continous_query_pool");

        QueryService queryService = myPool.getQueryService();
        CqAttributesFactory cqAf = new CqAttributesFactory();
        cqAf.addCqListener(new SimpleCQListener());
        CqAttributes cqa = cqAf.create();

        // Register the new Continous Query on the Citizens region for citizens age > 20
        String query = "SELECT * FROM /citizens b WHERE b.age > 20";
        queryService.newCq("CQ Citizens gt 20", query, cqa).execute();
    }
}
