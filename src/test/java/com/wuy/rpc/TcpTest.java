package com.wuy.rpc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.wuy.rpc.netty.client.ClientRequest;
import com.wuy.rpc.netty.client.DefaultFuture;
import com.wuy.rpc.netty.client.TcpClient;
import com.wuy.rpc.netty.handler.param.Response;
import com.wuy.rpc.user.bean.User;

public class TcpTest {
	
	private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);
	
	public static void main(String[] args) {
		
		List<User> userList=new ArrayList<User>();
		User u = new User();
		u.setId(1L);
		u.setName("wuy");
		userList.add(u);
		
		ClientRequest request=new ClientRequest();
		/*request.setCommand("com.wuy.rpc.user.controller.UserConroller.save");
		Object[] parameters={u};*/
		/*request.setCommand("com.wuy.rpc.user.controller.UserConroller.saves");
		Object[] parameters={userList};*/
		request.setCommand("com.wuy.rpc.user.controller.UserConroller.add");
		Object[] parameters={1,1.2};
		request.setParameters(parameters);
		System.out.println("ThreadName : "+Thread.currentThread().getName()+"  ,reuqest:  "+request);
		Response response = TcpClient.send(request);
		System.out.println("ThreadName : "+Thread.currentThread().getName()+"  ,response:  "+response);
		
		/*List<User> userList=new ArrayList<User>();
		for(int i=0;i<=1000;i++){
			User user = new User();
			user.setId((long)i);
			user.setName("wuy"+i);
			userList.add(user);
		}
		
		for(User user:userList){
			final User u=user;
			EXECUTOR.submit(new Runnable() {
				public void run() {
					ClientRequest request=new ClientRequest();
					request.setCommand("com.wuy.rpc.user.controller.UserConroller.save");
					Object[] parameters={u};
					request.setParameters(parameters);
					System.out.println("ThreadName : "+Thread.currentThread().getName()+"  ,reuqest:  "+request);
					Response response = TcpClient.send(request);
					System.out.println("ThreadName : "+Thread.currentThread().getName()+"  ,response:  "+response);
				}
			});
		}
		
		EXECUTOR.shutdown();
 		while (true) {
 			if (EXECUTOR.isTerminated()) {
 				System.out.println("执行完成！");
 				break;
 			}
 			try {
 				System.out.println("sleep！");
 				Thread.sleep(6000);
 			} catch (InterruptedException e) {
 				e.printStackTrace();
 			}
 		}
 		System.out.println("shutdown after DefaultFuture.allDefaultFuture 的 size : "+DefaultFuture.allDefaultFuture.size());*/
	}
	

	
}
