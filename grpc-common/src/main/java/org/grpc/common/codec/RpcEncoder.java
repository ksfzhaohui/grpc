package org.grpc.common.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.grpc.common.util.SerializationUtil;

/**
 * RPC 编码器
 * 
 * @author zhaohui
 * 
 */
public class RpcEncoder extends MessageToByteEncoder<Object> {

	private Class<?> genericClass;

	public RpcEncoder(Class<?> genericClass) {
		this.genericClass = genericClass;
	}

	@Override
	public void encode(ChannelHandlerContext ctx, Object in, ByteBuf out)
			throws Exception {
		if (genericClass.isInstance(in)) {
			byte[] data = SerializationUtil.serialize(in);
			out.writeInt(data.length);
			out.writeBytes(data);
		}
	}
}