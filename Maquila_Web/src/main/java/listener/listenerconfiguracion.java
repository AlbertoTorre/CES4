/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author Alex
 */
@WebListener()
public class listenerconfiguracion implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencexlm");
        sce.getServletContext().setAttribute("conexion", emf);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
