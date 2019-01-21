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
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import models.Clienti;
import models.Produse;
import models.SDV;
import models.Vanzari;

/**
 *
 * @author CyNEXX
 */
@ManagedBean(eager = true)
@Named(value = "indexController")
@RequestScoped
public class IndexController implements Serializable {

    int startId;
    int endId;
    DataModel clientTitles;
    DataModel dvtitles;
    Clienti client;
    DataModel produseTitles;
    DataModel tranzTitles;
    ClientiHelper clientiHelper;

    ProduseHelper produseHelper;
    VanzariHelper tranzHelper;
    private static String search = "";
    private static int recordCount = 1000;
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

    private static String staticNume = "";
    private static int staticId = 0;
    private static String staticPrenume = "";
    private static String staticAdresa = "";
    private static String staticTelefon = "";
    private static String staticnrv = "";
    private static String staticnrp = "";
    private static String staticcv = "";

    static int tempID;

    public Clienti selectedClient;
    public Produse selectedProdus;
    public Vanzari selectedTranz;
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
    public IndexController() {
        HintsController.setHint("Creaza o vanzare NOUA catre acest client");
        clientiHelper = new ClientiHelper();
        resultAvailable = true;
        addbutton_type = "clienti";
    }

    public Clienti getTempClient() {
        return tempClient;
    }

    public void setTempClient(Clienti thisTempClient) {
        tempClient = new Clienti(thisTempClient.getIdClient(), thisTempClient.getNumeClient(),
                thisTempClient.getPrenumeClient(), thisTempClient.getAdresaClient(),
                thisTempClient.getTelefonClient(), thisTempClient.getNrVanzari(),
                thisTempClient.getNrProduse(), thisTempClient.getCifraVanzari());
        staticNume = tempClient.getNumeClient();
        staticPrenume = tempClient.getPrenumeClient();
        staticAdresa = tempClient.getAdresaClient();
        staticTelefon = tempClient.getTelefonClient();
        staticnrv = String.valueOf(tempClient.getNrVanzari());
        staticnrp = String.valueOf(tempClient.getNrProduse());
        staticcv = String.valueOf(tempClient.getCifraVanzari());
        staticId = tempClient.getIdClient();

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
        setTempClient(clientiHelper.getThisClient(new_id));
        return tempClient;
    }

    public Produse specificProdus(int id) {
        produseHelper = new ProduseHelper();
        setTempProdus(produseHelper.getThisProdus(id));
        return tempProdus;
    }

