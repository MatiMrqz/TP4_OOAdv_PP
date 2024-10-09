package Dispositivos;

import Almacen.Carroceria;
import Almacen.Motor;
import Almacen.Vehiculo;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UIDispositivo extends JFrame {
    private JPanel contentPane;
    private JButton buttonExit;
    private JEditorPane textOut;
    private JButton crearButton;
    private JTextField nMotor;
    private JTextField nCarroceria;
    private JTextField pMotor;
    private JTextField pCarroceria;
    private JTextField cantCilindro;
    private JTextField cilindrada;
    private JTextField descCarroceria;
    private JTextField colCarroceria;
    private JLabel lnMotor;
    private JLabel lnCarroceria;
    private JLabel lpMotor;
    private JLabel lpCarroceria;
    private JLabel lcCilindro;
    private JLabel lCilindrada;
    private JLabel ldCarroceria;
    private JLabel lColCarroceria;
    private JTextField marca;
    private JComboBox<Inventario> inventarioCombo;
    private JPanel Form;
    private JPanel Botonera;
    private JPanel Salida;
    private JPanel Componente;
    private JPanel Radio_Buttons;
    private JPanel Inventario;
    private JRadioButton inventarioRadioButton;
    private JRadioButton vehiculoRadioButton;
    private JLabel inventarioComboLabel;
    private JLabel lMarca;
    private JLabel lNuevoInv;
    private JLabel lNuevoCo;
    private JTextField peso;
    private JLabel lpeso;
    private JRadioButton componenteRadioButton;
    private JComboBox<Vehiculo> vehiculoCombo;
    private JLabel lNuevoVe;
    private JLabel lVehiculoCombo;
    private JPanel Vehiculo;
    private String consoleContent="";
    private RadiusOptions radiusSelected;
    private final ArrayList<Inventario> inventarios = new ArrayList<>();
    private final ButtonGroup objectsForm = new ButtonGroup();

    public UIDispositivo() {
        setTitle("UI TP 4");
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonExit);
        objectsForm.add(inventarioRadioButton);
        objectsForm.add(vehiculoRadioButton);
        objectsForm.add(componenteRadioButton);
        textOut.setContentType("text/html");
        resetFormControls();

        // call onExit() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onExit();
            }
        });

        // call onExit() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onExit();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        inventarioRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableFields(RadiusOptions.INVENTARIO);
            }
        });
        vehiculoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableFields(RadiusOptions.VEHICULO);
            }
        });
        componenteRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableFields(RadiusOptions.COMPONENTE);
                onVehiculoSelected();
            }
        });

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (radiusSelected){
                    case INVENTARIO:
                        try{
                            if(!validString(marca)) throw new Exception("Marca debe estar especificada");
                            creaInventario(marca.getText());
                            resetFormControls();
                        }catch (Exception ex){
                            printlnError(ex.getMessage());
                        }
                        break;
                    case VEHICULO:
                        try{
                            if(inventarioCombo.getItemAt(inventarioCombo.getSelectedIndex())==null) throw new Exception("Debe seleccionar un Inventario");
                            if(!validInt(peso)) throw new Exception("Peso debe ser numerico y mayor que 0");
                            creaVehiculo(inventarioCombo.getItemAt(inventarioCombo.getSelectedIndex()),Integer.parseInt(peso.getText()));
                            resetFormControls();
                        } catch (Exception ex) {
                            printlnError(ex.getMessage());
                        }
                        break;
                    case COMPONENTE:
                        try{
                            if(vehiculoCombo.getItemAt(vehiculoCombo.getSelectedIndex())==null)throw new Exception("Debe seleccionar un Vehículo");
                            if(!validInt(nMotor))throw new Exception("Nº Motor debe ser numerico y mayor que 0");
                            if(!validInt(nCarroceria))throw new Exception("Nº Carroceria debe ser numerico y mayor que 0");
                            if(!validFloat(pMotor))throw new Exception("Precio Motor debe ser numerico y mayor que 0");
                            if(!validFloat(pCarroceria))throw new Exception("Precio Carrocería debe ser numerico y mayor que 0");
                            if(!validInt(cantCilindro))throw new Exception("Cantidad de Cilindros debe ser numerico y mayor que 0");
                            if(!validInt(cilindrada))throw new Exception("Cilindrada debe ser numerico y mayor que 0");
                            if(!validString(descCarroceria)) throw new Exception("Descripcion carroceria no puede estar vacío");
                            if(!validString(colCarroceria)) throw new Exception("Color carroceria no puede estar vacio");
                            creaComponente(vehiculoCombo.getItemAt(vehiculoCombo.getSelectedIndex()),Integer.parseInt(nMotor.getText()),Integer.parseInt(nCarroceria.getText()),Float.parseFloat(pMotor.getText()),Float.parseFloat(pCarroceria.getText()),Integer.parseInt(cantCilindro.getText()),Integer.parseInt(cilindrada.getText()),descCarroceria.getText(),colCarroceria.getText());
                            resetFormControls();
                        }catch (Exception ex){
                            printlnError(ex.getMessage());
                        }
                        break;
                }
            onVehiculoSelected();
            }
        });
        vehiculoCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onVehiculoSelected();
            }
        });
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onExit();
            }
        });
    }

    public void println(String text){
        consoleContent += text +"<br>";
        textOut.setText(consoleContent);
    }

    public void printlnError(String text){
        consoleContent += "<span style=\"color:red;\">"+text+"</span><br>";
        textOut.setText(consoleContent);
    }

    private void onExit() {
        System.exit(0);
        dispose();
    }

    public void run() {
        this.pack();
        this.setVisible(true);
    }

    private void enableFields(RadiusOptions selected){
        radiusSelected=selected;
        lNuevoInv.setEnabled(selected.equals(RadiusOptions.INVENTARIO));
        lMarca.setEnabled(selected.equals(RadiusOptions.INVENTARIO));
        marca.setEnabled(selected.equals(RadiusOptions.INVENTARIO));
        lNuevoVe.setEnabled(selected.equals(RadiusOptions.VEHICULO));
        inventarioComboLabel.setEnabled(selected.equals(RadiusOptions.VEHICULO));
        inventarioCombo.setEnabled(selected.equals(RadiusOptions.VEHICULO));
        lpeso.setEnabled(selected.equals(RadiusOptions.VEHICULO));
        peso.setEnabled(selected.equals(RadiusOptions.VEHICULO));
        lNuevoCo.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        lVehiculoCombo.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        vehiculoCombo.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        lnMotor.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        nMotor.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        lnCarroceria.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        nCarroceria.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        lpMotor.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        pMotor.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        lpCarroceria.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        pCarroceria.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        lcCilindro.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        cantCilindro.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        lCilindrada.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        cilindrada.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        ldCarroceria.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        descCarroceria.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        lColCarroceria.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        colCarroceria.setEnabled(selected.equals(RadiusOptions.COMPONENTE));
        crearButton.setEnabled(true);
    }

    private void resetFormControls(){
        marca.setText("");
        nMotor.setText("");
        nCarroceria.setText("");
        pMotor.setText("");
        pCarroceria.setText("");
        cantCilindro.setText("");
        cilindrada.setText("");
        descCarroceria.setText("");
        colCarroceria.setText("");
        peso.setText("");
        lNuevoCo.setEnabled(false);
        inventarioComboLabel.setEnabled(false);
        inventarioCombo.setEnabled(false);
        lnMotor.setEnabled(false);
        nMotor.setEnabled(false);
        lnCarroceria.setEnabled(false);
        nCarroceria.setEnabled(false);
        lpMotor.setEnabled(false);
        pMotor.setEnabled(false);
        lpCarroceria.setEnabled(false);
        pCarroceria.setEnabled(false);
        lcCilindro.setEnabled(false);
        cantCilindro.setEnabled(false);
        lCilindrada.setEnabled(false);
        cilindrada.setEnabled(false);
        ldCarroceria.setEnabled(false);
        descCarroceria.setEnabled(false);
        lColCarroceria.setEnabled(false);
        colCarroceria.setEnabled(false);
        lpeso.setEnabled(false);
        peso.setEnabled(false);
        lMarca.setEnabled(false);
        lNuevoInv.setEnabled(false);
        marca.setEnabled(false);
        lNuevoCo.setEnabled(false);
        lVehiculoCombo.setEnabled(false);
        vehiculoCombo.setEnabled(false);
        crearButton.setEnabled(false);
        radiusSelected=null;
        objectsForm.clearSelection();
    }

    private void creaInventario(String marca) throws Exception{
        Inventario i = new Inventario(marca);
        inventarios.add(i);
        inventarioCombo.addItem(i);
        println("Inventario "+marca+" creado.");
    }

    private void creaVehiculo(Inventario inventario, int peso){
        Vehiculo v = inventario.agregar(peso);
        vehiculoCombo.addItem(v);
        println("Vehículo creado y agregado al inventario "+ inventario);
    }

    private void creaComponente(Vehiculo vehiculo, Integer nroMotor, Integer nroCarroceria, float precioMotor, float precioCarroceria, int cantCilindro, int cilindrada, String descCarroceria, String colorCarroceria){
        Motor m = new Motor(nroMotor,precioMotor,cantCilindro,cilindrada,vehiculo);
        Carroceria c = new Carroceria(nroCarroceria,precioCarroceria,descCarroceria,colorCarroceria,vehiculo);
        vehiculo.setMotor(m);
        vehiculo.setCarroceria(c);
        println("Motor y Carroceria añadidos correctamente al vehículo seleccionado.");
    }

    private boolean validInt(JTextField input){
        String text = input.getText();
        try{
            int n = Integer.parseInt(text);
            return n>0;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private boolean validFloat(JTextField input){
        String text = input.getText();
        try{
            float n = Float.parseFloat(text);
            return n>0;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private boolean validString(JTextField input){
        String text = input.getText();
        try{
            return !text.isEmpty();
        }catch (NumberFormatException e){
            return false;
        }
    }

    private void onVehiculoSelected(){
        Vehiculo v = vehiculoCombo.getItemAt(vehiculoCombo.getSelectedIndex());
        if(v == null) return;
        if(v.fabricadoConMotor==null||v.fabricadoConCarroceria==null){
            nMotor.setEnabled(true);
            nMotor.setText("");
            nCarroceria.setEnabled(true);
            nCarroceria.setText("");
            pMotor.setEnabled(true);
            pMotor.setText("");
            pCarroceria.setEnabled(true);
            pCarroceria.setText("");
            cantCilindro.setEnabled(true);
            cantCilindro.setText("");
            cilindrada.setEnabled(true);
            cilindrada.setText("");
            descCarroceria.setEnabled(true);
            descCarroceria.setText("");
            colCarroceria.setEnabled(true);
            colCarroceria.setText("");
            crearButton.setEnabled(true);
        }else{
            nMotor.setEnabled(false);
            nMotor.setText(v.fabricadoConMotor.numero.toString());
            nCarroceria.setEnabled(false);
            nCarroceria.setText(v.fabricadoConCarroceria.numero.toString());
            pMotor.setEnabled(false);
            pMotor.setText(String.valueOf(v.fabricadoConMotor.getPrecio()));
            pCarroceria.setEnabled(false);
            pCarroceria.setText(String.valueOf(v.fabricadoConCarroceria.getPrecio()));
            cantCilindro.setEnabled(false);
            cantCilindro.setText(String.valueOf(v.fabricadoConMotor.cantCilindros));
            cilindrada.setEnabled(false);
            cilindrada.setText(String.valueOf(v.fabricadoConMotor.cilindrada));
            descCarroceria.setEnabled(false);
            descCarroceria.setText(v.fabricadoConCarroceria.descripcion);
            colCarroceria.setEnabled(false);
            colCarroceria.setText(v.fabricadoConCarroceria.color);
            crearButton.setEnabled(false);
        }
    }
}