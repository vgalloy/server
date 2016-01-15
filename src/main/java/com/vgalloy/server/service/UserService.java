package com.vgalloy.server.service;

import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.dao.model.entity.User;

import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 */
public interface UserService {
    /**
     * Permet d'obtenir l'ensemble des informations de tout les utilisateurs.
     *
     * @return Un liste contenant l'ensemble des utilisateurs
     */
    List<User> getAll();

    /**
     * Permet de créer/modifier un utilisateur.
     *
     * @param user l'utilisateur
     * @return L'utilisateur créé.
     */
    User createOrUpdate(User user);

    /**
     * Permet d'obtenir un utilisateur par son nom d'utilisateur.
     *
     * @param username Le nom d'utilisateur
     * @return Les informations sur l'utilisateur
     */
    User getByUsername(String username);

    /**
     * Permet de changer le mot de passe de l'utilisateur.
     *
     * @param username    Le nom d'utilisateur
     * @param newPassword Le nouveau mot de passe
     * @return L'utilisateur modifié
     */
    User changePassword(String username, String newPassword);

    /**
     * Permet de changer le rôle de l'utilisateur.
     *
     * @param username      Le nom d'utilisateur
     * @param securityLevel Le nouveau role
     * @return L'utilisateur modifié
     */
    User changeRole(String username, SecurityLevel securityLevel);

    /**
     * Permet de supprimer le mot de passe.
     *
     * @param username Le nom d'utilisateur
     */
    void deleteByUsername(String username);
}
