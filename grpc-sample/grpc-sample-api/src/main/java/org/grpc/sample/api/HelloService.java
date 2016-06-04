package org.grpc.sample.api;

public interface HelloService {

    String hello(String name);

    String hello(Person person);
}
