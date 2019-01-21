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
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import models.Clienti;
import models.Produse;
import models.Vanzari;

/**
 *
 * @author CyNEXX
 */
@ManagedBean(eager = true)
@Named(value = "produseController")
@RequestScoped
public class ProduseController implements Serializable {

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
    static int tempID;

    public Clienti selectedClient;
    public Produse selectedProdus;
    public Vanzari selectedVanzare;
    public String staticMessage;

    private Produse currentProdus = new Produse();
    private Vanzari currentVanz = new Vanzari();

    private Integer idProdus;
    private static String staticNumeProdus = "";
    private static String staticDescriereProdus = "";
    private static String staticStocProdus;
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
    public ProduseController() {
        produseHelper = new ProduseHelper();
        resultAvailable = true;
        addbutton_type = "clienti";
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
        staticStocProdus = Integer.toString(tempProdus.getStocProdus());
        staticPretProdus = String.valueOf(tempProdus.getPret());

    }

    public Produse getSelectedProdus() {
        if (currentProdus == null) {
            currentProdus = new Produse();

        }
        return currentProdus;
    }

    public void stergeProdus() {
        currentProdus = null;
        currentProdus = (Produse) getProduseTitles().getRowData();
        produseHelper = new ProduseHelper();
        produseHelper.dezactiveazaProdus(currentProdus.getIdProdus());
        if (produseTitles.getRowCount() == 1) {
            prodPrev();
        }
        recreateModel();
    }

    public Produse specificProdus(int id) {
        produseHelper = new ProduseHelper();
        setTempProdus(produseHelper.getThisProdus(id));
        return tempProdus;
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

    public static void resetStaticFields() {
        cosAvailable = false;
        listaDeCumparaturi = null;
        startRes = 0;
        search = "";
        staticNumeProdus = "";
        staticDescriereProdus = "";
        staticPretProdus = "";
        staticStocProdus = "";
    }

    public String getPaginaProduse() {
        resetStaticFields();
        setAddbutton_type("produse");
        isSearch = false;
        recordCount = 0;
        produseHelper = new ProduseHelper();
        setCurrentPage("produse");
        startRes = 0;
        return getCurrentPage();
    }

    public String getPaginaClienti() {
        setAddbutton_type("clienti");
        resetVanzare();
        setSearch("");
        isSearch = false;
        recordCount = 0;
        clientiHelper = new ClientiHelper();
        setCurrentPage("index");
        startRes = 0;
        return getCurrentPage();
    }

    public int getRecordCount(String type) {
        ProduseHelper p_r_helper = new ProduseHelper();
        recordCount = toIntExact(p_r_helper.getNumbers());
        return recordCount;
    }

    public int getProduseCount() {
        ProduseHelper p_r_helper = new ProduseHelper();
        recordCount = toIntExact(p_r_helper.getNumbers());
        return recordCount;
    }

    public int getSRCount() {
        produseHelper = new ProduseHelper();
        recordCount = toIntExact(produseHelper.getSearchNr(getSearch(), startRes));
        return recordCount;
    }

    public void displaySearch() {
        clientTitles = null;
        resultAvailable = false;
        startRes = 0;
        isSearch = true;

        getProduseSearch();
        if (produseTitles.getRowCount() == 0) {
            recordCount = 0;
            staticMessage = "Nu s-au gasit produse conform criteriilor";
            resultAvailable = false;
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

        currentProdus = null;
        currentVanz = null;

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
        String pagina = null;

        produseHelper = new ProduseHelper();
        currentProdus = null;
        currentProdus = (Produse) getProduseTitles().getRowData();
        id = currentProdus.getIdProdus();

        specificProdus(currentProdus.getIdProdus());
        pagina = "edit_produs";

        return pagina;
    }

    public String prepareList() {
        resetVanzare();
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
        String saveType = getCurrentPage();
        produseHelper.saveProdus(staticNumeProdus, staticDescriereProdus, Integer.valueOf(staticStocProdus), Double.valueOf(staticPretProdus));
        return TopController.getPaginaProduse();
    }

    public String modificaInfo() {
        String saveType = getCurrentPage();

        produseHelper = new ProduseHelper();
        tempProdus.setNumeProdus(getStaticNumeProdus());
        tempProdus.setDescriereProdus(getStaticDescriereProdus());
        tempProdus.setStocProdus(Integer.valueOf(getStaticStocProdus()));
        tempProdus.setPret(Double.parseDouble(getStaticPretProdus()));
        produseHelper.modificaProdus(tempProdus);
        return TopController.getPaginaProduse();
    }

    public String adauga() {
        resetStaticFields();
        String type = getCurrentPage();
        String page = "adauga_produs";
        currentProdus = new Produse();
        return page;
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
        ProduseController.id = id;
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

    public String getStaticStocProdus() {
        return staticStocProdus;
    }

    public void setStaticStocProdus(String sSP) {
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
        produseHelper = new ProduseHelper();
        if (produseTitles == null) {
            produseTitles = new ListDataModel(produseHelper.getProduseList(startRes, PAGESIZE));
        }
        getRecordCount("produse");
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

    public static void resetVanzare() {
        startRes = 0;
        cosAvailable = false;
        listaDeCumparaturi = null;
        staticNumeProdus = "";
        staticDescriereProdus = "";
        staticPretProdus = "";
        staticStocProdus = "";
        staticNume = "";
        staticPrenume = "";
        staticAdresa = "";
        staticTelefon = "";
    }

    public static int getPersonID() {
        return personID;
    }

    public static void setPersonID(int personID) {
        ProduseController.personID = personID;
    }

    public String getStaticMessage() {
        return staticMessage;
    }

    public void setStaticMessage(String staticMessage) {
        this.staticMessage = staticMessage;
    }

    public boolean isHasResults() {

        if (ProduseController.resultAvailable) {
            return true;
        }
        return false;
    }


    public static double getStaticSuma() {
        return staticSuma;
    }

    public static void setStaticSuma(double staticSuma) {
        ProduseController.staticSuma = staticSuma;
    }

    public static int getStaticTotalItemuri() {
        return staticTotalItemuri;
    }

    public static void setStaticTotalItemuri(int staticTotalItemuri) {
        ProduseController.staticTotalItemuri = staticTotalItemuri;
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

}
