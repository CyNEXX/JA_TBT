/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.faces.model.DataModel;
import models.Clienti;
import models.Vanzari;
import models.HibernateUtil;
import models.Produse;
import models.SDV;
import models.DDV;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author CyNEXX
 */
public class VanzariHelper {

    Produse produs;
    Clienti client;
    int idCos;
    int cantitateVanzare;
    double pretProdus;
    Vanzari vanzare;
    Produse produse;

    ClientiHelper clientiHelper;
    ProduseHelper produseHelper;
    VanzariHelper tranHelper;
    Produse prd_from_dm = null;
    Clienti clnt;

    Session session = null;
    org.hibernate.Transaction tx;

    public VanzariHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public long getNumbers() {
        long recordCount = 0;
        try {
            tx = session.beginTransaction();
            recordCount = (Long) session.createQuery("select count(distinct v.idComanda) from Vanzari as v").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recordCount;
    }

    public int getLastCos() {
        int lastValue = 0;
        List<Vanzari> vanzlist = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Vanzari as v ORDER BY v.idComanda DESC").setMaxResults(1);
            vanzlist = (List<Vanzari>) q.list();
            tx.commit();

            if (!vanzlist.isEmpty()) {
                lastValue = vanzlist.get(0).getIdComanda();
            } else {
                lastValue = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastValue;

    }

    public void complexSave(int idDv, DataModel dmProdList, Clienti client, List<Produse> listProd) {
        int itemuri = 0;
        double suma = 0;

        tx = session.beginTransaction();
        for (int i = 0; i < dmProdList.getRowCount(); i++) {
            dmProdList.setRowIndex(i);
            Produse actualProdus = listProd.get(i);
            prd_from_dm = (Produse) dmProdList.getRowData();

            if (prd_from_dm.getStocDorit() == 0) {
                continue;
            }
            if (actualProdus.getStocProdus() == 0) {
                continue;
            }

            if (actualProdus.getStocProdus() >= prd_from_dm.getStocDorit()) {
                Set<Vanzari> svn;
                itemuri = itemuri + prd_from_dm.getStocDorit();
                suma = suma + (actualProdus.getPret() * prd_from_dm.getStocDorit());

                vanzare = new Vanzari();

                vanzare.setClienti(client);
                vanzare.setProduse(actualProdus);
                vanzare.setIdComanda(idDv);
                vanzare.setCantitateVanzare(prd_from_dm.getStocDorit());
                vanzare.setPretProdus(actualProdus.getPret());
                vanzare.setTotalProdus(round(actualProdus.getPret() * prd_from_dm.getStocDorit()));

                actualProdus.addVanzare(vanzare);
                actualProdus.setStocProdus(actualProdus.getStocProdus() - prd_from_dm.getStocDorit());
                actualProdus.setTotalVandute(actualProdus.getTotalVandute() + prd_from_dm.getStocDorit());

                session.update(actualProdus);
                session.save(vanzare);
                client.addVanzare(vanzare);
                try {
                    client.setNrProduse(client.getNrProduse() + prd_from_dm.getStocDorit());
                } catch (NullPointerException npe) {
                    client.setNrProduse(prd_from_dm.getStocDorit());
                }
            }

        }
        try {
            client.setNrVanzari(client.getNrVanzari() + 1);
        } catch (NullPointerException npe) {
            client.setNrVanzari(1);
        }
        try {
            client.setCifraVanzari(client.getCifraVanzari() + suma);
        } catch (Exception e) {
            client.setCifraVanzari(suma);
        }
        session.update(client);

        tx.commit();
    }

    public Vanzari getThisVanzare(int id) {
        Vanzari trnz = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            vanzare = (Vanzari) session.get(Vanzari.class, id);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
        }
        return vanzare;
    }

    public List getVanzariList(int startRes, int PAGESIZE) {
        List<SDV> vanzariList = null;
        List<Object[]> objList = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("select v.idComanda, v.clienti.idClient, v.clienti.numeClient, v.clienti.prenumeClient, sum(v.cantitateVanzare), sum(v.totalProdus) from Vanzari as v group by v.idComanda DESC");/* where produse.idProdus between '" + startID + "' and '" + endID + "'");*/
            q.setFirstResult(startRes);
            q.setMaxResults(PAGESIZE);
            objList = (List<Object[]>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        vanzariList = new ArrayList<>();
        for (Object[] o : objList) {
            SDV tempSV;

            tempSV = new SDV();
            tempSV.setIdComanda((Integer) o[0]);
            tempSV.setIdClient((Integer) o[1]);
            tempSV.setNumeClient((String) o[2]);
            tempSV.setPrenumeClient((String) o[3]);
            tempSV.setCantitateVanzare((Long) o[4]);
            tempSV.setTotalProdus((Double) o[5]);
            vanzariList.add(tempSV);
        }
        return vanzariList;
    }

    public List getThisSale(int idComanda) {
        List<DDV> ddv = new ArrayList<>();
        List<Object[]> objList = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("select v.produse.numeProdus, v.produse.descriereProdus, v.cantitateVanzare, v.pretProdus, v.produse.idProdus, v.totalProdus from Vanzari as v where v.idComanda=" + idComanda);/* where clienti.idClient between '" + startID + "' and '" + endID + "'");*/
            objList = (List<Object[]>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Object[] o : objList) {

            DDV tempDV = new DDV();
            tempDV.setNumeProdus((String) o[0]);
            tempDV.setDescriereProdus((String) o[1]);
            tempDV.setCantitateTranz((int) o[2]);
            tempDV.setPretProdus((Double) o[3]);
            tempDV.setIdProdus((int) o[4]);
            tempDV.setTotalProdus((double) o[5]);
            ddv.add(tempDV);

        }
        return ddv;
    }

    public static double round(double value) {

        int places = 2;
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
