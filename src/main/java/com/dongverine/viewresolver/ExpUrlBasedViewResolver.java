package com.dongverine.viewresolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.Locale;

@Slf4j
public class ExpUrlBasedViewResolver extends UrlBasedViewResolver implements Ordered {
    @Override
    protected View loadView(String viewName, Locale locale) throws Exception {
        AbstractUrlBasedView view = buildView(viewName);
        View viewObj = (View) getApplicationContext().getAutowireCapableBeanFactory().initializeBean(view, viewName);
        // JSP 를 뷰객체로 사용 시 InternalResourceViewResolver 구현체를 상속받는 JSTLView ViewClass 를 사용하여 처리한다.
        if (viewObj instanceof JstlView) {
            JstlView jv = (JstlView) viewObj;
            /*
             * viewName 이 .jsp 로 끝나지 않을 시 다음 순위로 지정된 ViewResolver 가 처리할 수 있도록 null 리턴.
             */
            if (jv.getBeanName().indexOf(".jsp") == -1) {
                return null;
            }
        }
        return viewObj;
    }
}
