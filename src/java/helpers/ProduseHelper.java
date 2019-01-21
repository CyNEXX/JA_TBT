/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.HibernateUtil;
import models.Produse;
import models.Vanzari;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author CyNEXX
 */
public class ProduseHelper {

    Session session = null;
    org.hibernate.Transaction tx;
    static int MAXRES = 10;
    static Produse tempProdus;

    public ProduseHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List getProduseList(int startRes, int PAGESIZE) {
        List<Produse> produseList = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from models.Produse as p where p.produsActiv = 1");
            q.setFirstResult(startRes);
            q.setMaxResults(PAGESIZE);
            produseList = (List<Produse>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produseList;
    }

    public List getProduseListAvailable(int startRes, int PAGESIZE) {
        List<Produse> produseList = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Produse as p where p.produsActiv = 1 and p.stocProdus > 0");
            q.setFirstResult(startRes);
            q.setMaxResults(PAGESIZE);
            produseList = (List<Produse>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produseList;
    }

    public long getAvailableNumbers() {
        long recordCount = 0;
        try {
            tx = session.beginTransaction();
            recordCount = (Long) session.createQuery("select count(*) from Produse as p where p.produsActiv = 1 and p.stocProdus > 0").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recordCount;
    }


    public void dezactiveazaProdus(int id_Produs) {
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Produse produs = (Produse) session.get(Produse.class, id_Produs);
            produs.setProdusActiv((byte) 0);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            Logger.getLogger(HibernateUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Produse getThisProdus(int id) {
        Produse produs = null;
        try {
            tx = session.beginTransaction();
            produs = (Produse) session.get(Produse.class, id);
            tempProdus = new Produse();

            tempProdus.setIdProdus(id);
            tempProdus.setNumeProdus(produs.getNumeProdus());
            tempProdus.setDescriereProdus(produs.getDescriereProdus());
            tempProdus.setPret(produs.getPret());
            tempProdus.setTotalVandute(produs.getTotalVandute());
            tempProdus.setStocProdus(produs.getStocProdus());



            session.getTransaction().commit();
        } catch (HibernateException ex) {
        }
        return produs;
    }

    public Produse getSimpleProdus(int id) {
        Produse produs = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            produs = (Produse) session.load(Produse.class, id);

            session.getTransaction().commit();
        } catch (HibernateException ex) {
        }
        return produs;
    }

    public List getProduseSearch(String searchText, int startRes, int PAGESIZE) {
        List<Produse> produseList = null;
        String searchString = ("%" + searchText + "%").toLowerCase().trim();
        try {
            tx = session.beginTransaction();
            Query q = session
                    .createQuery("from models.Produse as p where p.produsActiv = 1 and (p.numeProdus like :textToSearch or p.descriereProdus like :textToSearch)")
                    .setParameter("textToSearch", searchString);
            q.setFirstResult(startRes);
            q.setMaxResults(PAGESIZE);
            produseList = (List<Produse>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produseList;
    }

    public int saveProdus(String nume, String descriere, int stoc, double pret) {
        int newId = 0;
        Produse produs;
        try {
            produs = new Produse();
            produs.setNumeProdus(nume);
            produs.setDescriereProdus(descriere);
            produs.setStocProdus(stoc);
            produs.setPret(pret);
            produs.setProdusActiv((byte) 1);
            produs.setTotalVandute(0);
            tx = session.beginTransaction();
            newId = (Integer) session.save(produs);
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
        }

        return newId;
    }

    public long getSearchNr(String searchText, int startRes) {
        String searchString = ("%" + searchText + "%").toLowerCase().trim();
        long recordCount = 0;
        try {
            tx = session.beginTransaction();
            Query q = session
                    .createQuery("select count(*) from Produse as p where p.produsActiv = 1 and (p.numeProdus like :textToSearch or p.descriereProdus like :textToSearch)")
                    .setParameter("textToSearch", searchString);
            recordCount = (Long) q.iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recordCount;
    }

    public long getNumbers() {
        long recordCount = 0;
        try {
            tx = session.beginTransaction();
            recordCount = (Long) session.createQuery("select count(*) from Produse as p where p.produsActiv = 1").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recordCount;
    }

    public void modificaProdus(Produse inputProdus) {



        tx = session.beginTransaction();
        if (!inputProdus.getNumeProdus().contentEquals(tempProdus.getNumeProdus())) {
            tempProdus.setNumeProdus(inputProdus.getNumeProdus());

        }
        if (!inputProdus.getDescriereProdus().contentEquals(tempProdus.getDescriereProdus())) {
            tempProdus.setDescriereProdus(inputProdus.getDescriereProdus());

        }
        if (inputProdus.getStocProdus().intValue() != tempProdus.getStocProdus().intValue()) {
            tempProdus.setStocProdus(inputProdus.getStocProdus());

        }
        if (inputProdus.getPret() != tempProdus.getPret()) {
            tempProdus.setPret(inputProdus.getPret());

        }
        tempProdus.setProdusActiv((byte) 1);
        session.update(tempProdus);
        session.getTransaction().commit();

    }

    public List getProduseShortList(int startRes, int PAGESIZE) {
        List<Produse> produseShortList = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("select p.idProdus, p.numeProdus, p.stocProdus, p.pret from Produse p where p.produsActiv = 1");
            q.setFirstResult(startRes);
            q.setMaxResults(PAGESIZE);
            produseShortList = (List<Produse>) q.list();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produseShortList;
    }

    public List getFullRes(List<Integer> al) {
        List<Produse> list = new ArrayList<>();

        try {
            Produse p = new Produse();
            p.setProdusActiv((byte) 1);
            tx = session.beginTransaction();
            Query q = session.createQuery(createTheOtherQ(al));
            list = (List<Produse>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List getSpecificRes(List<Integer> al) {

        List<Produse> list = new ArrayList<>();

        try {
            Produse p = new Produse();
            p.setProdusActiv((byte) 1);
            tx = session.beginTransaction();
            Query q = session.createQuery(createTheQ(al)).setProperties(p);
            list = (List<Produse>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String createTheQ(List<Integer> al) {
        StringBuilder sb = new StringBuilder();

        sb.append("from Produse as p where p.idProdus = '").append(al.get(0)).append("'");
        if (al.size() > 1) {
            for (int i = 1; i < al.size(); i++) {
                sb.append(" or  p.idProdus = '").append(al.get(i)).append("'");
            }
        }
        return sb.toString();
    }

    public String createTheOtherQ(List<Integer> al) {
        StringBuilder sb = new StringBuilder();
//        sb.append("from Produse as p LEFT JOIN FETCH p.vanzaris where (p.idProdus = '").append(al.get(0)).append("'");
        sb.append("from Produse as p left join fetch p.vanzaris where (p.idProdus = '").append(al.get(0)).append("'");

        if (al.size() > 1) {
            for (int i = 1; i < al.size(); i++) {
                sb.append(" or  p.idProdus = '").append(al.get(i)).append("'");
            }
        }
//        sb.append(") and p.produsActiv='1'");
        sb.append(") and p.produsActiv='1' group by p.idProdus");
        return sb.toString();
    }

    public void vanzare(int id, int cantitate) {
        Produse produs = null;
        try {
            /* org.hibernate.Transaction */
            tx = session.beginTransaction();
            produs = (Produse) session.get(Produse.class, id);
            produs.setStocProdus(produs.getStocProdus() - cantitate);


            session.update(produs);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
                throw ex;
            }
        }
    }

    public void savePV(int id, Set<Vanzari> hsv) {
        try {
            tx = session.beginTransaction();
            Produse produs = (Produse) session.get(Produse.class, id);
            produs.setVanzaris((HashSet) hsv);
            session.save(produs);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
                throw ex;
            }
        }
    }

}
