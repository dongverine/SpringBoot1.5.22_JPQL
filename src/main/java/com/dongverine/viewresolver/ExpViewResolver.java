package com.dongverine.viewresolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Slf4j
public class ExpViewResolver extends InternalResourceViewResolver {
    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String viewFile =request.getRealPath(this.getPrefix()+viewName+this.getSuffix());
        File reqFile=new File(viewFile);
        System.out.println(reqFile.getAbsolutePath()+":exists:"+reqFile.exists());
        if(!reqFile.exists()){
            viewName="default";
        }
        InternalResourceView view = (InternalResourceView) super.buildView(viewName);
        log.info("ExpViewResolver view class {}",view.getClass().toString());
        view.setAlwaysInclude(false);
        return null;
    }
}
