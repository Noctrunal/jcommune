/**
 * Copyright (C) 2011  JTalks.org Team
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.jtalks.jcommune.web.controller;

import org.jtalks.common.model.entity.Group;
import org.jtalks.common.model.permissions.GeneralPermission;
import org.jtalks.jcommune.plugin.api.web.dto.json.JsonResponse;
import org.jtalks.jcommune.plugin.api.web.dto.json.JsonResponseStatus;
import org.jtalks.jcommune.service.GroupService;
import org.jtalks.jcommune.service.security.AclClassName;
import org.jtalks.jcommune.service.security.PermissionService;
import org.jtalks.jcommune.web.dto.GroupDto;
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
    private final static String GROUP_PAGE = "userGroups";
    private final GroupService groupService;

    private final PermissionService permissionService;

    @Autowired
    public GroupController(GroupService groupService, PermissionService permissionService) {
        this.groupService = groupService;
        this.permissionService = permissionService;
    }

    /**
     * Get user groups page
     * with checking permission using {@link #checkPermission()} method.
     */
    @RequestMapping(value = "/group/list", method = RequestMethod.GET)
    public ModelAndView showGroupsWithUsers() {
        checkPermission();
        return new ModelAndView(GROUP_PAGE);
    }

    /**
     * Get user groups names with number of members in JSON
     * with checking permission using {@link #checkPermission()} method.
     */
    @RequestMapping(value = "/ajax/group/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getGroupsWithUsers() {
        List<GroupDto> groups = GroupDto.convertGroupList(groupService.getAll(),true);
        checkPermission();
        return new JsonResponse(JsonResponseStatus.SUCCESS, groups);
    }

    /**
     * Checking permission using {@link org.jtalks.jcommune.service.security.PermissionService}.
     */
    private void checkPermission() {
        for (Group group : groupService.getAll()) {
            permissionService.checkPermission(group.getId(), AclClassName.GROUP, GeneralPermission.ADMIN);
        }
    }
}
