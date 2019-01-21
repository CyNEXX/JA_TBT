/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.ProduseHelper;
import helpers.VanzariHelper;
import helpers.ClientiHelper;
import java.io.Serializable;
import static java.lang.Math.toIntExact;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import models.Clienti;
import models.Vanzari;
import models.Produse;
import models.SDV;

/**
 *
 * @author CyNEXX
 */
@ManagedBean(eager = true)
@Named(value = "vanzariController")
@RequestScoped
public class VanzariController implements Serializable {

    int startId;
    int endId;
    DataModel clientTitles;
    DataModel sumarVanzari;
    Clienti client;
    DataModel produseTitles;
    DataModel tranzTitles;
    ClientiHelper clientiHelper;
    DataModel vanzariTitles;

    ProduseHelper produseHelper;
    VanzariHelper vanzariHelper;
    private static String search = "";
    private static int recordCount = 1000;
    private static int prodQ = 0;
    private static int prodAvailable = 0;
    private static final int PAGESIZE = 10;
    static int startRes = 0;
    int currentIndex;
    private static String currentPage = "index";
    private static String lastPage;

    private static Clienti tempClient;
    static Produse tempProdus;
    static Vanzari tempVanzare;
    private static int id;
    private static int personID;

    static boolean isSearch = false;

    private static int staticId = 0;

    private static String staticNume = "";

    private static int staticIdClient = 0;
    private static String staticNumeClient = "";
    private static String staticPrenumeClient = "";
    private static String staticAdresaClient = "";
    private static String staticTelefonClient = "";
    private static String staticNrVanzariClient = "";
    private static String staticNrProduseClient = "";
    private static String staticCifraVanzari = "";
    static int tempID;

    public Clienti selectedClient;
    public Produse selectedProdus;
    public Vanzari selectedVanzare;
    public String staticMessage;

    private Produse currentProdus = new Produse();
    private Vanzari currentTranz = new Vanzari();
    private Clienti currentClient;
    private int selectedItemIndex;

    private Integer idProdus;
    private static String staticNumeProdus = "";
    private static String staticDescriereProdus = "";
    private static int staticStocProdus;
    private static String staticPretProdus = "";

    private static double staticSuma;
    private static int staticTotalItemuri;

    private List<Produse> listaProduseMini;
    private static List<Integer> listaDeCumparaturi;
    private List<Produse> listaCos;

    private static DataModel listDMCos;
    DataModel detalii;
    private static boolean resultAvailable;
    String hoverinfo = "";
    String addbutton_type;
    static boolean cosAvailable = false;

    /**
     * Creates a new instance of ClientController
     */
    public VanzariController() {
        vanzariHelper = new VanzariHelper();
        resultAvailable = true;
        addbutton_type = "clienti";
        produseHelper = new ProduseHelper();
        prodQ = toIntExact(produseHelper.getNumbers());
        startRes = 0;
    }

    public int getProduseCount() {

        ProduseHelper p_r_helper = new ProduseHelper();
        prodQ = toIntExact(p_r_helper.getNumbers());
        return prodQ;
    }

    public Clienti getTempClient() {
        return tempClient;
    }

    public Produse getTempProdus() {
        return tempProdus;
    }

    public void setTempProdus(Produse thisTempProdus) {
        tempProdus = new Produse();

        tempProdus.setNumeProdus(thisTempProdus.getNumeProdus());
        tempProdus.setDescriereProdus(thisTempProdus.getDescriereProdus());
        tempProdus.setStocProdus(thisTempProdus.getStocProdus());
        tempProdus.setPret(thisTempProdus.getPret());

        staticNumeProdus = tempProdus.getNumeProdus();
        staticDescriereProdus = tempProdus.getDescriereProdus();
        staticStocProdus = tempProdus.getStocProdus();
        staticPretProdus = String.valueOf(tempProdus.getPret());
    }

    public Clienti getSelectedClient() {
        if (currentClient == null) {
            currentClient = new Clienti();

        }
        return currentClient;
    }

    public Produse getSelectedProdus() {
        if (currentProdus == null) {
            currentProdus = new Produse();

        }
        return currentProdus;
    }

