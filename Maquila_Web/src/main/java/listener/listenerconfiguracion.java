/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import com.maquila.mservices.controller.InsumoJpaController;
import javax.persistence.EntityManager;
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persis");
        sce.getServletContext().setAttribute("conexion", emf);
        
//        InsumoJpaController insumu   = new InsumoJpaController(emf);
//        sce.getServletContext().setAttribute("insumo", insumu);
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