    public DataModel getClientTitles() {
        resultAvailable = true;
        if (!isSearch) {
            clientiHelper = new ClientiHelper();
            if (clientTitles == null) {
                clientTitles = new ListDataModel(clientiHelper.getClientList(startRes, PAGESIZE));
            }
            getRecordCount("clienti");

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
        return getCurrentPage();
    }

    public int getRecordCount(String type) {
        ClientiHelper c_r_helper = new ClientiHelper();
        recordCount = toIntExact(c_r_helper.getNumbers());
        return recordCount;
    }

    public int getAvailableCount() {
        ProduseHelper c_r_helper = new ProduseHelper();
        recordCount = toIntExact(c_r_helper.getAvailableNumbers());
        return recordCount;
    }

    public int getSRCount() {

        if (currentPage.contentEquals("index")) {
            clientiHelper = new ClientiHelper();
            recordCount = toIntExact(clientiHelper.getSearchNr(getSearch(), startRes));
        }

        if (currentPage.contentEquals("produse")) {
            produseHelper = new ProduseHelper();
            recordCount = toIntExact(produseHelper.getSearchNr(getSearch(), startRes));
        }
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

    void recreateModel() {
        cosAvailable = false;
        clientTitles = null;
        dvtitles = null;
        produseTitles = null;
        tranzTitles = null;
        currentClient = null;
        currentProdus = null;
        currentTranz = null;
        selectedClient = null;
        selectedProdus = null;
        selectedTranz = null;
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

    public void next() {
        startRes = startRes + PAGESIZE;
        recreateModel();
    }

    public void previous() {
        if ((startRes - PAGESIZE > 0)) {
            startRes = startRes - PAGESIZE;
        } else {
            startRes = 0;
        }
        recreateModel();
    }

    public int getPageSize() {
        return PAGESIZE;
    }

    public String prepareView() {
        String type = getCurrentPage();
        String pagina = pagina = "edit_client";
        clientiHelper = new ClientiHelper();
        currentClient = null;
        currentClient = (Clienti) getClientTitles().getRowData();
        personID = currentClient.getIdClient();

        specificClient(personID);

        return pagina;
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

    public String getSearch() {
        return search;
    }

    public String saveInfo() {
        clientiHelper.saveClient(staticNume, staticPrenume, staticAdresa, staticTelefon);
        return TopController.getPaginaClienti();
    }

    public String modificaInfo() {
        String saveType = getCurrentPage();

        clientiHelper = new ClientiHelper();
        tempClient.setNumeClient(getStaticNume());
        tempClient.setPrenumeClient(getStaticPrenume());
        tempClient.setAdresaClient(getStaticAdresa());
        tempClient.setTelefonClient(getStaticTelefon());
        clientiHelper.modificaClient(tempClient);

        return TopController.getPaginaClienti();
    }

    public String adauga() {
        resetStaticFields();
        String type = getCurrentPage();
        String page = "adauga_client";
        currentClient = new Clienti();
        return page;
    }

    public String creazaVanzare() {
        VanzariController vc = new VanzariController();
        clientiHelper = new ClientiHelper();
        currentClient = null;
        currentClient = (Clienti) getClientTitles().getRowData();
        clientiHelper = new ClientiHelper();
        currentClient = clientiHelper.getFullLoadClient(currentClient.getIdClient());
        VanzariController.setStaticClient((Clienti) getClientTitles().getRowData());
        System.out.println("------------ID------------->" + VanzariController.getPersonID());
        return vc.linkVanzare();
    }

    public void stergeClient() {

        currentClient = null;
        currentClient = (Clienti) getClientTitles().getRowData();
        clientiHelper = new ClientiHelper();
        clientiHelper.dezactiveazaClient(currentClient.getIdClient());
        if (clientTitles.getRowCount() == 1) {
            prodPrev();
        }
        recreateModel();
    }

    public DataModel getProduseTitles() {

        produseHelper = new ProduseHelper();
        if (!isSearch) {
            if (produseTitles == null) {
                produseTitles = new ListDataModel(produseHelper.getProduseList(startRes, PAGESIZE));
            }
            getRecordCount("produse");
        } else {
            if (produseTitles == null) {
                produseTitles = new ListDataModel(produseHelper.getProduseSearch(search, startRes, PAGESIZE));
            }
            getSRCount();
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
        IndexController.id = id;
    }

    public String getStaticNume() {
        return staticNume;
    }

    public void setStaticNume(String sN) {
        staticNume = sN;
    }

    public String getStaticPrenume() {
        return staticPrenume;
    }

    public void setStaticPrenume(String sP) {
        staticPrenume = sP;
    }

    public String getStaticAdresa() {
        return staticAdresa;
    }

    public void setStaticAdresa(String sA) {
        staticAdresa = sA;
    }

    public String getStaticTelefon() {
        return staticTelefon;
    }

    public void setStaticTelefon(String sT) {
        staticTelefon = sT;
    }

    public String getStaticNumeProdus() {
        return staticNumeProdus;
    }

    public void setStaticNumeProdus(String sNP) {
        staticNumeProdus = sNP;
    }

    public String getStaticDescriereProdus() {
        return staticDescriereProdus;
    }

    public void setStaticDescriereProdus(String sDP) {
        staticDescriereProdus = sDP;
    }

    public int getStaticStocProdus() {
        return staticStocProdus;
    }

    public void setStaticStocProdus(int sSP) {
        staticStocProdus = sSP;
    }

    public String getStaticPretProdus() {
        return staticPretProdus;
    }

    public void setStaticPretProdus(String pret) {
        staticPretProdus = pret;
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
        startRes = 0;
        produseHelper = new ProduseHelper();
        if (produseTitles == null) {
            produseTitles = new ListDataModel(produseHelper.getProduseListAvailable(startRes, PAGESIZE));
        }
        getAvailableCount();
        return (ListDataModel) produseTitles;
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

    public static void resetStaticFields() {
        startRes = 0;
        search = "";
        cosAvailable = false;
        listaDeCumparaturi = null;
        startRes = 0;
        staticNume = "";
        staticId = 0;
        staticPrenume = "";
        staticAdresa = "";
        staticTelefon = "";
    }

    public void adaugaLaCos() {
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
        IndexController.personID = personID;
    }

    public String getStaticMessage() {
        return staticMessage;
    }

    public void setStaticMessage(String staticMessage) {
        this.staticMessage = staticMessage;
    }

    public boolean isHasResults() {

        if (IndexController.resultAvailable) {
            return true;
        }
        return false;
    }

    public void setDvtitles(DataModel dvtitles) {
        this.dvtitles = dvtitles;
    }

    public String getPaginaDetalii() {
        setCurrentPage("detalii_vanzare");
        String page = "detalii_vanzare";
        SDV sdv = (SDV) dvtitles.getRowData();

        clientiHelper = new ClientiHelper();
        Clienti newCl = clientiHelper.getThisClient(sdv.getIdClient());

        setStaticNume(newCl.getNumeClient());
        setStaticPrenume(newCl.getPrenumeClient());
        setStaticAdresa(newCl.getAdresaClient());
        setStaticTelefon(newCl.getTelefonClient());

        setStaticSuma(sdv.getTotalSuma());

        tranzHelper = new VanzariHelper();

        return getCurrentPage();
    }

    public static double getStaticSuma() {
        return staticSuma;
    }

    public static void setStaticSuma(double staticSuma) {
        IndexController.staticSuma = staticSuma;
    }

    public static int getStaticTotalItemuri() {
        return staticTotalItemuri;
    }

    public static void setStaticTotalItemuri(int staticTotalItemuri) {
        IndexController.staticTotalItemuri = staticTotalItemuri;
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
        return cosAvailable;
    }

    public static String getStaticnrv() {
        return staticnrv;
    }

    public static void setStaticnrv(String snv) {
        staticnrv = snv;
    }

    public static String getStaticnrp() {
        return staticnrp;
    }

    public static void setStaticnrp(String snp) {
        staticnrp = snp;
    }

    public static String getStaticcv() {
        return staticcv;
    }

    public static void setStaticcv(String scv) {
        staticcv = scv;
    }

}
