# coffe-assesment-atradius
## Execute the application with maven and java
#### 1 - Compile the application:

	mvn clean install -U

#### 2 - Run the application:

	java -jar target/CoffeAssesment-0.1.1.jar

## Create and run docker container
#### Build docker image:
We will execute this command: 

	docker build -t coffe-assesment-atradius:tag . 

where tag is the version we want to specify for our image. In this example we'll be using 0.0.1:

	docker build -t coffe-assesment-atradius:0.0.1 . 
	
#### Run the docker container:
After successfully creating the image we'll execute the following command:

	docker run -p 8080:8080 coffe-assesment-atradius:tag

Remember to use the tag we specified previously. In this case:

	docker run -p 8080:8080 coffe-assesment-atradius:0.0.1


## Postman
I have included a Postman collection to test the 2 endpoints of the assesment.
