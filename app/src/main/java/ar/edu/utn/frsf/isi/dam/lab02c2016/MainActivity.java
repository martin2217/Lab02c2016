package ar.edu.utn.frsf.isi.dam.lab02c2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ElementoMenu[] listaBebidas;
    private ElementoMenu[] listaPlatos;
    private ElementoMenu[] listaPostre;
    private ElementoMenu[] listaActual;

    /*
     * Declaración de objetos de la vista
     */
    private ToggleButton toggleBtn;
    private Spinner spinner;
    private Switch switch1;
    private TextView textView;
    private RadioGroup radioGroup;
    private RadioButton radioBtn1;
    private RadioButton radioBtn2;
    private RadioButton radioBtn3;
    private Button boton1;
    private Button boton2;
    private Button boton3;
    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarListas();

        toggleBtn = (ToggleButton) findViewById(R.id.toggleButton);
        spinner = (Spinner) findViewById(R.id.spinner);
        //switch1 = (Switch) findViewById(R.id.switch1);
        textView = (TextView) findViewById(R.id.textView4);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(handler_radioGroup);
        radioBtn1 = (RadioButton) findViewById(R.id.radioBtn1);
        radioBtn2 = (RadioButton) findViewById(R.id.radioBtn2);
        radioBtn3 = (RadioButton) findViewById(R.id.radioBtn3);
        boton1 = (Button) findViewById(R.id.button);
        boton1.setOnClickListener(listener_boton1);
        boton2 = (Button) findViewById(R.id.button2);
        boton1.setOnClickListener(listener_boton2);
        boton3 = (Button) findViewById(R.id.button3);
        boton1.setOnClickListener(listener_boton3);
        listView1 = (ListView) findViewById(R.id.listView);

        // Propiedad de scrolling para el textView
        textView.setMovementMethod(new ScrollingMovementMethod());




        // Llenado del spinner de horarios
        ArrayAdapter<CharSequence> adapter_horarios=
                ArrayAdapter.createFromResource(this,
                        R.array.horarios,
                        android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_horarios);
        /*
         * Alternativa
        final String[] horarios = new String[]{"20:00", "20:30", "21:00", "21:30", "22:00"};
        ArrayAdapter<String> adapter_horarios =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, horarios);
        spinner.setAdapter(adapter_horarios);
        */
    }

    // Manejador del radioGroup
    RadioGroup.OnCheckedChangeListener handler_radioGroup = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId){
            switch (checkedId){
                case -1:
                    break;
                case R.id.radioBtn1:
                    listaActual=listaPlatos;
                    break;
                case R.id.radioBtn2:
                    listaActual=listaPostre;
                    break;
                case R.id.radioBtn3:
                    listaActual=listaBebidas;
                    break;
            }
            
            ArrayAdapter<ElementoMenu> adapter_radio = new ArrayAdapter<ElementoMenu>(MainActivity.this, android.R.layout.simple_list_item_single_choice, listaActual);
            listView1.setAdapter(adapter_radio);
        }
    };


    private TextView selection;
    private static final String[] items = {"lorem", "ipsum", "purus"};
    /*setListAdapter(new ArrayAdapter<String>(this, R.layout.fil, R.id.label, items));
    selection=(TextView)

    findViewById(R.id.selection);

    public void onListItemClick(ListViewparent, View v, intposition, longid) {
        selection.setText(items[position]);
    }*/

    /*
     * Listener de botones
     *      Agregar
     *      Confirmar
     *      Reiniciar
     */
    View.OnClickListener listener_boton1 = new View.OnClickListener() {
        public void onClick(View v) {
            // TODO btn1
        }
    };
    View.OnClickListener listener_boton2 = new View.OnClickListener() {
        public void onClick(View v) {
            // TODO btn2
        }
    };
    View.OnClickListener listener_boton3 = new View.OnClickListener() {
        public void onClick(View v) {
            // TODO btn3
        }
    };



    private void iniciarListas() {
        // inicia lista de bebidas
        listaBebidas = new ElementoMenu[7];
        listaBebidas[0] = new ElementoMenu(1, "Coca");
        listaBebidas[1] = new ElementoMenu(4, "Jugo");
        listaBebidas[2] = new ElementoMenu(6, "Agua");
        listaBebidas[3] = new ElementoMenu(8, "Soda");
        listaBebidas[4] = new ElementoMenu(9, "Fernet");
        listaBebidas[5] = new ElementoMenu(10, "Vino");
        listaBebidas[6] = new ElementoMenu(11, "Cerveza");
        // inicia lista de platos
        listaPlatos = new ElementoMenu[14];
        listaPlatos[0] = new ElementoMenu(1, "Ravioles");
        listaPlatos[1] = new ElementoMenu(2, "Gnocchi");
        listaPlatos[2] = new ElementoMenu(3, "Tallarines");
        listaPlatos[3] = new ElementoMenu(4, "Lomo");
        listaPlatos[4] = new ElementoMenu(5, "Entrecot");
        listaPlatos[5] = new ElementoMenu(6, "Pollo");
        listaPlatos[6] = new ElementoMenu(7, "Pechuga");
        listaPlatos[7] = new ElementoMenu(8, "Pizza");
        listaPlatos[8] = new ElementoMenu(9, "Empanadas");
        listaPlatos[9] = new ElementoMenu(10, "Milanesas");
        listaPlatos[10] = new ElementoMenu(11, "Picada 1");
        listaPlatos[11] = new ElementoMenu(12, "Picada 2");
        listaPlatos[12] = new ElementoMenu(13, "Hamburguesa");
        listaPlatos[12] = new ElementoMenu(14, "Calamares");
        // inicia lista de postres
        listaPostre = new ElementoMenu[15];
        listaPostre[0] = new ElementoMenu(1, "Helado");
        listaPostre[1] = new ElementoMenu(2, "Ensalada de Frutas");
        listaPostre[2] = new ElementoMenu(3, "Macedonia");
        listaPostre[3] = new ElementoMenu(4, "Brownie");
        listaPostre[4] = new ElementoMenu(5, "Cheescake");
        listaPostre[5] = new ElementoMenu(6, "Tiramisu");
        listaPostre[6] = new ElementoMenu(7, "Mousse");
        listaPostre[7] = new ElementoMenu(8, "Fondue");
        listaPostre[8] = new ElementoMenu(9, "Profiterol");
        listaPostre[9] = new ElementoMenu(10, "Selva Negra");
        listaPostre[10] = new ElementoMenu(11, "Lemon Pie");
        listaPostre[11] = new ElementoMenu(12, "KitKat");
        listaPostre[12] = new ElementoMenu(13, "IceCreamSandwich");
        listaPostre[13] = new ElementoMenu(14, "Frozen Yougurth");
        listaPostre[14] = new ElementoMenu(15, "Queso y Batata");
    }

}
