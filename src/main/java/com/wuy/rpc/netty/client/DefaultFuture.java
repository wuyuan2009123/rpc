package com.wuy.rpc.netty.client;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import com.wuy.rpc.netty.handler.param.Response;

public class DefaultFuture {
	public final static ConcurrentHashMap<Long, DefaultFuture> allDefaultFuture = new ConcurrentHashMap<Long, DefaultFuture>();
	private CountDownLatch count = new CountDownLatch(1);
	private Response response;

	public DefaultFuture(ClientRequest request) {
		allDefaultFuture.put(request.getId(), this);
	}

	// 主线程获取数据，首先要等待结果
	public Response get() {
		try {
			count.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.response;
	}

	public static void recive(Response response) {
		DefaultFuture df = allDefaultFuture.get(response.getId());
		if (df != null) {
			try {
				df.setResponse(response);
				df.count.countDown();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void setResponse(Response response) {
		this.response = response;
	}
}
