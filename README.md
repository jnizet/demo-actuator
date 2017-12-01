# demo-actuator
Demo showing how actuator requests are not being intercepted

 - Launch the app: see that the line `**** ADDING INTERCEPTOR com.example.demo.ActuatorConfig$Interceptor@3d7cc3cb TO MAPPING org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping@35e478f ****` is being logged on the standard output.
 - go to `http://localhost:8080/test`: see that the line `**** PRE-HANDLING /test ****` is being logged on the standard output.
 - go to `http://localhost:8080/actuator/health`: see that the expected line `**** PRE-HANDLING /actuator/health ****` is **not** being logged on the standard output.
