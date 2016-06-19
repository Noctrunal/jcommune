package org.jtalks.jcommune.web.controller;

import org.jtalks.common.model.entity.Group;
import org.jtalks.common.model.entity.User;
import org.jtalks.common.model.permissions.GeneralPermission;
import org.jtalks.jcommune.service.GroupService;
import org.jtalks.jcommune.service.security.AclClassName;
import org.jtalks.jcommune.service.security.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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
     * with checking permission using {@link #checkPermission(Map)}
     */
    @RequestMapping(value = "/group/list", method = RequestMethod.GET)
    public ModelAndView showGroupsWithUsers() {
        Map<Group, List<User>> groupsWithUsers = groupService.getAllGroupsWithUsers();
        checkPermission(groupsWithUsers);
        return new ModelAndView("userGroups").addObject("groups", groupsWithUsers);
    }

    /**
     * Checking permission using {@link org.jtalks.jcommune.service.security.PermissionService}
     */
    private void checkPermission(Map<Group, List<User>> groupsWithUsers) {
        for (Group group : groupsWithUsers.keySet()) {
            permissionService.checkPermission(group.getId(), AclClassName.GROUP, GeneralPermission.ADMIN);
        }
    }
}
