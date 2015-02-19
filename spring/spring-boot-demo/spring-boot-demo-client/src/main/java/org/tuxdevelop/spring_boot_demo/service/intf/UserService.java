package org.tuxdevelop.spring_boot_demo.service.intf;

import org.tuxdevelop.spring_boot_demo.service.dto.UserDTO;

import javax.jws.WebService;

@WebService(targetNamespace = "org.tuxdevelop.spring_boot_demo")
public interface UserService extends CommonServiceInterface {

	public static final String BASE_URI = REST_API + "/users";

	public static final String GET_USER_URI = BASE_URI;

	public static final String ADD_USER_URI = BASE_URI;

	public static final String UPDATE_USER_URI = BASE_URI;

	public static final String DELETE_USER_URI = BASE_URI;

	public static final String SOAP_SERVICE_NAME = "userService";

	UserDTO getUser(final String userName);

	UserDTO addUser(final UserDTO userDTO);

	void updateUser(final UserDTO userDTO);

	void deleteUser(final String userName);
}
