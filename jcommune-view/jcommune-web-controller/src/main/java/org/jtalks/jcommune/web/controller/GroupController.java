package org.jtalks.jcommune.web.controller;

import org.jtalks.jcommune.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller handles actions with groups
 * like display list of groups and number of users in it.
 *
 * @author Oleg Tkachenko
 */

@Controller
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @RequestMapping(value = "/group/list", method = RequestMethod.GET)
    public ModelAndView showGroupsWithNumberOfUsers() {
        return new ModelAndView("userGroups").addObject("groups", groupService.getAll());
    }
}
