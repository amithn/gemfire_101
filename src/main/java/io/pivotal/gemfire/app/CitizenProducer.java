package io.pivotal.gemfire.app;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;

public class CitizenProducer {

    public static void main(String[] args) {
        System.out.println("\nConnecting to the distributed system and creating the cache.");

        ClientCache cache = new ClientCacheFactory()
                .set("cache-xml-file", "citizens_cache_without_cb.xml")
                .create();

        Region<String,Citizen> citizenRegion = cache.getRegion("citizens");
        System.out.println("Example region, " + citizenRegion.getFullPath() + ", created in cache. ");

        Citizen p1 = new Citizen("Bill", 10);
        Citizen p2 = new Citizen("Donald", 1000);

        try {
            System.out.println("Putting entries: ");
            citizenRegion.put("101", p1);
            citizenRegion.put("102", p2);
        } catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nClosing the cache and disconnecting.");
        cache.close();
    }
}
