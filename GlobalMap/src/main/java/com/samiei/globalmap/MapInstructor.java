package com.samiei.globalmap;


import android.content.Context;
import android.graphics.Color;

import com.google.android.gms.maps.model.LatLng;
import com.samiei.globalmap.Interfaces.IMapClickEvents;
import com.samiei.globalmap.Models.CirclePolygonModel;
import com.samiei.globalmap.Models.MapLineModel;
import com.samiei.globalmap.Models.MapObjectModel;
import com.samiei.globalmap.Models.PolygonModel;
import com.samiei.globalmap.Service.LocationProvider;


import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

public abstract class MapInstructor  {
    int counter = 0;
    Context context;

    public MapInstructor(Context context) {
        this.context = context;
    }


    protected abstract void showCurrentLocation(MapObjectModel mapObjectModel);

    protected abstract void drawCirclePolygon(CirclePolygonModel circlePolygonModel);

    protected abstract boolean removeExistingFeatures(String groupKey);

    protected abstract void drawLinePolygon(MapLineModel mapLineModel);

    protected abstract void addGroupLocationLayer(ArrayList<MapObjectModel> mapObjectModels, String group);

    protected abstract void addSingleLocationLayer(MapObjectModel mapObjectModel);

    protected abstract void drawSellPolygon(ArrayList<PolygonModel> polygonModel);

    protected abstract void setStartLocation(MapObjectModel mapObjectModel);

    protected abstract LatLng convertGeoPointToLatLng(GeoPoint geoPoint);

    protected abstract void moveCameraToSpecificPosition(LatLng latLng);

    protected abstract void zoomCameraToSpecificPosition(GeoPoint geoPoint,int zoomScale);

    protected abstract void onMapContentClickListener(String groupMarkerId, IMapClickEvents IMapClickEvents);

    protected abstract void removeAllAvailableFeaturesOnMap();

    protected abstract int calculateZoomScale(Float radius);

    protected abstract void rotateCamera(double bearing);

    protected abstract void tiltCamera(double tilt);





    public String INCREMENTAL_LOCATION_LAYER_ID (){
        String CURRENT_LOCATION_LAYER_ID = String.valueOf(counter);
        counter++;
        return CURRENT_LOCATION_LAYER_ID;
    }
    public String INCREMENTAL_LOCATION_SOURCE_ID (){
        String CURRENT_LOCATION_LAYER_ID = String.valueOf(counter);
        counter++;
        return CURRENT_LOCATION_LAYER_ID;
    }

    //CURRENT_LOCATION
    public String CURRENT_LOCATION_LAYER_ID() {
        String CURRENT_LOCATION_LAYER_ID = "current_location_Layer_id";
        return CURRENT_LOCATION_LAYER_ID;
    }
    public String CURRENT_LOCATION_SOURCE_ID() {
        String CURRENT_LOCATION_SOURCE_ID = "Current_Location_Source";
        return CURRENT_LOCATION_SOURCE_ID;
    }
    public String CURRENT_LOCATION_GROUP_ID() {
        String CURRENT_LOCATION_GROUP_ID = "CURRENT_LOCATION_GROUP_ID";
        return CURRENT_LOCATION_GROUP_ID;
    }
    public String CURRENT_LOCATION_IMAGE_ID() {
        String CURRENT_LOCATION_IMAGE_ID = "CURRENT_LOCATION_IMAGE_ID";
        return CURRENT_LOCATION_IMAGE_ID;
    }




    public String SOURCE_LOCATION_GROUP_ID() {
        String SOURCE_LOCATION_GROUP_ID = "SOURCE_LOCATION_GROUP_ID";
        return SOURCE_LOCATION_GROUP_ID;
    }
    public String SOURCE_LOCATION_SOURCE_ID() {
        String SOURCE_LOCATION_GROUP_ID = "SOURCE_LOCATION_GROUP_ID";
        return SOURCE_LOCATION_GROUP_ID;
    }

    public String SOURCE_LOCATION_LAYER_ID() {
        String SOURCE_LOCATION_GROUP_ID = "SOURCE_LOCATION_GROUP_ID";
        return SOURCE_LOCATION_GROUP_ID;
    }
    public String SOURCE_LOCATION_IMAGE_ID() {
        String SOURCE_LOCATION_GROUP_ID = "SOURCE_LOCATION_GROUP_ID";
        return SOURCE_LOCATION_GROUP_ID;
    }





    public String DESTINATION_LOCATIONS() {
        String DESTINATION_LOCATIONS = "Destination_location";
        return DESTINATION_LOCATIONS;
    }



    public String SELL_POLYGON_GROUP_ID()
    {
        String ALL_POLYGON_LAYER_IDS = "ALL_POLYGON_LAYER_IDS";
        return ALL_POLYGON_LAYER_IDS;
    }






    public String CUSTOMER_IMAGE() {
        String CUSTOMER_IMAGE = "Customer_Image";
        return CUSTOMER_IMAGE;
    }

