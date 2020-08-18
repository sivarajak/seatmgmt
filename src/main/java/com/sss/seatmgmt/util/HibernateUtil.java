package com.sss.seatmgmt.util;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class HibernateUtil {
	
	/** The Constant THREAD_LOCAL. */
    private static final ThreadLocal<Session> THREAD_SESSION = new ThreadLocal<Session>();
	
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	
	public static void loadSessionFactory() {
		sessionFactory = new Configuration().configure("conf/hbm/hibernate.cfg.xml").buildSessionFactory();
	}
	
	/**
     * Current session.
     *
     * @return the session
     */
    public static Session getCurrentSession() {
        Session session = (Session) THREAD_SESSION.get();
        if (session == null || !session.isOpen()) {
            
            session = getSessionFactory().openSession();
            THREAD_SESSION.set(session);
        } 

        return session;
    }

    /**
     * Close session.
     */
    public static void closeSession() {
        // Open a Session, if this thread has none yet
        Session session = getCurrentSession();

        if (session != null) {
            session.close();
           // session = null;
            THREAD_SESSION.set(null);
        }
    }

    /**
     * This evictObject method remove the object from the session.
     *
     * @param obj
     *            the incoming object
     */
    public static void evictObject(Object obj) {
        Session session = getCurrentSession();
        session.evict(obj);
    }

    /**
     * Persist.
     *
     * @param objectToPersit
     *            the object to persit
     * @param refresh
     *            the refresh
     */
    public static void persist(Object objectToPersit, boolean refresh) {
        if (objectToPersit != null) {
            Session session = getCurrentSession();

            Transaction tx = null;


            try {
                tx = session.beginTransaction();

                if (tx.isActive()) {
                    session.save(objectToPersit);
                }

                tx.commit();
                session.evict(objectToPersit);
                if (refresh) {
                    session.refresh(objectToPersit);
                }

            } catch (HibernateException hbex) {
                tx.rollback();

            }
        }
    }

    /**
     * Persist.
     *
     * @param objectToPersit
     *            the object to persit
     * @throws Exception
     */
    public static void persist(Object objectToPersit) {
        persist(objectToPersit, false);
    }

    /**
     * Persist failed message.
     *
     * @param objectToPersit
     *            the Object to Persit.
     *
     */
    public static void persistData(Object objectToPersit) {
        if (objectToPersit != null) {
            Session session = getCurrentSession();

            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                if (tx.isActive()) {
                    session.saveOrUpdate(objectToPersit);
                }

                tx.commit();
                //session.evict(objectToPersit);
            } catch (HibernateException hbex) {
                tx.rollback();
                hbex.printStackTrace();
            }
            closeSession();
        }
    }
    
    /**
     * Persist failed message.
     *
     * @param objectToPersit
     *            the Object to Persit.
     *
     */
    public static void persistListObject(List<?> objectList) {
        if (objectList != null) {
            Session session = getCurrentSession();
            int i = 1;
            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                for(Object obj : objectList) {
	                if (tx.isActive()) {
	                    session.saveOrUpdate(obj);
	                }
	                if ( i % 20 == 0 ) { //20, same as the JDBC batch size
	                    //flush a batch of inserts and release memory:
	                    session.flush();
	                    session.clear();
	                }
	                i++;
                }

                tx.commit();
                //session.evict(objectToPersit);
            } catch (HibernateException hbex) {
                tx.rollback();
                hbex.printStackTrace();
            }
            closeSession();
        }
    }

    /**
     * method to delete given object from DB.
     *
     * @param objectTodelete
     *            the Object to delete.
     *
     */
    public static void deleteObject(Object objectTodelete) {
        if (objectTodelete != null) {
            Session session = getCurrentSession();

            try {
                session.delete(objectTodelete);

                // session.evict(objectToPersit);
            } catch (HibernateException hbex) {

            }
        }
    }

    /**
     * method to delete given object from DB.
     *
     * @param objectTodelete
     *            the Object to delete.
     *
     */
    public static void deleteObjectWithCommit(Object objectTodelete) {
        Transaction tx = null;

        if (objectTodelete != null) {
            Session session = getCurrentSession();

            try {
                tx = session.beginTransaction();

                if (tx.isActive()) {
                    session.delete(objectTodelete);
                }

                tx.commit();

                // session.evict(objectToPersit);
            } catch (HibernateException hbex) {

            }
        }
    }

    /**
     *
     * Execute query.
     *
     * @param queryName
     *            the query name
     *
     * @return the list<?>
     *
     *
     *
     */
    public static List<?> executeQuery(String queryName) {
        Query query = null;
        List<?> resultList = null;
        Session session = getCurrentSession();


        try {
            query = session.getNamedQuery(queryName);
            resultList = query.list();
        } catch (HibernateException hbex) {

        }

        return resultList;
    }

    /**
     * executeQuery.
     */

    /**
     * @param queryName
     *            the queryName
     * @param param
     *            the param
     * @param values
     *            the values
     * @return the object
     *
     */
    public static List<?> executeQuery(String queryName, String[] param,
            Object[] values) {
        Query query = null;
        List<?> resultList = null;
        Session session = getCurrentSession();


        try {
            query = session.getNamedQuery(queryName);


            if ((param != null) && (values != null)) {
                for (int i = 0; i < values.length; i++) {
                    applyNamedParameterToQuery(query, param[i], values[i]);
                }
            }

            resultList = query.list();
        } catch (HibernateException hbex) {

        }

        return resultList;
    }

    /**
     * Execute Create Query.
     *
     * @param queryName
     *            the query name
     * @return the list<?>
     *
     */
    public static List<?> executeCreateQuery(String queryName) {
        Query query = null;
        List<?> resultList = null;
        Session session = getCurrentSession();


        try {
            query = session.createQuery(queryName);
            resultList = query.list();
        } catch (HibernateException hbex) {

        }

        return resultList;
    }

    /**
     * Apply the given name parameter to the given Query object.
     *
     * @param queryObject
     *            the Query object
     * @param paramName
     *            the name of the parameter
     * @param value
     *            the value of the parameter
     */
    @SuppressWarnings("unchecked")
    private static void applyNamedParameterToQuery(Query queryObject,
            String paramName, Object value) {
        if (value instanceof Collection) {
            queryObject.setParameterList(paramName, (Collection) value);
        } else {
            if (value instanceof Object[]) {
                queryObject.setParameterList(paramName, (Object[]) value);
            } else {
                queryObject.setParameter(paramName, value);
            }
        }
    }

    /**
     * Execute executeSQLQuery Query.
     *
     * @param queryName
     *            the query name
     * @return the list<?>
     *
     */
    public static long executeSQLQuery(String queryName) {
        long messageIdValue = 0;
        Session session = getCurrentSession();


        try {
            Query query = session.createSQLQuery(queryName);
            Object seqValue = (Object) query.uniqueResult();
            messageIdValue = Long.valueOf(seqValue.toString());
        } catch (HibernateException hbex) {

        }

        return messageIdValue;
    }

    /**
     * executeUpdate.
     *
     * @param queryName
     *            the queryName.
     * @param param
     *            the param.
     * @param values
     *            the values'
     * @return int.
     */
    public static int executeUpdate(String queryName, String[] param, Object[] values) {
        Query query = null;
        int noOfRows = 0;
        Session session = getCurrentSession();

        try {
            query = session.getNamedQuery(queryName);

            if ((param != null) && (values != null)) {
                for (int i = 0; i < values.length; i++) {
                    applyNamedParameterToQuery(query, param[i], values[i]);
                }
            }

            noOfRows = query.executeUpdate();
        } catch (HibernateException hbex) {

        }

        return noOfRows;
    }

    /**
     * Execute create sql query.
     *
     * @param queryName
     *            the query name
     *
     * @return the list<?>
     */
    public static List<?> executeCreateSQLQuery(String queryName) {
        Query query = null;
        List<?> resultList = null;
        Session session = getCurrentSession();

        try {
            query = session.createSQLQuery(queryName);
            resultList = query.list();
        } catch (HibernateException hbex) {

        }

        return resultList;
    }

	
	

}
