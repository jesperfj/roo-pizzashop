package com.example.roo.pizzashop.app;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server();
        String dburl = System.getenv("DATABASE_URL");
        if(dburl!=null && dburl.startsWith("postgres://")) {
        	String[] s = dburl.split("://");
        	String[] s2 = s[1].split("@");
        	String[] s3 = s2[0].split(":");
        	System.setProperty("database.url","jdbc:postgresql://"+s2[1]+"?user="+s3[0]+"&password="+s3[1]);
        	System.out.println("Detected a DATABASE_URL pointing to a postgres database. Will set the database.url Java system property to the corresponding JDBC connect URL");
                System.out.println("Setting hibernate.dialect to org.hibernate.dialect.PostgreSQLDialect. Note that this will not override Hibernate settings in persistence.xml");
                System.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        }
        Connector connector = new SelectChannelConnector();
        connector.setPort(Integer.getInteger("jetty.port",8080).intValue());
        server.setConnectors(new Connector[] { connector });
	if(args.length<1) {
            System.out.println("Missing required argument: path_to_webapp");
            System.exit(1);
        }

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setDescriptor(args[0]+"/WEB-INF/web.xml");
        context.setResourceBase(args[0]);
        //context.setParentLoaderPriority(true);

        //context.setWar(args[0]);
        

        server.setHandler(context);
        System.out.println("Starting server...");
        server.start();
        server.join();
    }
}
