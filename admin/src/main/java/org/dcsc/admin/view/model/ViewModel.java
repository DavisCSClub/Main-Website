package org.dcsc.admin.view.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.ui.ModelMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ViewModel extends ModelMap {
}
