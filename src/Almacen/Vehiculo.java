package Almacen;

public class Vehiculo {
    public int peso;
    public Motor fabricadoConMotor;
    public Carroceria fabricadoConCarroceria;

    public Vehiculo(Integer numeroMotor, Integer numCarroceria, float precioMotor, float precioCarroceria, int cantCilindro, int cilindrada, String descCarroceria, String colorCarroceria, int peso){
        this.peso = peso;
        this.fabricadoConCarroceria = new Carroceria(numCarroceria,precioCarroceria,descCarroceria,colorCarroceria,this);
        this.fabricadoConMotor = new Motor(numeroMotor, precioMotor,cantCilindro,cilindrada,this);
    }

    public float calcularPrecio(){
        return this.fabricadoConMotor.getPrecio()+this.fabricadoConCarroceria.getPrecio();
    }

    @Override
    public String toString() {
        return "Modelo Vehículo:" + (this.fabricadoConCarroceria.descripcion) + "\n"
                + "      Descripción\n"
                + "      Color: " + this.fabricadoConCarroceria.color + "\n"
                + "      Peso: " + this.peso + " kg\n"
                + "      Cilindros: " + this.fabricadoConMotor.cantCilindros + "\n"
                + "      Cilindrada: " + this.fabricadoConMotor.cilindrada + "\n"
                + "      Precio: " + String.format("%.2f", calcularPrecio()) + "\n";
    }
}