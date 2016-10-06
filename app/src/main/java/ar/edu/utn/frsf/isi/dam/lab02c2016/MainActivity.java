package ar.edu.utn.frsf.isi.dam.lab02c2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ElementoMenu[] listaBebidas;
    private ElementoMenu[] listaPlatos;
    private ElementoMenu[] listaPostre;
    private ElementoMenu[] listaActual;
    private ArrayList<ElementoMenu> pedidoActual;
    private ElementoMenu elementoSeleccionado;
    private boolean terminado;

    /*
     * Declaración de objetos de la vista
     */
    private ToggleButton toggleBtn;
    private Spinner spinner;
    private TextView textView;
    private RadioGroup radioGroup;
    private Button boton1;
    private Button boton2;
    private Button boton3;
    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializaciones
        iniciarListas();
        elementoSeleccionado =null;
        terminado=false;
        listaActual=null;
        pedidoActual= new ArrayList<>();

        toggleBtn = (ToggleButton) findViewById(R.id.toggleButton);
        spinner = (Spinner) findViewById(R.id.spinner);

        textView = (TextView) findViewById(R.id.textView4);


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(handler_radioGroup);


        /*
         * Listener de botones
         *      Agregar
         *      Confirmar
         *      Reiniciar
         */

        // Agregar
        boton1 = (Button) findViewById(R.id.button);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (terminado) {
                    Toast.makeText(MainActivity.this.getApplicationContext(), "Pedido ya confirmado", Toast.LENGTH_SHORT).show();
                } else if (elementoSeleccionado == null) {
                    Toast.makeText(MainActivity.this.getApplicationContext(), "Debe seleccionar un item", Toast.LENGTH_SHORT).show();
                } else {
                    pedidoActual.add(elementoSeleccionado);
                    StringBuilder sb = new StringBuilder(textView.getText());

                    if (sb.length() > 0) {
                        sb.append(System.getProperty("line.separator"));
                    }
                    sb.append(elementoSeleccionado.toString());
                    textView.setText(sb.toString());
                }
            }
        });

        // Confirmar
        boton2 = (Button) findViewById(R.id.button2);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (terminado){
                    Toast.makeText(MainActivity.this.getApplicationContext(), "Pedido ya confirmado", Toast.LENGTH_SHORT).show();
                }
                else if (pedidoActual.isEmpty()){
                    Toast.makeText(MainActivity.this.getApplicationContext(), "Debe agregar al menos un item", Toast.LENGTH_SHORT).show();
                }
                else {
                    terminado=true;
                    double total=0;
                    for(ElementoMenu em: pedidoActual){
                        total+=em.getPrecio();
                    }
                    StringBuilder sb = new StringBuilder(textView.getText());
                    sb.append(System.getProperty("line.separator"));
                    DecimalFormat f = new DecimalFormat("##.00");
                    sb.append("Total: "+f.format(total));
                    textView.setText(sb.toString());
                    //
                }
            }
        });

        // Reiniciar
        boton3 = (Button) findViewById(R.id.button3);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terminado=false;
                textView.setText("");
                textView.scrollTo(0, 0);
                pedidoActual= new ArrayList<>();
            }
        });


        listView1 = (ListView) findViewById(R.id.listView);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                elementoSeleccionado = listaActual[i];
                view.setSelected(true);
            }
        });
        listView1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


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
            // MAL, debería solo actualizar la listaActual, y avisar que se cambió la lista, y checked=-1, etc
            ArrayAdapter<ElementoMenu> adapter_radio = new ArrayAdapter<ElementoMenu>(MainActivity.this, android.R.layout.simple_list_item_single_choice, listaActual);
            listView1.setAdapter(adapter_radio);
            elementoSeleccionado = null;
        }
    };

    private void iniciarListas() {
        // inicia lista de bebidas
        listaBebidas = new ElementoMenu[7];
        listaBebidas[0] = new ElementoMenu(1, "Coca");
        listaBebidas[1] = new ElementoMenu(2, "Jugo");
        listaBebidas[2] = new ElementoMenu(3, "Agua");
        listaBebidas[3] = new ElementoMenu(4, "Soda");
        listaBebidas[4] = new ElementoMenu(5, "Fernet");
        listaBebidas[5] = new ElementoMenu(6, "Vino");
        listaBebidas[6] = new ElementoMenu(7, "Cerveza");
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
        listaPlatos[13] = new ElementoMenu(14, "Calamares");
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
