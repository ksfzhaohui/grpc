package org.grpc.registry.api;

public interface ServiceRegistry {

	/**
	 * 注册服务
	 * 
	 * @param serviceName
	 *            服务名称
	 * @param serviceAddress
	 *            服务地址
	 */
	public void register(String serviceName, String serviceAddress);

	/**
	 * 取消注册
	 * 
	 * @param serviceName
	 *            服务名称
	 */
	public void unregister(String serviceName);
}
