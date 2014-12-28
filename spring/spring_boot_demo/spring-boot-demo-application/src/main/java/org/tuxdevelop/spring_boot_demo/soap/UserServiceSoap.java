package org.tuxdevelop.spring_boot_demo.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tuxdevelop.spring_boot_demo.service.UserServiceBean;
import org.tuxdevelop.spring_boot_demo.service.dto.UserDTO;
import org.tuxdevelop.spring_boot_demo.service.intf.UserService;

@Service
public class UserServiceSoap implements UserService {

	@Autowired
	private UserServiceBean userServiceBean;

	@Override
	public UserDTO getUser(final String userName) {
		return userServiceBean.getUserByUserName(userName);
	}

	@Override
	public UserDTO addUser(final UserDTO userDTO) {
		return userServiceBean.addUser(userDTO);
	}

	@Override
	public void updateUser(final UserDTO userDTO) {
		userServiceBean.updateUser(userDTO);
	}

	@Override
	public void deleteUser(final String userName) {
		userServiceBean.deleteUser(userName);
	}
}
