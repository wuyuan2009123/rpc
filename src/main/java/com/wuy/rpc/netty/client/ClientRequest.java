package com.wuy.rpc.netty.client;

import java.util.concurrent.atomic.AtomicLong;

public class ClientRequest {
	private final long id;
	private Object content;
	private static final AtomicLong aid = new AtomicLong(1);

	private String command;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public ClientRequest() {
		id = aid.incrementAndGet();
	}

	public long getId() {
		return id;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ClientRequest [id=" + id + ", content=" + content
				+ ", command=" + command + "]";
	}
	
	
}
