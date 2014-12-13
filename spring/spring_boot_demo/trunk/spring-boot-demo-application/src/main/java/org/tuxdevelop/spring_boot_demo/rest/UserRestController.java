package org.tuxdevelop.spring_boot_demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.tuxdevelop.spring_boot_demo.service.UserServiceBean;
import org.tuxdevelop.spring_boot_demo.service.dto.UserDTO;
import org.tuxdevelop.spring_boot_demo.service.intf.UserService;

@RestController
@RequestMapping(value = UserService.BASE_URI, consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController implements UserService {

    @Autowired
    private UserServiceBean userServiceBean;

    @RequestMapping(method = RequestMethod.GET)
    public UserDTO getUser(@RequestParam(value = "userName") final String userName) {
        return userServiceBean.getUserByUserName(userName);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDTO addUser(@RequestBody final UserDTO userDTO) {
        return userServiceBean.addUser(userDTO);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateUser(@RequestBody final UserDTO userDTO) {
        userServiceBean.updateUser(userDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam(value = "userName") final String userName) {
        userServiceBean.deleteUser(userName);
    }
}