    public Vanzari getSelectedVanzare() {
        if (currentTranz == null) {
            currentTranz = new Vanzari();
            selectedItemIndex = -1;
        }
        return currentTranz;
    }

    public Clienti specificClient(int new_id) {
        setPersonID(new_id);
        clientiHelper = new ClientiHelper();
        setStaticClient(clientiHelper.getThisClient(new_id));
        return tempClient;
    }

    public Produse specificProdus(int id) {
        produseHelper = new ProduseHelper();
        setTempProdus(produseHelper.getThisProdus(id));
        return tempProdus;
    }

    public String maiAdauga() {
        cosAvailable = true;
        return "adauga_vanzare";
    }

    public DataModel getVanzariTitles() {
        resultAvailable = true;
        vanzariHelper = new VanzariHelper();
        if (vanzariTitles == null) {
            vanzariTitles = new ListDataModel(vanzariHelper.getVanzariList(startRes, PAGESIZE));
        }
        getRecordCount();
        return vanzariTitles;
    }

    public DataModel getQClientTitles() {
        clientiHelper = new ClientiHelper();
        if (clientTitles == null) {
            clientTitles = new ListDataModel(clientiHelper.getClientList(startRes, PAGESIZE));
        }
        return clientTitles;
    }

    public DataModel getClientSearch() {
        isSearch = true;
        clientiHelper = new ClientiHelper();
        if (clientTitles == null) {
            clientTitles = new ListDataModel(clientiHelper.getClientSearch(search, startRes, PAGESIZE));
        }
        return clientTitles;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentTempPage) {
        lastPage = currentPage;
        currentPage = currentTempPage;
    }

    public String getLastPage() {
        return lastPage;
    }

    public void setLastPage(String thislastPage) {
        lastPage = thislastPage;
    }

    public String getPaginaClienti() {
        setAddbutton_type("clienti");
        resetStaticFields();
        setSearch("");
        isSearch = false;
        recordCount = 0;
        clientiHelper = new ClientiHelper();
        setCurrentPage("index");
        startRes = 0;
        return "index";
    }

    public int getRecordCount() {
        VanzariHelper v_r_helper = new VanzariHelper();
        recordCount = toIntExact(v_r_helper.getNumbers());
        return recordCount;
    }

    public static void setProduseCount(int nr) {
        prodQ = nr;
    }

    void recreateModel() {
        cosAvailable = false;
        clientTitles = null;
        sumarVanzari = null;
        produseTitles = null;
        tranzTitles = null;
        currentClient = null;
        currentProdus = null;
        currentTranz = null;
        selectedClient = null;
        selectedProdus = null;
        selectedVanzare = null;
        listaProduseMini = null;
        listDMCos = null;

    }

    public boolean isHasNextPage() {
        if (startRes + PAGESIZE < recordCount) {
            return true;
        }
        return false;
    }

    public boolean isHasPreviousPage() {
        if (startRes - PAGESIZE >= 0) {
            return true;
        }
        return false;
    }

    public boolean isHasNextProdPage() {
        if (startRes + PAGESIZE <= prodQ) {
            return true;
        }
        return false;
    }

    public boolean isHasPreviousProdPage() {
        if (startRes - PAGESIZE >= 0) {
            return true;
        }
        return false;
    }

    public boolean isHasNextProdPageA() {
        if (startRes + PAGESIZE <= prodAvailable) {
            return true;
        }
        return false;
    }

    public boolean isHasPreviousProdPageA() {
        if (startRes - PAGESIZE >= 0) {
            return true;
        }
        return false;
    }

    public int getPageSize() {
        return PAGESIZE;
    }

    public DataModel getClientTitles() {
        resultAvailable = true;
        if (!isSearch) {
            clientiHelper = new ClientiHelper();
            if (clientTitles == null) {
                clientTitles = new ListDataModel(clientiHelper.getClientList(startRes, PAGESIZE));
            }
            getClientiCount("clienti");

        } else {
            resultAvailable = true;
            clientiHelper = new ClientiHelper();
            if (clientTitles == null) {
                clientTitles = new ListDataModel(clientiHelper.getClientSearch(search, startRes, PAGESIZE));
            }
            getSRCount();
        }
        return clientTitles;
    }

