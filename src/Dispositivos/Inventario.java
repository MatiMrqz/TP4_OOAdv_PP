package Dispositivos;

import Almacen.Vehiculo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Inventario implements Serializable {
    private static final String persistenceFolder = "inventario";
    public String marca;
    public ArrayList<Vehiculo> item = new ArrayList<>();

    public Inventario(String marca) {
        this.marca = marca;
    }

    public Vehiculo agregar(int peso){
        Vehiculo v = new Vehiculo(peso);
        this.item.add(v);
        return v;
    }

    public void listar(){
        for (Vehiculo vehiculo : item){
            Impresora.imprimir(vehiculo.toString());
        }
    }

    public void grabar(){
        ObjectOutputStream oos;
        try{
            if (item.size()==0) throw new Exception("No hay objetos para grabar");
            for (int i = 0; i < item.size(); i++) {
                oos = new ObjectOutputStream(new FileOutputStream(Inventario.persistenceFolder+"/inv_"+i+".txt"));
                oos.writeObject(item.get(i));
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void leer(){
        try{
            File f = new File(Inventario.persistenceFolder);
            File [] files = f.listFiles();
            files = Arrays.stream(files).filter(fl -> fl.getName().matches("inv_[0-9][0-9]?\\.txt")).toArray(File[]::new);
            if(files.length==0) throw new Exception("No hay objetos almacenados");
            for(File file : files){
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                item.add((Vehiculo) ois.readObject());
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return this.marca;
    }

}
