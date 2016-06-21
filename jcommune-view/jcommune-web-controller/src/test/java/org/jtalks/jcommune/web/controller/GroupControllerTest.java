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

import org.jtalks.jcommune.plugin.api.web.dto.json.JsonResponse;
import org.jtalks.jcommune.plugin.api.web.dto.json.JsonResponseStatus;
import org.jtalks.jcommune.service.GroupService;
import org.jtalks.jcommune.service.security.PermissionService;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.testng.Assert.assertEquals;

/**
 * @author Oleg Tkachenko
 */
public class GroupControllerTest {
    private GroupController controller;
    @Mock
    private GroupService groupService;
    @Mock
    private PermissionService permissionService;


    @BeforeMethod
    public void init() {
        initMocks(this);
        controller = new GroupController(groupService, permissionService);
    }

    @Test
    public void testShowGroupsWithUsers() throws Exception {
        assertViewName(controller.showGroupsWithUsers(), "userGroups");
    }

    @Test
    public void testGetGroupsWithUsers() throws Exception {
        JsonResponse expected = controller.getGroupsWithUsers();
        assertEquals(expected.getStatus(), JsonResponseStatus.SUCCESS);
    }
}