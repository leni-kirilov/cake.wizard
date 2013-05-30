
package bg.cakerecipes.searchservices.service;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.7.5
 * 2013-05-31T00:05:13.951+03:00
 * Generated source version: 2.7.5
 * 
 */
 
public class SearchServiceServer{

    protected SearchServiceServer() throws Exception {
        System.out.println("Starting Server");
        Object implementor = new bg.cakerecipes.searchservices.service.SearchServiceImpl();
        String address = "http://localhost:9090/SearchServiceImplPort";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws Exception { 
        new SearchServiceServer();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
 
 