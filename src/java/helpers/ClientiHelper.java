/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.logging.Level;
import java.util.logging.Logger;
import models.Clienti;
import models.Vanzari;
import models.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author CyNEXX
 */
public class ClientiHelper {
    
    Session session = null;
    org.hibernate.Transaction tx;
    static Clienti tempClient;
    
    public ClientiHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public List getClientList(int startRes, int PAGESIZE) {
        List<Clienti> clientList = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from models.Clienti c where c.clientActiv=1");
            q.setFirstResult(startRes);
            q.setMaxResults(PAGESIZE);
            clientList = (List<Clienti>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientList;
    }

    public void dezactiveazaClient(int id_Client) {
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Clienti client = (Clienti) session.get(Clienti.class, id_Client);
            client.setClientActiv((byte) 0);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            Logger.getLogger(HibernateUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Clienti getThisClient(int id) {
        Clienti client = null;
        List<Clienti> clientList = null;
        try {
            tx = session.beginTransaction();
            client = (Clienti) session.get(Clienti.class, id);
            
            
            tempClient = new Clienti(client.getIdClient(), client.getNumeClient(), client.getPrenumeClient(),
                    client.getAdresaClient(), client.getTelefonClient(), client.getNrVanzari(),
                    client.getNrProduse(), client.getCifraVanzari());
            
            if (client.getCifraVanzari() == null) {
                tempClient.setCifraVanzari(0.0);
            } else {
                tempClient.setCifraVanzari(client.getCifraVanzari());
            }
            if (client.getNrProduse() == null) {
                tempClient.setNrProduse(0);
            } else {
                tempClient.setNrProduse(client.getNrProduse());
            }
            if (client.getNrVanzari() == null) {
                tempClient.setNrVanzari(0);
            } else {
                tempClient.setNrVanzari(client.getNrVanzari());
            }

            session.getTransaction().commit();
        } catch (HibernateException ex) {
        }
        return client;
    }
    
    public Clienti getSimpleClient(int id) {
        Clienti client = null;
        try {
            tx = session.beginTransaction();
            client = (Clienti) session.load(Clienti.class, id);
            
            session.getTransaction().commit();
        } catch (HibernateException ex) {
        }
        return client;
    }
    
    public List getClientSearch(String searchText, int startRes, int PAGESIZE) {
        List<Clienti> clientList = null;
        String searchString = ("%" + searchText + "%").toLowerCase().trim();
        try {
            tx = session.beginTransaction();     
            Query q = session
                    .createQuery("from Clienti as c where c.clientActiv = '1' and (c.numeClient like :textToSearch or c.prenumeClient like :textToSearch)")
                    .setParameter("textToSearch", searchString);
            
            q.setFirstResult(startRes);
            q.setMaxResults(PAGESIZE);
            clientList = (List<Clienti>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < clientList.size(); i++) {
        }
        return clientList;
    }
    
    public Clienti getFullLoadClient(int id) {
        List<Clienti> clientList = null;
        Clienti fullLoadClient = null;
        try {
            tx = session.beginTransaction();
            Query q = session
                    .createQuery("from Clienti as c left join fetch c.vanzaris where c.clientActiv = 1 and c.idClient =" + id);
            
            fullLoadClient = (Clienti) q.uniqueResult();


            tx.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullLoadClient;
    }
    
    public boolean saveClient(String nume, String prenume, String adresa, String telefon) {
        int newId = 0;
        boolean succes = false;
        Clienti client;
        try {
            client = new Clienti(nume, prenume, adresa, telefon);
            client.setClientActiv((byte) 1);
            client.setCifraVanzari(0.0);
            client.setNrProduse(0);
            client.setNrVanzari(0);
            tx = session.beginTransaction();
            /* newId = (Integer)*/ session.save(client);
            session.getTransaction().commit();
            succes = true;
        } catch (HibernateException hibernateException) {
            succes = false;
        }
        return succes;
    }
    
    public long getNumbers() {
        long recordCount = 0;
        try {
            tx = session.beginTransaction();
            recordCount = (Long) session.createQuery("select count(*) from Clienti as c where c.clientActiv = 1").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recordCount;
    }
    

    
    public void saveCV(int id, Set<Vanzari> hdv) {
        Clienti client = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            client = (Clienti) session.get(Clienti.class, id);
            client.setVanzaris((HashSet) hdv);
            session.save(client);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
        }
    }
    
    public long getSearchNr(String searchText, int startRes) {
        String searchString = ("%" + searchText + "%").toLowerCase().trim();
        long recordCount = 0;
        try {
            tx = session.beginTransaction();
            Query q = session
                    .createQuery("select count(*) from Clienti as c where c.clientActiv = 1 and (c.numeClient like :textToSearch or c.prenumeClient like :textToSearch)")
                    .setParameter("textToSearch", searchString);
            recordCount = (Long) q.iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recordCount;
    }
    
    public void modificaClient(Clienti inputClient) {

        tx = session.beginTransaction();
        if (!inputClient.getNumeClient().contentEquals(tempClient.getNumeClient())) {
            tempClient.setNumeClient(inputClient.getNumeClient());
        }
        if (!inputClient.getPrenumeClient().contentEquals(tempClient.getPrenumeClient())) {
            tempClient.setPrenumeClient(inputClient.getPrenumeClient());
        }
        if (!inputClient.getAdresaClient().contentEquals(tempClient.getAdresaClient())) {
            tempClient.setAdresaClient(inputClient.getAdresaClient());
        }
        if (!inputClient.getTelefonClient().contentEquals(tempClient.getTelefonClient())) {
            tempClient.setTelefonClient(inputClient.getTelefonClient());
        }
        tempClient.setClientActiv((byte) 1);
        session.update(tempClient);
        session.getTransaction().commit();
    }
}
