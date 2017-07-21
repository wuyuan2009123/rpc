package com.wuy.rpc.netty.media;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.wuy.rpc.netty.handler.param.ServerRequest;
import com.wuy.rpc.pojo.Response;

public class Media {
	public static Map<String,BeanMethod> beanMap;
    static{
        beanMap= new HashMap<String,BeanMethod>();
    }
     
    private static Media m=null;
    private Media(){
         
    }
     
     
    public static Media newInstance() {
        if(m==null){
            m=new Media();
        }
        return m;
    }
    
    //反射处理业务代码
    public Response process(ServerRequest request) {
        Response result=null;
        try {
            String command = request.getCommand();
            BeanMethod beanMethod = beanMap.get(command);
            if(beanMethod==null){
                return null;
            }
            Object bean = beanMethod.getBean();
            Method m = beanMethod.getMethod();
            Class<?>[] parameterTypes = m.getParameterTypes();
            Object[] parameters = request.getParameters();
            Object[] params=new Object[parameterTypes.length];
            if(null!=parameterTypes){
            	for(int i=0;i<parameterTypes.length;i++){
            		Object param = JSONObject.parseObject(JSONObject.toJSONString(parameters[i]), parameterTypes[i]);
            		params[i]=param;
                }
            }
            result = (Response) m.invoke(bean, params);
            result.setId(request.getId());
        }catch (Exception e) {
            e.printStackTrace();
        }
         
        return result;
    }
}
