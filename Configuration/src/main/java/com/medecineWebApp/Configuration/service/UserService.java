package com.medecineWebApp.Configuration.service;



import com.medecineWebApp.Configuration.models.user.User;
import com.medecineWebApp.Configuration.payload.request.ChangePasswordRequest;
import com.medecineWebApp.Configuration.payload.response.PageResponse;
import com.medecineWebApp.Configuration.payload.response.ResponseMessage;
import com.medecineWebApp.Configuration.payload.response.UserResponse;

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
    ResponseMessage<User> getUserById(Long userId);

    /**
     *
     * @param page
     * @param size
     * @return
     */
    PageResponse<UserResponse> getAllUsers(int page, int size);

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
    ResponseMessage<User> updateUser(Long userId, User user );
    /**
     * permet de recuperer l'utilisateur authentifier
     *
     * @return Users
     */
    Optional<User> getCurrentUser(String token);





}