    public int getClientiCount(String type) {

        ClientiHelper c_r_helper = new ClientiHelper();
        recordCount = toIntExact(c_r_helper.getNumbers());
        return recordCount;
    }

    public int getSRCount() {

        clientiHelper = new ClientiHelper();
        recordCount = toIntExact(clientiHelper.getSearchNr(getSearch(), startRes));

        return recordCount;
    }

    public void displaySearch() {
        clientTitles = null;
        resultAvailable = false;
        startRes = 0;
        isSearch = true;

        getClientSearch();
        if (clientTitles.getRowCount() == 0) {
            recordCount = 0;
            staticMessage = "Nu s-au gasit clienti conform criteriilor";
            resultAvailable = false;
            recordCount = 0;
        } else {
            getSRCount();
            resultAvailable = true;
        }

    }

    public String creazaVanzare() {
        if (listaDeCumparaturi == null) {
            listaDeCumparaturi = new ArrayList<>();
        }
        //    startRes = 0;

        clientiHelper = new ClientiHelper();
        currentClient = null;
        currentClient = (Clienti) getClientTitles().getRowData();

        specificClient(currentClient.getIdClient());
        System.out.println(InfoClientController.printInfo());
        System.out.println("SELECTING THIS CLIENT ID ----------------------------------------------->" + currentClient.getIdClient());

        InfoClientController.setClient(tempClient);
        System.out.println(InfoClientController.printInfo());
        getListAvailable();
        TopController.setBackpage("vanzarev");
        return "adauga_vanzare";
    }

    public String linkVanzare() {
        if (listaDeCumparaturi == null) {
            listaDeCumparaturi = new ArrayList<>();
        }

        InfoClientController.setClient(tempClient);

        getListAvailable();
        TopController.setBackpage("index");
        startRes = 0;
        return "adauga_vanzare";
    }

    public ListDataModel getListAvailable() {
        isSearch = false;
        produseHelper = new ProduseHelper();
        if (produseTitles == null) {
            produseTitles = new ListDataModel(produseHelper.getProduseListAvailable(startRes, PAGESIZE));
        }
        produseHelper = new ProduseHelper();
        prodAvailable = toIntExact(produseHelper.getAvailableNumbers());
        return (ListDataModel) produseTitles;
    }

    public String prepareList() {
        recreateModel();
        if (getCurrentPage().contentEquals("adauga_vanzare")) {
            listaDeCumparaturi = null;
            startRes = 0;
            setCurrentPage("index");
        }
        return getCurrentPage();
    }

    public void setSearch(String str) {
        search = str;
    }
//

    public String getSearch() {
        return search;
    }

    public String adauga() {
        startRes = 0;
        TopController.resetStatics();
        currentProdus = new Produse();
        return "vanzarev";
    }

    public DataModel getProduseTitles() {

        produseHelper = new ProduseHelper();
        if (!isSearch) {
            if (produseTitles == null) {
                produseTitles = new ListDataModel(produseHelper.getProduseList(startRes, PAGESIZE));
            }

            prodQ = getProduseCount();
        } else {
            if (produseTitles == null) {
                produseTitles = new ListDataModel(produseHelper.getProduseSearch(search, startRes, PAGESIZE));
            }
        }

        return produseTitles;
    }

    public DataModel getQProduseTitles() {
        produseHelper = new ProduseHelper();
        if (produseTitles == null) {
            produseTitles = new ListDataModel(produseHelper.getProduseList(startRes, PAGESIZE));
        }
        return produseTitles;
    }

    public DataModel getProduseSearch() {
        isSearch = true;
        produseHelper = new ProduseHelper();
        if (produseTitles == null) {
            produseTitles = new ListDataModel(produseHelper.getProduseSearch(search, startRes, PAGESIZE));
        }
        return produseTitles;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        VanzariController.id = id;
    }

    public String getStaticNumeClient() {
        return staticNumeClient;
    }

    public void setStaticNumeClient(String sN) {
        staticNumeClient = sN;
    }

    public String getStaticPrenumeClient() {
        return staticPrenumeClient;
    }