    public String CIRCLE_POLYGON_GROUP_ID()
    {
        String CIRCLE_POLYGON_GROUP_ID = "CIRCLE_POLYGON_GROUP_ID";
        return CIRCLE_POLYGON_GROUP_ID;
    }

    public String CIRCLE_POLYGON_LAYER_ID()
    {
        String CIRCLE_POLYGON_LAYER_ID = "CIRCLE_POLYGON_LAYER_ID";
        return CIRCLE_POLYGON_LAYER_ID;
    }

    public String CIRCLE_POLYGON_SOURCE_ID()
    {
        String CIRCLE_POLYGON_SOURCE_ID = "CIRCLE_POLYGON_SOURCE_ID";
        return CIRCLE_POLYGON_SOURCE_ID;
    }


    public String CUSTOMER_LOCATION_SOURCE_ID()
    {
        String CUSTOMER_LOCATION_SOURCE_ID = "CUSTOMER_LOCATION_SOURCE_ID";
        return CUSTOMER_LOCATION_SOURCE_ID;
    }
    public String CUSTOMER_LOCATION_LAYER_ID()
    {
        String CUSTOMER_LOCATION_LAYER_ID = "CUSTOMER_LOCATION_LAYER_ID";
        return CUSTOMER_LOCATION_LAYER_ID;
    }


    public String CUSTOMER_LOCATION_IMAGE_ID()
    {
        String CUSTOMER_LOCATION_IMAGE_ID = "CUSTOMER_LOCATION_IMAGE_ID";
           return CUSTOMER_LOCATION_IMAGE_ID;

    }
    public String CUSTOMER_LOCATION_GROUP_ID()
    {
        String CUSTOMER_LOCATION_GROUP_ID = "CUSTOMER_LOCATION_GROUP_ID";
        return CUSTOMER_LOCATION_GROUP_ID;

    }



    public double TOUCH_ACCURACY() {
        double TOUCH_ACCURACY = 0.0001;
        return TOUCH_ACCURACY;
    }

    public String DIRECTION_LAYER_ID() {
        String DIRECTION_LAYER_ID = "DIRECTION_LAYER_ID";
        return DIRECTION_LAYER_ID;
    }

    public String DIRECTION_SOURCE_ID() {
        String DIRECTION_SOURCE_ID = "DIRECTION_SOURCE_ID";
        return DIRECTION_SOURCE_ID;
    }


    public String START_LOCATION_GROUP_ID() {
        String START_LOCATION_GROUP_ID = "START_LOCATION_GROUP_ID";
        return START_LOCATION_GROUP_ID;
    }
    public String START_LOCATION_LAYER_ID() {
        String START_LOCATION_LAYER_ID = "START_LOCATION_LAYER_ID";
        return START_LOCATION_LAYER_ID;
    }
    public String START_LOCATION_SOURCE_ID() {
        String START_LOCATION_SOURCE_ID = "START_LOCATION_SOURCE_ID";
        return START_LOCATION_SOURCE_ID;
    }
    public String START_LOCATION_IMAGE_ID() {
        String START_LOCATION_IMAGE_ID = "START_LOCATION_IMAGE_ID";
        return START_LOCATION_IMAGE_ID;
    }


    public String POLYLINE_GROUP_ID()
    {
        String POLYLINE_GROUP_ID = "POLYLINE_GROUP_ID";
        return POLYLINE_GROUP_ID;
    }
    public String POLYLINE_LAYER_ID()
    {
        String POLYLINE_LAYER_ID = "POLYLINE_LAYER_ID";
        return POLYLINE_LAYER_ID;
    }
    public String POLYLINE_SOURCE_ID() {
        String POLYLINE_SOURCE_ID = "POLYLINE_SOURCE_ID";
        return POLYLINE_SOURCE_ID;
    }

    public String POLYLINE_IMAGE_ID() {
        String POLYLINE_IMAGE_ID = "POLYLINE_IMAGE_ID";
        return POLYLINE_IMAGE_ID;
    }


    protected void drawTarhZojoFard() {
        PolygonModel polygonModel = new PolygonModel.Builder()
                .setGroup_key(SELL_POLYGON_GROUP_ID())
                .setSource_id(INCREMENTAL_LOCATION_SOURCE_ID())
                .setLayer_id(INCREMENTAL_LOCATION_LAYER_ID())
                .setLatLng(getOddEvenPoints())
                .setStrokeWidth(4f)
                .setHasInfoWindow(false)
                .setHasBorders(false)
                .setFillColor(Color.parseColor("#00ff00"))
                .setStrokeColor( Color.RED)
                .setFillOpacity(3.0f)
                .create();
        ArrayList<PolygonModel> polygonModels = new ArrayList<>();
        polygonModels.add(polygonModel);
        drawSellPolygon(polygonModels);
    }
    protected void drawTarhAsli() {

        PolygonModel polygonModel = new PolygonModel.Builder()
                .setGroup_key(SELL_POLYGON_GROUP_ID())
                .setSource_id(INCREMENTAL_LOCATION_SOURCE_ID())
                .setLayer_id(INCREMENTAL_LOCATION_LAYER_ID())
                .setLatLng(getTarhAsliPoints())
                .setStrokeWidth(4f)
                .setHasInfoWindow(false)
                .setHasBorders(false)
                .setFillColor(Color.parseColor("#0F363FF4"))
                .setStrokeColor(Color.RED)
                .setFillOpacity(3.0f)
                .create();
        ArrayList<PolygonModel> polygonModels = new ArrayList<>();
        polygonModels.add(polygonModel);
        drawSellPolygon(polygonModels);
    }
    public GeoPoint getCurrentLocation()
    {
        LocationProvider locationProvider = new LocationProvider(context);
        GeoPoint geoPoint = new GeoPoint(locationProvider.getLatitude(), locationProvider.getLongitude());
        return geoPoint;
    }

