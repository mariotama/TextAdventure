public class Personaje {

    //VARIABLES
    private int MAX_VIDA = 10;
    private int vida;

    private int dano = 4;

    private String arma = "cuchillo";


    //CONSTRUCTOR
    public Personaje(){
        vida = MAX_VIDA;
    }


    //GETTERS Y SETTERES
    public int getMAX_VIDA() {
        return MAX_VIDA;
    }

    public int getVida() {
        return vida;
    }

    public int getDano() {
        return dano;
    }

    public String getArma() {
        return arma;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }

    //FUNCIONES
    public void recibirGolpe(int dano){
        vida -= dano;
        if(vida <= 0){
            System.out.println("¡Has muerto!");
            System.out.println("Gracias por jugar.");
            System.exit(0);
        }
    }

    public void recuperarVida(int cantidad){
        vida += cantidad;
    }
}
