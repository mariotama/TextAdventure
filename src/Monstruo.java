public class Monstruo {
    private int vida = 10;

    public Monstruo() {
    }

    public int getVida() {
        return vida;
    }

    public void recibirGolpe(int dano) {
        vida -= dano;
        if (vida <= 0) {
            System.out.println("¡Has derrotado al monstruo!");
        }
    }


}
