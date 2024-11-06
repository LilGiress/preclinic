package com.medecineWebApp.Configuration.service;



import com.medecineWebApp.Configuration.dto.UserDTO;
import com.medecineWebApp.Configuration.models.user.User;
import com.medecineWebApp.Configuration.payload.request.ChangePasswordRequest;
import com.medecineWebApp.Configuration.payload.response.ResponseMessage;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.Optional;

public interface UserService {
    /**
     *
     * @param request
     * @param userConnected
     */
    void changePassword(ChangePasswordRequest request, Principal userConnected);

    /**
     *
     * @param email
     * @return
     */
    Optional<User> forgotPassword(String email);

    /**
     *
     * @param request
     */
    void updatepassword(ChangePasswordRequest request);

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
     * @param user
     * @return
     */
    ResponseMessage<UserDTO> updateUser(Long userId, User user );
    /**
     * permet de recuperer l'utilisateur authentifier
     *
     * @return Users
     */
    Optional<User> getCurrentUser(String token);





}
