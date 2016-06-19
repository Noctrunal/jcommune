package org.jtalks.jcommune.web.controller;

import org.jtalks.common.model.entity.Group;
import org.jtalks.common.model.permissions.GeneralPermission;
import org.jtalks.jcommune.service.GroupService;
import org.jtalks.jcommune.service.security.AclClassName;
import org.jtalks.jcommune.service.security.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * This controller handles actions with groups
 * like display list of groups and number of users in it.
 *
 * @author Oleg Tkachenko
 * @author Evgeniy Cheban
 */

@Controller
public class GroupController {
    private final GroupService groupService;

    private final PermissionService permissionService;

    @Autowired
    public GroupController(GroupService groupService, PermissionService permissionService) {
        this.groupService = groupService;
        this.permissionService = permissionService;
    }

    /**
     * Find all groups with list of users inside them
     * with checking permission using {@link #checkPermission(List)}
     */
    @RequestMapping(value = "/group/list", method = RequestMethod.GET)
    public ModelAndView showGroupsWithUsers() {
        return new ModelAndView("userGroups");
    }

    @RequestMapping(value = "/ajax/group/list", method = RequestMethod.GET)
    public @ResponseBody List<Group> getGroupsWithUsers() {
        List<Group> groups = groupService.getAll();
        checkPermission(groups);
        return groups;
    }

    /**
     * Checking permission using {@link org.jtalks.jcommune.service.security.PermissionService}
     */
    private void checkPermission(List<Group> groups) {
        for (Group group : groups) {
            permissionService.checkPermission(group.getId(), AclClassName.GROUP, GeneralPermission.ADMIN);
        }
    }
}
