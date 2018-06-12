package org.ryanstrong.security;

import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.logging.Logger;
//import org.springframework.security.web.access.AccessDeniedHandler;

public class AccessDeniedHandler {
//        implements DeniedHandler {
    public static final Logger LOG = (Logger) LoggerFactory.getLogger(AccessDeniedHandler.class);

//    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) throws IOException,
            ServletException{
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if(auth != null){
//        LOG.warn("User: " + auth.getName() + " attempted to access the protected URL: " + request.getRequestURI());
        }
//        response.sendRedirect(request.getContextPath()+ "/accessDenied");
    }
//}
