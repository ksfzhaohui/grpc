package org.grpc.sample.server;

import org.grpc.sample.api.HelloService;
import org.grpc.sample.api.Person;
import org.grpc.server.RpcService;

@RpcService(value = HelloService.class, version = "sample.hello2")
public class HelloServiceImpl2 implements HelloService {

	@Override
	public String hello(String name) {
		return "你好! " + name;
	}

	@Override
	public String hello(Person person) {
		return "你好! " + person.getFirstName() + " " + person.getLastName();
	}
}
