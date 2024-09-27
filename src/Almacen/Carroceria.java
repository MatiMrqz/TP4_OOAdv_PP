package Almacen;

public class Carroceria extends Componente{
    public final String descripcion;
    public final String color;

    public Carroceria(Integer num, float Precio, String descripcion, String color, Vehiculo vehiculo) {
        super(num, Precio, vehiculo);
        this.descripcion = descripcion;
        this.color = color;
    }
}
