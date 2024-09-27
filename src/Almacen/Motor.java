package Almacen;

public class Motor extends Componente{
    public int cantCilindros;
    public  int cilindrada;

    public Motor(Integer numero, float precio, int cantCilindros, int cilindrada, Vehiculo vehiculo) {
        super(numero, precio, vehiculo);
        this.cantCilindros = cantCilindros;
        this.cilindrada = cilindrada;
    }
}
