package com.vgalloy.server.webservice;

import com.vgalloy.server.webservice.dto.AuthenticationDTO;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 06/01/16.
 */
public interface LoginWebService {
    boolean checkPassword(AuthenticationDTO authenticationDTO);
}
