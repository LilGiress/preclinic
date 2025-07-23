package com.medecineWebApp.Configuration.service;



import com.medecineWebApp.Configuration.dto.UserDTO;
import com.medecineWebApp.Configuration.models.user.Users;
import com.medecineWebApp.Configuration.payload.request.ResetPasswordRequest;
import com.medecineWebApp.Configuration.payload.response.ResponseMessage;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UserService {

    /**
     *
     * @param email
     * @return
     */
    UserDTO forgotPassword(String email);

    /**
     *
     * @param userId
     * @return
     */
    ResponseMessage<UserDTO> getUserById(Long userId);

    /**
     *
     * @param page
     * @param size
     * @return
     */
    Page<UserDTO> getAllUsers(int page, int size);

    /**
     *
     * @param userId
     */
    void deleteUserById(Long userId);

    /**
     *
     * @param userId
     * @param users
     * @return
     */
    ResponseMessage<UserDTO> updateUser(Long userId, Users users);
    /**
     * permet de recuperer l'utilisateur authentifier
     *
     * @return Users
     */
    UserDTO getCurrentUser(String token);


    /**
     *
     *
     * @param request
     *
     */
    void resetPassword(ResetPasswordRequest request);

    List<UserDTO> getUsersByRole(List<String> roles);

}
