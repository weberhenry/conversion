### DB End Point 
[H2 DB](http://localhost:8182/conversion/h2-ui)

### Status End Points 
[Acuator](http://localhost:8182/conversion/actuator)
[Acuator/Health](http://localhost:8182/conversion/actuator/health)

### API End Point 
[Swagger API](http://localhost:8182/conversion/swagger-ui.html)

### Rest Call Ex
[Request URL](http://localhost:8182/conversion/convert/Length/Millimetre/Milometre/10000)

### Application URL
[UI](http://localhost:8182/conversion)

### Cmd to package application

```mvn clean compile test package```

### Cmd to Dockerize application

```docker build -t demo:1.0.0 .```

### Cmd to instantiate Docker appliction

```docker run -d -p 8182:8182 demo:1.0.0```
