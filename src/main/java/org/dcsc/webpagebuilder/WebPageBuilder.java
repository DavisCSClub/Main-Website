package org.dcsc.webpagebuilder;

import org.springframework.ui.Model;

/**
 * Created by tktong on 7/28/2015.
 */
public interface WebPageBuilder {
    WebPageBuilder model(Model model);
    WebPageBuilder template(String template);
}
