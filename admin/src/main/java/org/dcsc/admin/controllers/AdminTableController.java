package org.dcsc.admin.controllers;

import org.dcsc.admin.view.model.table.ViewModel;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class AdminTableController {
    @RequestMapping(value = "/admin/r/table/{table-id}/headers", method = RequestMethod.GET)
    public List<String> getTableHeaders(@PathVariable("table-id") String table) {
        return Arrays.asList("Event Name", "Date", "Time", "Location", "Status", "Actions");
    }

    @RequestMapping(value = "/admin/r/table/{table-id}", method = RequestMethod.GET)
    public List getRows(@PathVariable("table-id") String table,
                        @RequestParam(value = "pageIndex", required = false) int pageIndex,
                        @RequestParam(value = "pageSize", required = false) int pageSize) {
        ViewModel vm1 = new ViewModel();
        vm1.put("name", "event1");
        vm1.put("date", "01/01/2016");
        vm1.put("time", "12:00:00 ");
        vm1.put("here", "here");
        vm1.put("done", "done");
        vm1.put("action", "action");
        ViewModel vm2 = new ViewModel();
        vm2.put("name", "event1");
        vm2.put("date", "01/01/2016");
        vm2.put("time", "12:00:00 ");
        vm2.put("here", "here");
        vm2.put("done", "done");
        vm2.put("action", "action");

        return Arrays.asList(vm1, vm2);
    }
}