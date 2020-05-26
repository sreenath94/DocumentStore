# Document Store
Document Store is a java library used to store documents in different storage options based on configuration

## Installation
* This library can be installed by adding jar file to the class path of the project

* For using local storage the following propeties should be added to resource/application.properties file 
```bash
type = LOCALSTORAGE
file.destination = test/
file.download.destination = download/
```
* For using MONGODB storage the following propeties should be added to resource/application.properties file 
  * Add the following properties to resource/application.properties file 
      ```bash
      type = MONGODB
      file.destination = test/
      host = localhost
      port = 27017
      db = store
      file.download.destination = download/
      ```
   * Update pom.xml with the following dependency   
      ```bash
      <dependency>
			<groupId>org.mongodb</groupId>
			<artifactId>mongo-java-driver</artifactId>
			<version>3.12.4</version>
		  </dependency>
      ```
* Update pom.xml with the below dependecy 
```bash
<dependency>
<groupId>commons-io</groupId>
<artifactId>commons-io</artifactId>
<version>2.6</version>
</dependency>
```


## Usage

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
