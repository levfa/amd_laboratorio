package fabianleven.cristianmilapallas.valenbisi;

import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetalleParada extends AppCompatActivity {
    private TextView numeroTV;
    private TextView addressTV;
    private TextView totalSlotsTV;
    private TextView availableBikesTV;
    private TextView freeSlotsTV;
    private TextView coordinatesTV;
    private ListView incidentsLV;
    private ImageButton openMapBt;
    private ImageButton addIncidentBt;

    private Parada parada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_parada);
        parada = (Parada) getIntent().getSerializableExtra(ListaParadas.STATION_KEY);
        setTitle(parada.name);
        numeroTV = (TextView) findViewById(R.id.detalle_number);
        addressTV = (TextView) findViewById(R.id.detalle_address);
        totalSlotsTV = (TextView) findViewById(R.id.detalle_total);
        availableBikesTV = (TextView) findViewById(R.id.detalle_available);
        freeSlotsTV = (TextView) findViewById(R.id.detalle_freeslots);
        coordinatesTV = (TextView) findViewById(R.id.detalle_coordinates);
        incidentsLV = (ListView) findViewById(R.id.detalle_incidents);
        openMapBt = (ImageButton) findViewById(R.id.detalle_openmap);
        addIncidentBt = (ImageButton) findViewById(R.id.detalle_addincident);

        numeroTV.setText(String.valueOf(parada.number));
        addressTV.setText(parada.address);
        String totalSlots_string = parada.totalSlots == -1 ? getString(R.string.DetalleParada_no_hay_data) : String.valueOf(parada.totalSlots);
        totalSlotsTV.setText(totalSlots_string);
        String availableSlots_string = parada.availableBikes == -1 ? getString(R.string.DetalleParada_no_hay_data) : String.valueOf(parada.availableBikes);
        availableBikesTV.setText(availableSlots_string);
        String freeSlots_string = parada.freeSlots == -1 ? getString(R.string.DetalleParada_no_hay_data) : String.valueOf(parada.freeSlots);
        freeSlotsTV.setText(freeSlots_string);
        String coordinates_as_string = parada.coordinates.latitude + ", " + parada.coordinates.longitude;
        coordinatesTV.setText(coordinates_as_string);

        ArrayList<String> dummyIncidents = new ArrayList<String>();
        dummyIncidents.add("Incidencia 1");
        dummyIncidents.add("Incidencia 2");
        dummyIncidents.add("Incidencia 3");
        dummyIncidents.add("Incidencia 4");
        dummyIncidents.add("Incidencia 5");
        dummyIncidents.add("Incidencia 6");
        dummyIncidents.add("Incidencia 7");
        dummyIncidents.add("Incidencia 8");
        dummyIncidents.add("Incidencia 9");
        incidentsLV.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, dummyIncidents));


        openMapBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri;
                gmmIntentUri = Uri.parse("geo:0,0?q="+parada.coordinates.latitude+","+parada.coordinates.longitude+"("+parada.address+")");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
    }
}
