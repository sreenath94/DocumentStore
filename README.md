# Document Store
Document Store is a java library used to store documents in different storage options based on configuration

## Installation
* This library can be installed by adding jar file to the class path of the project
* Add the below dependecy to pom.xml
```bash
<dependency>
<groupId>commons-io</groupId>
<artifactId>commons-io</artifactId>
<version>2.6</version>
</dependency>
```

* For using local storage the following propeties should be added to resource/application.properties file 
```bash
documentStore.type = LOCALSTORAGE 
documentStore.file.uploadDestination = test/
documentStore.downloadDestination = download/
```
* For using MONGODB storage the following steps needs to be done 
  * Add the following properties to resource/application.properties file 
      ```bash
      documentStore.type = MONGODB
      documentStore.uploadDestination = test/
      documentStore.host = localhost
      documentStore.port = 27017
      documentStore.db = store
      documentStore.downloadDestination = download/
      ```
   * Update pom.xml with the following dependency   
      ```bash
      <dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>2.6</version>
      </dependency>
      ```



## Usage
* Upload file
```java
import java.io.File;

import simpledocumentstore.DocumentStore;
import simpledocumentstore.exception.DocumentStoreException;

public class TestClass {

	public static void main(String args[]) throws DocumentStoreException {
		DocumentStore.uploadDocument(new File("target/test.pdf"));
	}
}
```
* Download file
```java
import java.io.File;
import simpledocumentstore.models.DocumentModel;
import simpledocumentstore.DocumentStore;
import simpledocumentstore.exception.DocumentStoreException;

public class TestClass {

	public static void main(String args[]) throws DocumentStoreException {
		DocumentModel documentModel =  DocumentStore.downloadDocument("test.pdf"); 
	}
}
```
