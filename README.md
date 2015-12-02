Time Server  [![Build Status](https://travis-ci.org/101bits/akka-http-time-server.svg?branch=master)](https://travis-ci.org/101bits/akka-http-time-server)
--

The purpose of the project is to learn building a simple HTTP REST API that returns server time. The focus is also to write tests as I learn more about `akka-http` and `akka` in general.

How to run?
--
* Clone this project
* Make sure project compiles and test pass
```
sbt clean compile test package
```
* Run the time server
```
sbt run
```

You should see something similar to following
```
$ sbt run
[info] Loading project definition from /Users/harit/IdeaProjects/akka-http-time-server/project
[info] Set current project to akka-http-time-server (in build file:/Users/harit/IdeaProjects/akka-http-time-server/)
[info] Running com.akka.http.time.Server
TimeServer is running at http://localhost:8080
Press RETURN to stop ...
```

* Hit the API
```
$ curl -v http://localhost:8080
* Rebuilt URL to: http://localhost:8080/
*   Trying 127.0.0.1...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET / HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.43.0
> Accept: */*
>
< HTTP/1.1 200 OK
< Server: akka-http/2.4-SNAPSHOT
< Date: Wed, 02 Dec 2015 04:02:26 GMT
< Content-Type: application/json
< Content-Length: 38
<
{
  "now": "2015-12-01T20:02:26.682"
}
```
