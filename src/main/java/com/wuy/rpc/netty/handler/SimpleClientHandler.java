package com.wuy.rpc.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.wuy.rpc.netty.client.DefaultFuture;
import com.wuy.rpc.netty.handler.param.Response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SimpleClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		try {
			if ("ping".equals(msg.toString())) {
				ctx.channel().writeAndFlush("ping\r\n");
				return;
			}
			Response response = JSONObject.parseObject(msg.toString(),
					Response.class);
			DefaultFuture.recive(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
