package Almacen;

public class Vehiculo {
    private static int count = 0;
    public int id;
    public int peso;
    public Motor fabricadoConMotor;
    public Carroceria fabricadoConCarroceria;

    public Vehiculo(int peso){
        this.id = ++count;
        this.peso = peso;
    }

    public void setMotor(Motor m){
        this.fabricadoConMotor = m;
    }

    public void setCarroceria(Carroceria c){
        this.fabricadoConCarroceria = c;
    }

    public float calcularPrecio(){
        return this.fabricadoConMotor.getPrecio()+this.fabricadoConCarroceria.getPrecio();
    }

    public String getDetalle(){
        if(this.fabricadoConMotor==null||this.fabricadoConCarroceria==null){
            return "Vehiculo "+this.id;
        }
        return "Modelo Vehículo "+this.id+":" + (this.fabricadoConCarroceria.descripcion) + "\n"
                + "      Descripción\n"
                + "      Color: " + this.fabricadoConCarroceria.color + "\n"
                + "      Peso: " + this.peso + " kg\n"
                + "      Cilindros: " + this.fabricadoConMotor.cantCilindros + "\n"
                + "      Cilindrada: " + this.fabricadoConMotor.cilindrada + "\n"
                + "      Precio: " + String.format("%.2f", calcularPrecio()) + "\n";
    }

    @Override
    public String toString() {
        return "Vehículo "+this.id;
    }
}