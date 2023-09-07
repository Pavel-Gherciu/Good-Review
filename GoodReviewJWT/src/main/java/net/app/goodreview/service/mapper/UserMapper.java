package net.app.goodreview.service.mapper;

import net.app.goodreview.dto.request.UpdateUserDto;
import net.app.goodreview.model.User;

public class UserMapper {

    public static User toUser(UpdateUserDto userDto) {
        return User.builder()
                .icon(userDto.getIcon())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
    }
}
