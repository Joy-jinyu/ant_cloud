package com.gree.filter;

import com.alibaba.fastjson.JSON;
import com.gree.exception.KellyException;
import com.gree.result.RestErrorResponse;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class ErrorFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().containsKey("throwable");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext rc = RequestContext.getCurrentContext();
        Throwable throwable = rc.getThrowable();
        RestErrorResponse restErrorResponse = getResponseInfo(throwable);
        rc.remove("throwable");
        rc.setResponseBody(JSON.toJSONString(restErrorResponse));
        rc.getResponse().setContentType("application/json;charset=utf-8");
        return null;
    }

    private RestErrorResponse getResponseInfo(Throwable exception){
        RestErrorResponse resultVO;
        Throwable error = getKellyException(exception,0,5);
        if(error instanceof KellyException){
            KellyException kellyException = (KellyException)error;
            resultVO = new RestErrorResponse(kellyException.getCode(),kellyException.getMessage(),kellyException.getStackTrace(),new Date(),kellyException.getName());
        }else{
            logger.debug("error:{},{},{}",error,error.getCause(),error.getMessage());
            String msg = error.getCause() == null ? error.getMessage() : error.getCause().getMessage();
            resultVO = new RestErrorResponse(500,msg,error.getStackTrace(),new Date(), error.getClass().getName());
        }
        return resultVO;
    }

    private Throwable getKellyException(Throwable error,Integer now,Integer max){
        if(now < max) {
            if (!(error instanceof KellyException)) {
                return error.getCause() == null ? error : getKellyException(error.getCause(),now+1,max);
            } else {
                return error;
            }
        }else{
            return error;
        }
    }
}
