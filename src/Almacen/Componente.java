package Almacen;

public abstract class Componente {
    public Integer numero;
    private float precio;
    private Vehiculo parte;
    public Componente(Integer numero, float precio, Vehiculo vehiculo) {
        this.numero = numero;
        this.precio = precio;
        this.parte = vehiculo;
    }
    public float getPrecio(){
        return precio;
    };
}
