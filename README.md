# exercises

# --t
docker build -t ales911/jcache-simple-example .

#docker network create -d host jcache-simple-example-net

#docker create --name jcache-simple-example ales911/jcache-simple-example 

#docker run -d --network host --name jcache-simple-example ales911/jcache-simple-example 
# -p <host port>:<container port>
docker run -d -p 8080:8080 --name jcache-simple-example ales911/jcache-simple-example 


docker run -p 9411:9411 openzipkin/zipkin:latest
