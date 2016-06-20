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
package org.jtalks.jcommune.web.dto;

import org.jtalks.common.model.entity.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * This dto used for transferring data to client side.
 * To get more info see
 * {@link org.jtalks.jcommune.web.controller.GroupController}
 *
 * @author Oleg Tkachenko
 */
public class UserDto {
    private Long id;
    private String userName;

    public UserDto(User user) {
        this.id = user.getId();
        this.userName = user.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static List<UserDto> convertUserListToDto(List<User> users) {
        List<UserDto> dtoList = new LinkedList<>();
        if (Objects.nonNull(users)){
            for (User user : users) {
                dtoList.add(new UserDto(user));
            }
        }
        return dtoList;
    }

}
