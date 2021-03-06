package at.FH.Database;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import at.FH.Task.Bachelor;
import at.FH.Task.Master;
import at.FH.Task.Project;
import at.FH.Task.Task;
import at.FH.User.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 * This Class handles the communication with the database
 *
 * @author Stettinger
 */

public class HibernateSupport {

    private static SessionFactory sessionFactory;

    static {
        System.out.println("HibernateSupport: Constructor");
        init();
    }

    public static void create() {
        // function is not necessary it only activates the static construction above
    }

    public static void init() {
        File configFile = new File("src/main/resources/hibernate.cfg.xml");

        Configuration configuration = new Configuration();

        //TODO add all classes you want to annotate
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Assistant.class);
        configuration.addAnnotatedClass(Admin.class);
        configuration.addAnnotatedClass(Registration.class);
        configuration.addAnnotatedClass(Task.class);
        configuration.addAnnotatedClass(Project.class);
        configuration.addAnnotatedClass(Master.class);
        configuration.addAnnotatedClass(Bachelor.class);


        configuration.configure(configFile);

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    private static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public static void beginTransaction() {
        getCurrentSession().beginTransaction();
    }

    public static void commitTransaction() {
        getCurrentSession().getTransaction().commit();
    }

    public static boolean commit(Object obj) {
        try {
            getCurrentSession().saveOrUpdate(obj);
        } catch (HibernateException e) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> readMoreObjects(Class<?> classToRetrieve, List<Criterion> criterions) {
        beginTransaction();
        Criteria criteria = getCurrentSession().createCriteria(classToRetrieve);
        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        List<T> result = criteria.list();
        commitTransaction();
        return result;
    }

    public static <T> T readOneObject(Class<?> classToRetrieve, List<Criterion> criterions) {
        List<T> result = readMoreObjects(classToRetrieve, criterions);
        return (result.size() > 0) ? (result.get(0)) : (null);
    }

    public static <T> T readOneObjectByID(Class<?> classToRetrieve, String id) {
        List<Criterion> criterions = new ArrayList<Criterion>();
        criterions.add(Restrictions.idEq(id));
        T result = readOneObject(classToRetrieve, criterions);
        return result;
    }

    public static <T> void deleteObject(T objectToDelete) {
        getCurrentSession().delete(objectToDelete);
    }
}
