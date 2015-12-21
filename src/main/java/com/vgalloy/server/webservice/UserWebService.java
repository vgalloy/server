package com.vgalloy.server.webservice;

import com.vgalloy.server.dao.model.entity.User;
import com.vgalloy.server.webservice.dto.UserDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by Vincent Galloy on 09/12/15.
 */
public interface UserWebService {
    List<User> getAll();

    User create(String username, UserDto userDto);

    User getByUsername(String username);

    User update(String username, UserDto userDto);

    void delete(String username);
}
