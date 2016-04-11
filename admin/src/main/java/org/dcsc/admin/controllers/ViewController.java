package org.dcsc.admin.controllers;

import com.google.common.collect.Sets;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@RequestMapping("/admin/view")
@Controller("adminViewController")
public class ViewController {
    private static final Set<String> COMMON_VIEWS = Sets.newHashSet("sidebar", "header");
    private static final String COMMON_VIEW_FORMAT = "admin/view/common/%s::altair";
    private static final String STANDARD_VIEW_FORMAT = "admin/view/%s::altair";
    private static final String EDIT_VIEW_FORMAT = "admin/view/edit/%s::altair";

    @RequestMapping(value = "/{viewId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getView(@PathVariable("viewId") String viewId) {
        String viewNameFormat = STANDARD_VIEW_FORMAT;

        if (COMMON_VIEWS.contains(viewId)) {
            viewNameFormat = COMMON_VIEW_FORMAT;
        }

        return String.format(viewNameFormat, viewId);
    }

    @RequestMapping(value = "/{viewId}/edit", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getEdit(@PathVariable("viewId") String viewId) {
        return String.format(EDIT_VIEW_FORMAT, viewId);
    }
}