    public void setStaticPrenumeClient(String sP) {
        staticPrenumeClient = sP;
    }

    public String getStaticAdresaClient() {
        return staticAdresaClient;
    }

    public void setStaticAdresaClient(String sA) {
        staticAdresaClient = sA;
    }

    public String getStaticTelefonClient() {
        return staticTelefonClient;
    }

    public void setStaticTelefonClient(String sT) {
        staticTelefonClient = sT;
    }

    public List<Produse> getListaProduseMini() {
        return listaProduseMini;
    }

    public Integer getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(Integer idProdus) {
        this.idProdus = idProdus;
    }

    public ListDataModel getListDM() {
        isSearch = false;
        produseHelper = new ProduseHelper();

        if (produseTitles == null) {
            produseTitles = new ListDataModel(produseHelper.getProduseListAvailable(startRes, PAGESIZE));
        }

        for (int i = 0; i < produseTitles.getRowCount(); i++) {
            produseTitles.setRowIndex(i);
            Produse prd = (Produse) produseTitles.getRowData();
        }
        getAvailableCount();
        return (ListDataModel) produseTitles;
    }

    public int getAvailableCount() {
        ProduseHelper c_r_helper = new ProduseHelper();
        recordCount = toIntExact(c_r_helper.getAvailableNumbers());
        return recordCount;
    }

    public List<Produse> getListaCos() {
        return listaCos;
    }

    public DataModel getListDMCos() {
        return listDMCos;
    }

    public void prodNext() {
        startRes = startRes + PAGESIZE;
        recreateModel();
    }

    public void prodPrev() {
        if ((startRes - PAGESIZE > 0)) {
            startRes = startRes - PAGESIZE;
        } else {
            startRes = 0;
        }
        recreateModel();
    }

    public void prodNextA() {
        startRes = startRes + PAGESIZE;
        recreateModel();
    }

    public void prodPrevA() {
        if ((startRes - PAGESIZE > 0)) {
            startRes = startRes - PAGESIZE;
        } else {
            startRes = 0;
        }
        recreateModel();
    }

    public void vanzNext() {
        startRes = startRes + PAGESIZE;
        recreateModel();
    }

    public void vanzPrev() {
        if ((startRes - PAGESIZE > 0)) {
            startRes = startRes - PAGESIZE;
        } else {
            startRes = 0;
        }
        recreateModel();
    }

    public static void resetStaticFields() {
        cosAvailable = false;
        listaDeCumparaturi = null;
        startRes = 0;
        staticNumeProdus = "";
        staticDescriereProdus = "";
        staticPretProdus = "";
        staticStocProdus = 0;
        staticNumeClient = "";
        staticPrenumeClient = "";
        staticAdresaClient = "";
        staticTelefonClient = "";
        search = "";
    }

    public void adaugaLaCos() {
        if (listaDeCumparaturi == null) {
            listaDeCumparaturi = new ArrayList<>();
        }
        produseHelper = new ProduseHelper();
        currentProdus = null;
        currentProdus = (Produse) getQProduseTitles().getRowData();
        tempID = currentProdus.getIdProdus();
        boolean unique = false;

        if (!listaDeCumparaturi.isEmpty()) {
            for (int i = 0; i < listaDeCumparaturi.size(); i++) {
                if (listaDeCumparaturi.get(i) == tempID) {
                    unique = false;
                    break;
                }
                unique = true;
            }
        } else {
            unique = true;
        }

        if (unique) {
            listaDeCumparaturi.add(tempID);
            cosAvailable = true;
        }
    }

    public String mergilacos() throws EmptyInfoException {
        String tempPage = "";
        try {
            if (!cosAvailable) {
                throw new EmptyInfoException("Cosul este gol");
            }
            produseHelper = new ProduseHelper();
            listDMCos = new ListDataModel(produseHelper.getSpecificRes(listaDeCumparaturi));
            return "detalii_cos";
        } catch (EmptyInfoException e) {
        }

        return tempPage;
    }

    public static int getPersonID() {
        return personID;
    }

    public static void setPersonID(int personID) {
        VanzariController.personID = personID;
    }

    public boolean isHasResults() {

        if (VanzariController.resultAvailable) {
            return true;
        }
        return false;
    }

