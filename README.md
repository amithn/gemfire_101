# gemfire_101

What is Gemfire?
http://pivotal.io/big-data/pivotal-gemfire

There is an open source version of it here - http://geode.incubator.apache.org/

>gemfire_101 is a quick start Gemfire client app to demonstrate various features of Gemfire/Apache Geode. Make sure you have the locator URI setup properly in the citizens_cache.xml before running the app.

#Running Gemfire OQL via the Java API

##Step 1: Use gfsh to create a region.
	gfsh>create region --name=/citizens --type=REPLICATE_PERSISTENT
	
##Step 2: Import the project into an IDE as a Gradle app

##Step 3: Run CitizenProducer
##Step 4: Run GemfireCitizenQueryApp


#Running the Continous Query Example

>This is an example of running a Continous Query on a Gemfire region. In this example the region is citizens.
>Before running the example you would need to create the "citizens" region using gfsh.

 - 1) Run "ContinousQueryExample" - it will register the callback handler and wait for insert/updates on the Citizens region where age > 20.
 - 2) Run CitizenRecordAgeGt20Inserter - which will insert records matching the Continous Query and trigger callbacks in SimpleCQListener




