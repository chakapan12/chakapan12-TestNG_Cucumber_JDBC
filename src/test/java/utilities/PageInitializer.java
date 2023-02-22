package utilities;

import pages.LoginPage;

public class PageInitializer extends BaseClass{
	
	public static LoginPage lp;


   
    
    public static void initialize() {
    	lp = new LoginPage();
    
    } 

}