    public String saveInfo() {

        vanzariHelper = new VanzariHelper();
        int ultimaComanda = vanzariHelper.getLastCos();
        produseHelper = new ProduseHelper();
        List<Produse> realList = produseHelper.getFullRes(listaDeCumparaturi);
        clientiHelper = new ClientiHelper();
        client = clientiHelper.getFullLoadClient(InfoClientController.getId());
        vanzariHelper = new VanzariHelper();
        vanzariHelper.complexSave(ultimaComanda + 1, listDMCos, client, realList);
        return TopController.getPaginaClienti();
    }

    public String getPaginaDetalii() {

        SDV sdv = (SDV) vanzariTitles.getRowData();

        clientiHelper = new ClientiHelper();
        Clienti newCl = clientiHelper.getThisClient(sdv.getIdClient());
        InfoClientController.setClient(newCl);
        vanzariHelper = new VanzariHelper();
        detalii = new ListDataModel(vanzariHelper.getThisSale(sdv.getIdComanda()));
        TopController.setBackpage("vanzarev");
        return "detalii_vanzare";
    }

    public static double getStaticSuma() {
        return staticSuma;
    }

    public static void setStaticSuma(double staticSuma) {
        VanzariController.staticSuma = staticSuma;
    }

    public static int getStaticTotalItemuri() {
        return staticTotalItemuri;
    }

    public static void setStaticTotalItemuri(int staticTotalItemuri) {
        VanzariController.staticTotalItemuri = staticTotalItemuri;
    }

    public DataModel getDetalii() {
        return detalii;
    }

    public void setDetalii(DataModel detalii) {
        this.detalii = detalii;
    }

    public String getHoverinfo() {
        return hoverinfo;
    }

    public void setHoverinfo(String hoverinfo) {
        this.hoverinfo = hoverinfo;
    }

    public String getAddbutton_type() {
        return addbutton_type;
    }

    public void setAddbutton_type(String addbutton_type) {
        this.addbutton_type = addbutton_type;
    }

    public boolean isCosAvailable() {
        return cosAvailable;
    }

    public boolean isHasCos() {
        try {
            if (listaDeCumparaturi.isEmpty()) {
                cosAvailable = false;
            } else {
                cosAvailable = true;
            }
        } catch (Exception e) {
            cosAvailable = false;
        }
        return cosAvailable;
    }

    public void stergeDinCos() {

        List<Produse> list = new ArrayList<>();

        int rowToDelete = listDMCos.getRowIndex();
        listaDeCumparaturi = new ArrayList<>();
        for (int i = 0; i < listDMCos.getRowCount(); i++) {
            listDMCos.setRowIndex(i);
            if (listDMCos.getRowIndex() == rowToDelete) {
                continue;
            }
            Produse prd = (Produse) listDMCos.getRowData();
            list.add(prd);
            listaDeCumparaturi.add(prd.getIdProdus());
        }
        listDMCos = null;
        listDMCos = new ListDataModel(list);
        listDMCos.setRowIndex(0);
        if (!listDMCos.isRowAvailable()) {
            cosAvailable = false;
        }
        if (!listaDeCumparaturi.isEmpty()) {
            cosAvailable = false;
        }
    }

    public static void setStaticClient(Clienti client) {
        tempClient = client;
        staticIdClient = client.getIdClient();
        staticPrenumeClient = client.getPrenumeClient();
        staticNumeClient = client.getNumeClient();
        staticAdresaClient = client.getAdresaClient();
        staticTelefonClient = client.getTelefonClient();
        staticNrVanzariClient = Integer.toString(client.getNrVanzari());
        staticNrProduseClient = Integer.toString(client.getNrProduse());
        staticCifraVanzari = Double.toString(client.getCifraVanzari());
    }

    public static void resetStaticClient() {
        staticIdClient = 0;
        staticPrenumeClient = "";
        staticNumeClient = "";
        staticAdresaClient = "";
        staticTelefonClient = "";
        staticNrVanzariClient = "";
        staticNrProduseClient = "";
        staticCifraVanzari = "";
    }

    public static DataModel getStaticDMList() {
        return listDMCos;
    }

}