    public ArrayList<LatLng> getTarhAsliPoints(){

        ArrayList<LatLng> tarhAsliLatLngs = new ArrayList<>();

        tarhAsliLatLngs.add(new LatLng(35.660886969808175,51.44502348527749));
        tarhAsliLatLngs.add(new LatLng(35.66078348452622,51.44475146343686));
        tarhAsliLatLngs.add(new LatLng(35.66072160922886,51.4435367221932154));
        tarhAsliLatLngs.add(new LatLng(35.6606372559873,51.442409193062429));
        tarhAsliLatLngs.add(new LatLng(35.66050545549325,51.440603711173566));
        tarhAsliLatLngs.add(new LatLng(35.6602482588293,51.438522767206166));
        tarhAsliLatLngs.add(new LatLng(35.65990616474984,51.43702832935966));
        tarhAsliLatLngs.add(new LatLng(35.65964821305903,51.43529217548249));
        tarhAsliLatLngs.add(new LatLng(35.65974282463952,51.4329007461418544));
        tarhAsliLatLngs.add(new LatLng(35.65976485328845,51.43139139224678));
        tarhAsliLatLngs.add(new LatLng(35.65966189111364,51.430366270304696));
        tarhAsliLatLngs.add(new LatLng(35.659582117057056,51.42720788535752));
        tarhAsliLatLngs.add(new LatLng(35.65934591975791,51.42443128794079));
        tarhAsliLatLngs.add(new LatLng(35.65917153907162,51.42114771133754));
        tarhAsliLatLngs.add(new LatLng(35.65891564683882,51.41733837395611));
        tarhAsliLatLngs.add(new LatLng(35.65880743633791,51.414724103028476));
        tarhAsliLatLngs.add(new LatLng(35.65869224593773,51.413314665417374));
        tarhAsliLatLngs.add(new LatLng(35.6582126022482,51.40980160204563));
        tarhAsliLatLngs.add(new LatLng(35.65817683273602,51.40767057448528));
        tarhAsliLatLngs.add(new LatLng(35.65856792774949,51.40504167689966));
        tarhAsliLatLngs.add(new LatLng(35.65880581311362,51.4018105817282));
        tarhAsliLatLngs.add(new LatLng(35.659196259192356,51.398668856215494));
        tarhAsliLatLngs.add(new LatLng(35.65953828457249,51.39822081682249));
        tarhAsliLatLngs.add(new LatLng(35.65976526678466,51.39779231779815));
        tarhAsliLatLngs.add(new LatLng(35.66119540080311,51.39671227221706));
        tarhAsliLatLngs.add(new LatLng(35.66396527993507,51.3963313418154));
        tarhAsliLatLngs.add(new LatLng(35.66632456726383,51.396012835865729));
        tarhAsliLatLngs.add(new LatLng(35.66866058835319,51.39564746718483));
        tarhAsliLatLngs.add(new LatLng(35.67236214764618,51.39507375257509));
        tarhAsliLatLngs.add(new LatLng(35.67478873414771,51.39482450427158));
        tarhAsliLatLngs.add(new LatLng(35.67793664290798,51.39438440402736));
        tarhAsliLatLngs.add(new LatLng(35.68215101116736,51.393752061587924));
        tarhAsliLatLngs.add(new LatLng(35.68516979055971,51.39334280765269));
        tarhAsliLatLngs.add(new LatLng(35.689240744413496,51.392893396906126));
        tarhAsliLatLngs.add(new LatLng(35.69036859006353,51.39265951153766));
        tarhAsliLatLngs.add(new LatLng(35.69315051546434,51.39235192205555));
        tarhAsliLatLngs.add(new LatLng(35.695969744967414,51.39204524073443));
        tarhAsliLatLngs.add(new LatLng(35.699671806238285,51.39149858079324));
        tarhAsliLatLngs.add(new LatLng(35.701439846898566,51.39125734328434));
        tarhAsliLatLngs.add(new LatLng(35.70469981411452,51.39076458363013));
        tarhAsliLatLngs.add(new LatLng(35.707437108235126,51.39039931960812));
        tarhAsliLatLngs.add(new LatLng(35.70998462476015,51.39001640280961));
        tarhAsliLatLngs.add(new LatLng(35.712970452861356,51.389710789919775));
        tarhAsliLatLngs.add(new LatLng(35.713757876443,51.38959773329307));
        tarhAsliLatLngs.add(new LatLng(35.71426244527366,51.390002215843964));
        tarhAsliLatLngs.add(new LatLng(35.71574705675651,51.394167507990886));
        tarhAsliLatLngs.add(new LatLng(35.717348093399934,51.39870173769435));
        tarhAsliLatLngs.add(new LatLng(35.71893707725293,51.403206524393596));
        tarhAsliLatLngs.add(new LatLng(35.71950135499952,51.404960450879464));
        tarhAsliLatLngs.add(new LatLng(35.719634778507626,51.405659580910999));
        tarhAsliLatLngs.add(new LatLng(35.719911842432495,51.406255228807595));
        tarhAsliLatLngs.add(new LatLng(35.720752011071326,51.40857499926696));
        tarhAsliLatLngs.add(new LatLng(35.72102457657988,51.40888167275591));
        tarhAsliLatLngs.add(new LatLng(35.721532186250016,51.408988345691114));
        tarhAsliLatLngs.add(new LatLng(35.72240124531811,51.409520635791894));
        tarhAsliLatLngs.add(new LatLng(35.723616458775695,51.41040714510177));
        tarhAsliLatLngs.add(new LatLng(35.72380952450354,51.418758615832396));
        tarhAsliLatLngs.add(new LatLng(35.723883425403,51.423532502966225));
        tarhAsliLatLngs.add(new LatLng(35.724160157437225,51.42892362658253));
        tarhAsliLatLngs.add(new LatLng(35.72429908476005,51.43420890707043));
        tarhAsliLatLngs.add(new LatLng(35.7244420141527,51.437362537208855));
        tarhAsliLatLngs.add(new LatLng(35.72415733551243,51.43894040779384));
        tarhAsliLatLngs.add(new LatLng(35.72398182400558,51.440015965748415));
        tarhAsliLatLngs.add(new LatLng(35.723858474380776,51.440729272149724));
        tarhAsliLatLngs.add(new LatLng(35.72381359603895,51.44093951560433));
        tarhAsliLatLngs.add(new LatLng(35.722018629893284,51.440333598669355));
        tarhAsliLatLngs.add(new LatLng(35.72068348962689,51.439940679359836));
        tarhAsliLatLngs.add(new LatLng(35.719314980770704,51.43944785642347));
        tarhAsliLatLngs.add(new LatLng(35.71808360986766,51.43907212478956));
        tarhAsliLatLngs.add(new LatLng(35.717840253108776,51.43901166277118));
        tarhAsliLatLngs.add(new LatLng(35.71740268960954,51.44028358610538));
        tarhAsliLatLngs.add(new LatLng(35.71716388489942,51.440445174069144));
        tarhAsliLatLngs.add(new LatLng(35.715427484655905,51.439462982263599));
        tarhAsliLatLngs.add(new LatLng(35.71355454488115,51.43831569334722));
        tarhAsliLatLngs.add(new LatLng(35.7124036205021,51.437675868948645));
        tarhAsliLatLngs.add(new LatLng(35.710463872475756,51.43653143709366));
        tarhAsliLatLngs.add(new LatLng(35.7079968540165,51.434759587308804));
        tarhAsliLatLngs.add(new LatLng(35.70707881602159,51.434043824831324));
        tarhAsliLatLngs.add(new LatLng(35.706379702951494,51.43365884069405));
        tarhAsliLatLngs.add(new LatLng(35.70645115764654,51.434625514452456));
        tarhAsliLatLngs.add(new LatLng(35.706625741934516,51.43684956826269));
        tarhAsliLatLngs.add(new LatLng(35.70671597888112,51.4384190134231));
        tarhAsliLatLngs.add(new LatLng(35.70672053707642,51.439352073220476));
        tarhAsliLatLngs.add(new LatLng(35.70658366702635,51.4398293080483));
        tarhAsliLatLngs.add(new LatLng(35.70616357899404,51.44018358435898));
        tarhAsliLatLngs.add(new LatLng(35.70504403270289,51.43976826334793));
        tarhAsliLatLngs.add(new LatLng(35.703869623062744,51.43925947320801));
        tarhAsliLatLngs.add(new LatLng(35.70222487410106,51.438600376143285));
        tarhAsliLatLngs.add(new LatLng(35.70088241458052,51.438052210894824));
        tarhAsliLatLngs.add(new LatLng(35.70033835911302,51.437751637394285));
        tarhAsliLatLngs.add(new LatLng(35.69929207932536,51.437139841673));
        tarhAsliLatLngs.add(new LatLng(35.698940823979,51.436931196651585));
        tarhAsliLatLngs.add(new LatLng(35.698734345965974,51.43720570243269));
        tarhAsliLatLngs.add(new LatLng(35.69884023349998,51.43742300540458));
        tarhAsliLatLngs.add(new LatLng(35.69862280862813,51.4379746086228244));
        tarhAsliLatLngs.add(new LatLng(35.69963702668194,51.4404365722495249));
        tarhAsliLatLngs.add(new LatLng(35.70034705027123,51.44301132769354));
        tarhAsliLatLngs.add(new LatLng(35.70101722248323,51.44543922538412));
        tarhAsliLatLngs.add(new LatLng(35.701502173578604,51.44704923154356));
        tarhAsliLatLngs.add(new LatLng(35.70162310563792,51.447486283190955));
        tarhAsliLatLngs.add(new LatLng(35.701244203425816,51.4481843422873064));
        tarhAsliLatLngs.add(new LatLng(35.70004062177857,51.4480744777685));
        tarhAsliLatLngs.add(new LatLng(35.698705757716965,51.447973360585056));
        tarhAsliLatLngs.add(new LatLng(35.69867070140563,51.44783945958105));
        tarhAsliLatLngs.add(new LatLng(35.69736202499216,51.447813638350794));
        tarhAsliLatLngs.add(new LatLng(35.69590538348801,51.447636035262434));
        tarhAsliLatLngs.add(new LatLng(35.694255858843036,51.44753895016126));
        tarhAsliLatLngs.add(new LatLng(35.6926628702342,51.44744888147622));
        tarhAsliLatLngs.add(new LatLng(35.69120930196216,51.4474139005427));
        tarhAsliLatLngs.add(new LatLng(35.68997909186875,51.44725874662055));
        tarhAsliLatLngs.add(new LatLng(35.68861369355611,51.44723460361456));
        tarhAsliLatLngs.add(new LatLng(35.6870478457617,51.44709984920789));
        tarhAsliLatLngs.add(new LatLng(35.68488534400184,51.446924000620754));
        tarhAsliLatLngs.add(new LatLng(35.682824936805076,51.44676288917569));
        tarhAsliLatLngs.add(new LatLng(35.68102574997167,51.446662361673134));
        tarhAsliLatLngs.add(new LatLng(35.679151236877175,51.4465561265647));
        tarhAsliLatLngs.add(new LatLng(35.67737590696264,51.44638938736222));
        tarhAsliLatLngs.add(new LatLng(35.67551730566808,51.446234513338425));
        tarhAsliLatLngs.add(new LatLng(35.669816775050776,51.4458591517793));
        tarhAsliLatLngs.add(new LatLng(35.66751485237482,51.44573542429404));
        tarhAsliLatLngs.add(new LatLng(35.66389853062984,51.44536550856151));
        tarhAsliLatLngs.add(new LatLng(35.66229831365047,51.44527704223316));
        tarhAsliLatLngs.add(new LatLng(35.66104394731457,51.44508960841338));



        return tarhAsliLatLngs;
    }

