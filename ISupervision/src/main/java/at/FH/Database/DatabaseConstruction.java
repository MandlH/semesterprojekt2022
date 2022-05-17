package at.FH.Database;

import at.FH.User.Admin;
import at.FH.User.User;
import org.hibernate.cfg.Configuration;


/**
 * Class for creating the database
 * @author Stettinger
 *
 */

public class DatabaseConstruction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start DB Construction");
		Configuration configuration = new Configuration();
		
		//TODO add classes
		configuration.addAnnotatedClass(User.class);
		configuration.addAnnotatedClass(Admin.class);
		
		configuration.configure("hibernate.cfg.xml");

		System.out.println("Finished");
	}
	
}
