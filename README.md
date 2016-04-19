# gemfire_101

What is Gemfire?
http://pivotal.io/big-data/pivotal-gemfire

There is an open source version of it here - http://geode.incubator.apache.org/

gemfire_101 is a quick start Gemfire client app to put data into your Gemfire caches. Make sure you have the locator URI setup properly in the citizens_cache_without_cb.xml before running the app.

#Step 1: Use gfsh to create a region.
	gfsh>create region --name=/OrderKey --type=REPLICATE_PERSISTENT
	
##Step 2: Import the project into an IDE as a Gradle app

##Step 3: Run CitizenProducer
##Step 4: Run GemfireCitizenQueryApp