    public ArrayList<LatLng> getOddEvenPoints()
    {
        ArrayList<LatLng> OddEvenLatLngs = new ArrayList<>();
        OddEvenLatLngs.add(new LatLng(35.75011215079002,51.3863051447463));
        OddEvenLatLngs.add(new LatLng(35.75086818135888,51.39657922295453));
        OddEvenLatLngs.add(new LatLng(35.751058559529994,51.40137995208511));
        OddEvenLatLngs.add(new LatLng(35.75107900553259,51.404064045342665));
        OddEvenLatLngs.add(new LatLng(35.75110504347957,51.404490749453174));
        OddEvenLatLngs.add(new LatLng(35.75120072309858,51.40894097691333));
        OddEvenLatLngs.add(new LatLng(35.75135864735175,51.410362210302424));
        OddEvenLatLngs.add(new LatLng(35.75147857568369,51.41134713728516));
        OddEvenLatLngs.add(new LatLng(35.75192727460943,51.41349041156735));
        OddEvenLatLngs.add(new LatLng(35.75198417290969,51.41492119161077));
        OddEvenLatLngs.add(new LatLng(35.752051464062674,51.41530332738807));
        OddEvenLatLngs.add(new LatLng(35.7519634686433,51.41568596011285));
        OddEvenLatLngs.add(new LatLng(35.75154154729242,51.4174150326121));
        OddEvenLatLngs.add(new LatLng(35.75024469442624,51.4198348988146));
        OddEvenLatLngs.add(new LatLng(35.74932124556152,51.4216637792199));
        OddEvenLatLngs.add(new LatLng(35.74909119357521,51.42300914909612));
        OddEvenLatLngs.add(new LatLng(35.74908958342098,51.42548653985932));
        OddEvenLatLngs.add(new LatLng(35.749065432054195,51.42789232560298));
        OddEvenLatLngs.add(new LatLng(35.749027394300526,51.43379954373338));
        OddEvenLatLngs.add(new LatLng(35.7494964748542,51.436946070620394));
        OddEvenLatLngs.add(new LatLng(35.75006590958904,51.43873940565848));
        OddEvenLatLngs.add(new LatLng(35.75051065299729,51.44107104495947));
        OddEvenLatLngs.add(new LatLng(35.75068753055007,51.44459789265551));
        OddEvenLatLngs.add(new LatLng(35.75084244083156,51.44578142834919));
        OddEvenLatLngs.add(new LatLng(35.7514691636502,51.448388740975616));
        OddEvenLatLngs.add(new LatLng(35.752569277861525,51.45075588196315));
        OddEvenLatLngs.add(new LatLng(35.753646277395845,51.45240999420409));
        OddEvenLatLngs.add(new LatLng(35.75427088807015,51.453413640470984));
        OddEvenLatLngs.add(new LatLng(35.754671721913894,51.45422795520463));
        OddEvenLatLngs.add(new LatLng(35.75576499018503,51.45733552243621));
        OddEvenLatLngs.add(new LatLng(35.756030463635554,51.45849377769372));
        OddEvenLatLngs.add(new LatLng(35.756438593769076,51.46216995871415));
        OddEvenLatLngs.add(new LatLng(35.756537483635285,51.46520574297304));
        OddEvenLatLngs.add(new LatLng(35.756557107967154,51.46873922720297));
        OddEvenLatLngs.add(new LatLng(35.75676822814802,51.472075158216285));
        OddEvenLatLngs.add(new LatLng(35.75705250994643,51.47336124223122));
        OddEvenLatLngs.add(new LatLng(35.75791305480969,51.475736545002036));
        OddEvenLatLngs.add(new LatLng(35.75876215135703,51.47828259772149));
        OddEvenLatLngs.add(new LatLng(35.75918425594058,51.48081213917328));
        OddEvenLatLngs.add(new LatLng(35.7590885624285,51.48269109319173));
        OddEvenLatLngs.add(new LatLng(35.75878671359551,51.48397457053869));
        OddEvenLatLngs.add(new LatLng(35.75870705111002,51.484558017834786));
        OddEvenLatLngs.add(new LatLng(35.75773508723583,51.484807514997385));
        OddEvenLatLngs.add(new LatLng(35.756606895649895,51.48499453234174));
        OddEvenLatLngs.add(new LatLng(35.755947327724655,51.48509918660957));
        OddEvenLatLngs.add(new LatLng(35.75448009953452,51.485250188054266));
        OddEvenLatLngs.add(new LatLng(35.753196185462954,51.48537172516254));
        OddEvenLatLngs.add(new LatLng(35.75258402392264,51.485295015941034));
        OddEvenLatLngs.add(new LatLng(35.75092152331928,51.48462938983013));
        OddEvenLatLngs.add(new LatLng(35.750215203719534,51.48423229900413));
        OddEvenLatLngs.add(new LatLng(35.74938756821727,51.48391489094186));
        OddEvenLatLngs.add(new LatLng(35.74737392966182,51.48339617497538));
        OddEvenLatLngs.add(new LatLng(35.74564717843147,51.483095478133066));
        OddEvenLatLngs.add(new LatLng(35.74399108044352,51.4832898087968));
        OddEvenLatLngs.add(new LatLng(35.74184300538491,51.48371886397538));
        OddEvenLatLngs.add(new LatLng(35.740922176306995,51.48370098959427));
        OddEvenLatLngs.add(new LatLng(35.738943213285125,51.48358121309053));
        OddEvenLatLngs.add(new LatLng(35.73764757266731,51.483124318669695));
        OddEvenLatLngs.add(new LatLng(35.73583390107234,51.48248836534131));
        OddEvenLatLngs.add(new LatLng(35.73495568900131,51.482196227559086));
        OddEvenLatLngs.add(new LatLng(35.73407279253651,51.48142880074937));
        OddEvenLatLngs.add(new LatLng(35.73295855026946,51.480479391418044));
        OddEvenLatLngs.add(new LatLng(35.73231371588696,51.47986041140521));
        OddEvenLatLngs.add(new LatLng(35.73149544244187,51.479166930728326));
        OddEvenLatLngs.add(new LatLng(35.72996456360562,51.47783813445295));
        OddEvenLatLngs.add(new LatLng(35.72809147874162,51.475660306005096));
        OddEvenLatLngs.add(new LatLng(35.725308505685135,51.47265849595465));
        OddEvenLatLngs.add(new LatLng(35.724566859102225,51.47154310262869));
        OddEvenLatLngs.add(new LatLng(35.72360620383468,51.46998195263231));
        OddEvenLatLngs.add(new LatLng(35.7223628624277,51.46759905356794));
        OddEvenLatLngs.add(new LatLng(35.72108291045548,51.465078764145886));
        OddEvenLatLngs.add(new LatLng(35.71990541151288,51.4631486753384));
        OddEvenLatLngs.add(new LatLng(35.71843903552262,51.46053059623182));
        OddEvenLatLngs.add(new LatLng(35.717569698410216,51.45917334786324));
        OddEvenLatLngs.add(new LatLng(35.716590895076834,51.45816010699235));
        OddEvenLatLngs.add(new LatLng(35.715666091269,51.45723424375646));
        OddEvenLatLngs.add(new LatLng(35.71419167958278,51.45655500624221));
        OddEvenLatLngs.add(new LatLng(35.71255088440229,51.45642504864301));
        OddEvenLatLngs.add(new LatLng(35.71106059176023,51.45665662435778));
        OddEvenLatLngs.add(new LatLng(35.70880475340671,51.456972004893856));
        OddEvenLatLngs.add(new LatLng(35.70683346448074,51.457251533956565));
        OddEvenLatLngs.add(new LatLng(35.70483820558751,51.45758864337853));
        OddEvenLatLngs.add(new LatLng(35.70094182776887,51.45721245811643));
        OddEvenLatLngs.add(new LatLng(35.697076197644506,51.45665530465646));
        OddEvenLatLngs.add(new LatLng(35.69521506721463,51.45624524779433));
        OddEvenLatLngs.add(new LatLng(35.69259072212597,51.45668060114971));
        OddEvenLatLngs.add(new LatLng(35.690433374113184,51.45686298710095));
        OddEvenLatLngs.add(new LatLng(35.68655999881355,51.45805627474405));
        OddEvenLatLngs.add(new LatLng(35.68328151265864,51.45991200105718));
        OddEvenLatLngs.add(new LatLng(35.680533656929896,51.45970165647583));
        OddEvenLatLngs.add(new LatLng(35.67829092949411,51.45913659360889));
        OddEvenLatLngs.add(new LatLng(35.67630543883753,51.459204399313876));
        OddEvenLatLngs.add(new LatLng(35.6741717483811,51.46039422023594));
        OddEvenLatLngs.add(new LatLng(35.67167175903229,51.46332785716879));
        OddEvenLatLngs.add(new LatLng(35.6704042764339,51.464248613244166));
        OddEvenLatLngs.add(new LatLng(35.66884063884187,51.46482986229094));
        OddEvenLatLngs.add(new LatLng(35.6659022785458,51.46391528138605));
        OddEvenLatLngs.add(new LatLng(35.662996616428245,51.46233477856916));
        OddEvenLatLngs.add(new LatLng(35.66082008748735,51.46135330885798));
        OddEvenLatLngs.add(new LatLng(35.6583688717333,51.46071233756544));
        OddEvenLatLngs.add(new LatLng(35.65467355398927,51.460621723650036));
        OddEvenLatLngs.add(new LatLng(35.65204259523138,51.46115168994885));
        OddEvenLatLngs.add(new LatLng(35.64974492428716,51.46174629890916));
        OddEvenLatLngs.add(new LatLng(35.64749164723318,51.462392362289705));
        OddEvenLatLngs.add(new LatLng(35.64558611131588,51.46292722530194));
        OddEvenLatLngs.add(new LatLng(35.64356554738794,51.463335709846376));
        OddEvenLatLngs.add(new LatLng(35.643003526039735,51.46342184471342));
        OddEvenLatLngs.add(new LatLng(35.643359744227,51.46238514822909));
        OddEvenLatLngs.add(new LatLng(35.64417350943289,51.460592928847404));
        OddEvenLatLngs.add(new LatLng(35.64480261332369,51.45862915562165));
        OddEvenLatLngs.add(new LatLng(35.645264707199786,51.45658051531072));
        OddEvenLatLngs.add(new LatLng(35.64560235703969,51.45398772945882));
        OddEvenLatLngs.add(new LatLng(35.645655143595704,51.45149012342483));
        OddEvenLatLngs.add(new LatLng(35.64560370409279,51.44928663025914));
        OddEvenLatLngs.add(new LatLng(35.64538064192293,51.447192259513855));
        OddEvenLatLngs.add(new LatLng(35.64521696058384,51.44529615318592));
        OddEvenLatLngs.add(new LatLng(35.64507782924841,51.443731872345836));
        OddEvenLatLngs.add(new LatLng(35.645054274645005,51.44213337444429));
        OddEvenLatLngs.add(new LatLng(35.64515830225807,51.43984012179834));
        OddEvenLatLngs.add(new LatLng(35.6452642660919,51.43792895939404));
        OddEvenLatLngs.add(new LatLng(35.64543628138274,51.43660861737882));
        OddEvenLatLngs.add(new LatLng(35.64562301353706,51.434802539125826));
        OddEvenLatLngs.add(new LatLng(35.6459210734961,51.43256047109307));
        OddEvenLatLngs.add(new LatLng(35.64619594260162,51.430384165279776));
        OddEvenLatLngs.add(new LatLng(35.646371523463785,51.42762172683291));
        OddEvenLatLngs.add(new LatLng(35.646913203609145,51.42331005648609));
        OddEvenLatLngs.add(new LatLng(35.64733192548481,51.4194658585308));
        OddEvenLatLngs.add(new LatLng(35.647583366475445,51.41684427824575));
        OddEvenLatLngs.add(new LatLng(35.64794163156081,51.41375390390991));
        OddEvenLatLngs.add(new LatLng(35.64814235741211,51.41212427746956));
        OddEvenLatLngs.add(new LatLng(35.64837730386927,51.40956731655194));
        OddEvenLatLngs.add(new LatLng(35.64876641151551,51.40660494958604));
        OddEvenLatLngs.add(new LatLng(35.64922741329784,51.40238715476079));
        OddEvenLatLngs.add(new LatLng(35.64953899323011,51.39916123437595));
        OddEvenLatLngs.add(new LatLng(35.64909650670211,51.39766039734306));
        OddEvenLatLngs.add(new LatLng(35.64855905740008,51.39710043441346));
        OddEvenLatLngs.add(new LatLng(35.64756986547661,51.39559620978983));
        OddEvenLatLngs.add(new LatLng(35.64619524337415,51.394073826163975));
        OddEvenLatLngs.add(new LatLng(35.64528110526935,51.393507618552775));
        OddEvenLatLngs.add(new LatLng(35.64408074736534,51.392968356970925));
        OddEvenLatLngs.add(new LatLng(35.645223056411,51.39278375968493));
        OddEvenLatLngs.add(new LatLng(35.646219389256714,51.39216481947139));
        OddEvenLatLngs.add(new LatLng(35.64740796849816,51.39159063465604));
        OddEvenLatLngs.add(new LatLng(35.648778198400734,51.39028614500665));
        OddEvenLatLngs.add(new LatLng(35.650370036345535,51.388521603833));
        OddEvenLatLngs.add(new LatLng(35.65175501081494,51.386512740916146));
        OddEvenLatLngs.add(new LatLng(35.65281755405168,51.384524103705985));
        OddEvenLatLngs.add(new LatLng(35.653819572791846,51.38309513171956));
        OddEvenLatLngs.add(new LatLng(35.65477455341974,51.38271679667827));
        OddEvenLatLngs.add(new LatLng(35.65645833659855,51.38264145957618));
        OddEvenLatLngs.add(new LatLng(35.65855828139992,51.382523034636534));
        OddEvenLatLngs.add(new LatLng(35.66087827451213,51.38240351778222));
        OddEvenLatLngs.add(new LatLng(35.66337580922655,51.38222693735446));
        OddEvenLatLngs.add(new LatLng(35.66514902663236,51.382068681744045));
        OddEvenLatLngs.add(new LatLng(35.667745699355365,51.38201457959596));
        OddEvenLatLngs.add(new LatLng(35.6699170337926,51.38193814273842));
        OddEvenLatLngs.add(new LatLng(35.6715685320232,51.38179688734587));
        OddEvenLatLngs.add(new LatLng(35.673388830988955,51.38163616079438));
        OddEvenLatLngs.add(new LatLng(35.67526318750096,51.381266239342125));
        OddEvenLatLngs.add(new LatLng(35.67826050661118,51.38093543999804));
        OddEvenLatLngs.add(new LatLng(35.68046165681325,51.38066902609182));
        OddEvenLatLngs.add(new LatLng(35.68290661837783,51.3802602031013));
        OddEvenLatLngs.add(new LatLng(35.6857543194632,51.3799850507574));
        OddEvenLatLngs.add(new LatLng(35.68869840416322,51.379779281489476));
        OddEvenLatLngs.add(new LatLng(35.691695555398084,51.37938357344251));
        OddEvenLatLngs.add(new LatLng(35.69315083257152,51.37924987727874));
        OddEvenLatLngs.add(new LatLng(35.69636782084707,51.378775161559986));
        OddEvenLatLngs.add(new LatLng(35.69674735106268,51.37897354968604));
        OddEvenLatLngs.add(new LatLng(35.69693797306765,51.378944731369955));
        OddEvenLatLngs.add(new LatLng(35.69722724270535,51.3786978073785));
        OddEvenLatLngs.add(new LatLng(35.70050150134321,51.37836678078048));
        OddEvenLatLngs.add(new LatLng(35.70511149543627,51.37791242283413));
        OddEvenLatLngs.add(new LatLng(35.710122626920366,51.37817472438638));
        OddEvenLatLngs.add(new LatLng(35.71266056872045,51.3783370111349));
        OddEvenLatLngs.add(new LatLng(35.7153361541945,51.37846218596991));
        OddEvenLatLngs.add(new LatLng(35.71825032627514,51.378611422999114));
        OddEvenLatLngs.add(new LatLng(35.71923700394666,51.37869504531136));
        OddEvenLatLngs.add(new LatLng(35.721838177260864,51.37973090858057));
        OddEvenLatLngs.add(new LatLng(35.725040364314395,51.3817352953825));
        OddEvenLatLngs.add(new LatLng(35.727395259920186,51.383090249401135));
        OddEvenLatLngs.add(new LatLng(35.72999617484291,51.38353771206903));
        OddEvenLatLngs.add(new LatLng(35.73312531453534,51.38360079293662));
        OddEvenLatLngs.add(new LatLng(35.73581582295782,51.38378140464255));
        OddEvenLatLngs.add(new LatLng(35.738650974471156,51.383780439653265));
        OddEvenLatLngs.add(new LatLng(35.74118469665078,51.3838690184501));
        OddEvenLatLngs.add(new LatLng(35.743166131656096,51.38434629847487));
        OddEvenLatLngs.add(new LatLng(35.74713516960813,51.38616607124578));
        OddEvenLatLngs.add(new LatLng(35.74826518821749,51.38617639412658));
        OddEvenLatLngs.add(new LatLng(35.74949437933448,51.38625952001854));

        return OddEvenLatLngs;
    }

}




