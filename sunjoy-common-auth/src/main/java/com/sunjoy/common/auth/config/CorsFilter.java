package com.sunjoy.common.auth.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 *
 * @author liuganchao<740033486@qq.com>
 * @date 2018年7月23日
 */
public class CorsFilter implements HandlerInterceptor {

    static final String ORIGIN = "Origin";
  

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//String origin = request.getHeader(ORIGIN);	
		if (request.getHeader(HttpHeaders.ORIGIN) != null)
        {
	        response.setHeader("Access-Control-Allow-Origin", request.getHeader(HttpHeaders.ORIGIN));//* or origin as u prefer
	        response.setHeader("Access-Control-Allow-Credentials", "true");
	        response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
	        response.setHeader("Access-Control-Max-Age", "3600");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization,Pragma,Cache-Control");
        }
        if (request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
        }
        System.out.println("---------------拦截器开始------------------");
        try{
            response.setHeader("Content-type", "application/json;charset=UTF-8");

            String requestMethord = request.getRequestURI();//请求方法
            if(requestMethord==null){
                return false;
            }

            //获取请求参数
            JSONObject parameterMap = JSON.parseObject(new BodyReaderHttpServletRequestWrapper(request).getBodyString(request));
            String dataFrom = String.valueOf(parameterMap.get("dataFrom")); 

        }catch (Exception e){
            e.printStackTrace();
        }
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
