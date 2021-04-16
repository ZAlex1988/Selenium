Solr Stress test application allows users to insert documents into collection and perform searches in parallel
Once the operation is done it will provide stats to the user about the length of each of the operations. This application
assumes you know some java programming and how to manage and set up Solr. It is not intended to be run out of the box,
rather designed for a specific use case for USF's ISM6145.020S21 course as a helper for the final portfolio project

Before running this program please ensure the following:

1. films.csv file is available at C:\FOSS\solr-8.1.1\solr-8.1.1\example\films\films.csv
You can get this CSV file at: https://drive.google.com/file/d/1a4Gxehc-biE1LnV05URt6ZjUpyB4gUOE/view?usp=sharing
Alternatively Solr comes with the file by default as part of its tutorial.

2. Solr v 8.1.1 is installed and running on localhost port 8983

3. Movies collection is created with the following schema (to view full schema please clone repo and view in editior -
GitHub may have problems rendering the page with XML content):

<?xml version="1.0" encoding="UTF-8"?>
<schema name="Movies" version="1.6">
<uniqueKey>id</uniqueKey>
<types>
  <fieldType name="uuid" class="solr.UUIDField" indexed="true"/>
  <fieldType name="int" class="solr.IntPointField"/>
  <fieldType name="long" class="solr.LongPointField"/>
  <fieldType name="string" class="solr.StrField"/>
  <fieldType name="date" class="solr.DatePointField"/>
</types>
<fields>

  <field name="id" type="uuid" indexed="true" stored="true" required="true" />
  <field name="_version_" type="long" indexed="true" stored="true" required="true" />


  <field name="name" type="string" indexed="true" stored="true" docValues="true"/>
  <field name="directed_by" type="string" indexed="true" stored="true" docValues="true" multiValued="true"/>
  <field name="genre" type="string" indexed="true" stored="true" docValues="true"  multiValued="true"/>
  <field name="initial_release_date" type="date" indexed="true" stored="true" docValues="true"/>
  <field name="numActors" type="int" indexed="true" stored="true" docValues="true"/>

</fields>


</schema>

4. Please ensure that the configuration allows UUID field generation for field ID. Please refer https://solr.apache.org/guide/ for set up instructions

5. Solr must have admin user created with all permissions defined. Please provide username and password for the user in MainDriver.java class in section //config params

6. //config params section also allows configurations for the search concurrency and the number of documents to be inserted

7. Once configurations are completed please run MainDriver.java

8. Please note that collection configuration methods are available in InsertDocs.java - createCollection; deleteCollection; clearCollection
These can be used as long as the both configuration files are available at C:\FOSS\solr-8.1.1\solr-8.1.1\server\solr\configsets\Movies\conf
These are optional methods that can be run either from a custom created class or from the main driver.
It is recommended to create these manually following the guidelines from https://solr.apache.org/guide/

