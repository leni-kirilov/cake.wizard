
package bg.cakerecipes.searchservices.service;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

/**
 * This class was generated by Apache CXF 2.7.5
 * 2013-05-31T00:05:50.716+03:00
 * Generated source version: 2.7.5
 * 
 */
public final class SearchService_SearchServiceImplPort_Client {

    private static final QName SERVICE_NAME = new QName("http://service.searchservices.cakerecipes.bg/", "SearchServiceImplService");

    private SearchService_SearchServiceImplPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = SearchServiceImplService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        SearchServiceImplService ss = new SearchServiceImplService(wsdlURL, SERVICE_NAME);
        SearchService port = ss.getSearchServiceImplPort();  
        
        {
        System.out.println("Invoking buildIndexTree...");
        java.util.List<java.lang.Object> _buildIndexTree_arg0 = new java.util.ArrayList<java.lang.Object>();
        java.lang.Object _buildIndexTree_arg0Val1 = null;
        _buildIndexTree_arg0.add(_buildIndexTree_arg0Val1);
        port.buildIndexTree(_buildIndexTree_arg0);


        }
        {
        System.out.println("Invoking query...");
        java.lang.String _query_arg0 = "_query_arg0-491685184";
        java.util.List<bg.cakerecipes.searchservices.service.Entry> _query__return = port.query(_query_arg0);
        System.out.println("query.result=" + _query__return.get(0).getId());
        System.out.println("query.result=" + _query__return.get(0).getRank());


        }

        System.exit(0);
    }

}
