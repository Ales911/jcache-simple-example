# exercises

# --t
docker build -t ales911/jcache-simple-example .

#docker network create -d host jcache-simple-example-net

#docker create --name jcache-simple-example ales911/jcache-simple-example 

#--network host  OR -p 8080:8080
docker run -d --network host --name jcache-simple-example ales911/jcache-simple-example 
