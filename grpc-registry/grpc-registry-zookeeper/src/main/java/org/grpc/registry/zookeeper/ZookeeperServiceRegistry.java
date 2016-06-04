package org.grpc.registry.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.grpc.registry.api.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperServiceRegistry implements ServiceRegistry {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ZookeeperServiceRegistry.class);

	private ZkClient zkClient;

	public ZookeeperServiceRegistry(String zkAddress) {
		zkClient = new ZkClient(zkAddress, Constant.ZK_SESSION_TIMEOUT,
				Constant.ZK_SESSION_TIMEOUT);
		LOGGER.debug("connect zookeeper");
	}

	@Override
	public void register(String serviceName, String serviceAddress) {
		// 创建 registry 节点（持久）
		String registryPath = Constant.ZK_REGISTRY_PATH;
		if (!zkClient.exists(registryPath)) {
			zkClient.createPersistent(registryPath);
			LOGGER.debug("create registry node: {}", registryPath);
		}
		// 创建 service 节点（持久）
		String servicePath = registryPath + "/" + serviceName;
		if (!zkClient.exists(servicePath)) {
			zkClient.createPersistent(servicePath);
			LOGGER.debug("create service node: {}", servicePath);
		}
		// 创建 address 节点（临时）
		String addressPath = servicePath + "/address-";
		String addressNode = zkClient.createEphemeralSequential(addressPath,
				serviceAddress);
		LOGGER.debug("create address node: {}", addressNode);
	}

	@Override
	public void unregister(String serviceName) {

	}
}
