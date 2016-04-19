package io.pivotal.gemfire.cq;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import io.pivotal.gemfire.app.Citizen;

public class CitizenRecordAgeGt20Inserter {

    public static void main(String[] args) {
        System.out.println("Connecting to Gemfire");

        ClientCache cache = new ClientCacheFactory()
                .set("cache-xml-file", "citizens_cache.xml")
                .create();

        Region<String,Citizen> citizenRegion = cache.getRegion("citizens");
        System.out.println("Example region, " + citizenRegion.getFullPath() + ", created in cache. ");

        Citizen bill = new Citizen("Bill", 10);
        Citizen donald = new Citizen("Donald", 88);

        try {
            System.out.println("Inserting entries into /citizens");

            // This won't trigger the callback as Bill's  age < 20
            citizenRegion.put("11111", bill);

            // This will as it matches the Continous query of age > 20
            citizenRegion.put("22222", donald);

        } catch(Exception e) {
            e.printStackTrace();
        }

        cache.close();
    }
}
