package com.example.demo.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.codenotfound.types.helloworld.Greeting;
import com.codenotfound.types.helloworld.ObjectFactory;
import com.codenotfound.types.helloworld.Person;

/**
 * @Endpoint registers the class with Spring WS as a potential candidate for
 *           processing incoming SOAP messages.
 * 
 * @PayloadRoot is then used by Spring WS to pick the handler method based on
 *              the message’s namespace (refer to "targetNamespace" inside
 *              <wsdl:types> ->> <xsd:schema targetNamespace="nameSpace">) and
 *              localPart (refer to <wsdl:message> ->>
 *              <wsdl:part name="localPart">).
 * 
 * @RequestPayload indicates that the incoming message will be mapped to the
 *                 method’s request parameter.
 * 
 *                 The @ResponsePayload annotation makes Spring WS map the
 *                 returned value to the response payload.
 * 
 * @author gyang
 *
 */
@Endpoint
public class HelloWorldEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldEndpoint.class);

	//下面这两个注解的作用就是通过映射wsdl中的namespace和localpart配置相应的方法。 每个wsdl中的方法都有统一的nameSpace和自己的对应的localPart
	@PayloadRoot(namespace = "http://codenotfound.com/types/helloworld", localPart = "person")
	@ResponsePayload
	public Greeting sayHello(@RequestPayload Person request) {
		LOGGER.info("Endpoint received person[firstName={},lastName={}]", request.getFirstName(),
				request.getLastName());

		String greeting = "Hello " + request.getFirstName() + " " + request.getLastName() + "!";

		ObjectFactory factory = new ObjectFactory();
		Greeting response = factory.createGreeting();
		response.setGreeting(greeting);

		LOGGER.info("Endpoint sending greeting='{}'", response.getGreeting());
		return response;
	}
}
