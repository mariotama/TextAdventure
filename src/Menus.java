import java.util.InputMismatchException;
import java.util.Scanner;

public class Menus {
    //Variable global de si el monstruo está vivo (TODO ver si hay otra manera de verificar esto sin variables globales)
    private static boolean monstruoVivo = true;

    private static boolean tieneEspada = false;

    //Scanner general para toda la clase
    private static Scanner leer = new Scanner(System.in);

    //TUTORIAL
    public static void menuTutorial(Personaje personaje,Monstruo monstruo){
        System.out.println("¡Hola! Bienvenido/a a esta aventura de texto en Java.");
        System.out.println("Para jugar, introduce el número que se corresponda con la opción que quieras realizar y pulsa Intro.");
        System.out.println("¡Disfruta!");
        pulsaIntro();
        menuPuerta(personaje, monstruo);
    }

    //MAPAS
    public static void menuPuerta(Personaje personaje, Monstruo monstruo) {
        saltoDeLinea();
        mostrarVida(personaje);
        System.out.println("Te encuentras delante de las puertas de la ciudad. Las puertas son custodiadas por un guardia, que te dice:");
        System.out.println("Guardia: \"Los forasteros no son bienvenidos.\"");
        System.out.println("¿Qué decides hacer?");
        System.out.println("1 - Golpear al guardia.");
        System.out.println("2 - Tratar de convencer al guardia.");
        System.out.println("3 - Marcharte de la ciudad.");

        int opcion = introduceOpcion();

        while (opcion < 1 || opcion > 3) {
            opcion = introduceOpcion();
        }

        switch (opcion) {
            case 1:
                atacarGuardia(personaje, monstruo);
                break;
            case 2:
                if(monstruoVivo){
                    System.out.println("Guardia: \"No puedo dejar pasar a ningún forastero hasta que le monstruo que vive al sur deje de atormentar la villa\"");
                    System.out.println("1 - Golpear al guardia.");
                    System.out.println("2 - Hacerle caso y marcharte.");
                    int opcion2 = introduceOpcion();
                    while (opcion2 < 1 || opcion2 > 2) {
                        opcion2 = introduceOpcion();
                    }
                    switch (opcion2) {
                        case 1:
                            atacarGuardia(personaje, monstruo);
                            break;
                        case 2:
                            menuMapamundi(personaje, monstruo);
                            break;
                    }
                }else{
                    System.out.println("Guardia: \"¡Has derrotado al monstruo! Eres libre de entrar a nuestra villa.\"");
                    System.out.println("¡Gracias por jugar! :-)");
                    pulsaIntro();
                    System.exit(0);
                }
                break;
            case 3:
                menuMapamundi(personaje, monstruo);
                break;
        }
    }

    public static void menuMapamundi(Personaje personaje, Monstruo monstruo) {
        saltoDeLinea();
        mostrarVida(personaje);
        System.out.println("Sales al camino. ¿Hacia donde te diriges?");
        System.out.println("1 - Hacia el Norte (puerta de la ciudad)");
        System.out.println("2 - Este");
        System.out.println("3 - Oeste");
        System.out.println("4 - Sur");

        int opcion = introduceOpcion();

        while (opcion < 1 || opcion > 4) {
            opcion = introduceOpcion();
        }

        switch (opcion) {
            case 1:
                menuPuerta(personaje, monstruo);
                break;
            case 2:
                menuLago(personaje, monstruo);
                break;
            case 3:
                menuEspada(personaje, monstruo);
                break;
            case 4:
                menuMonstruo(personaje, monstruo);
                break;
        }

    }

    public static void menuLago(Personaje personaje, Monstruo monstruo) {
        saltoDeLinea();
        mostrarVida(personaje);
        System.out.println("Llegas a un lago de aguas cristalinas.");
        System.out.println("¿Qué quieres hacer?");
        System.out.println("1 - Beber del agua.");
        System.out.println("2 - Marcharte de nuevo al camino.");

        int opcion = introduceOpcion();

        while (opcion < 1 || opcion > 2) {
            opcion = introduceOpcion();
        }

        switch (opcion) {
            case 1:
                System.out.println("Bebes del agua del lago...");
                if (personaje.getVida() >= personaje.getMAX_VIDA()) {
                    System.out.println("Tienes la vida al máximo, pero está muy fresca.");
                    System.out.println("Vuelves al camino...");
                } else {
                    if (personaje.getVida() == 9) {
                        System.out.println("¡Has recuperado 1 punto de vida!");
                        personaje.recuperarVida(1);
                    } else {
                        System.out.println("¡Has recuperado 2 puntos de vida!");
                        personaje.recuperarVida(2);
                    }
                }
                leer.nextLine();
                pulsaIntro();
                menuMapamundi(personaje, monstruo);
                break;
            case 2:
                menuMapamundi(personaje, monstruo);
                break;
        }
    }

    public static void menuEspada(Personaje personaje, Monstruo monstruo) {
        saltoDeLinea();
        if(!tieneEspada){
            System.out.println("Llegas a una gran roca de la cual sobresale una espada brillante.");
            System.out.println("¿Qué quieres hacer?");
            System.out.println("1 - Intentar sacar la espada");
            System.out.println("2 - Marcharte");

            int opcion = introduceOpcion();

            while (opcion < 1 || opcion > 2) {
                opcion = introduceOpcion();
            }

            switch (opcion){
                case 1:
                    int probabilidad = (int) ((Math.random() * 6) + 1);
                    if (probabilidad > 3){
                        sacarEspada(personaje, monstruo);
                    }else{
                        do{
                            System.out.println("La espada no sale...");
                            System.out.println("¿Qué quieres hacer?");
                            System.out.println("1 - Intentarlo de nuevo.");
                            System.out.println("2 - Marcharte.");
                            int opcion2 = introduceOpcion();

                            while (opcion2 < 1 || opcion2 > 2) {
                                opcion2 = introduceOpcion();
                            }

                            switch (opcion2){
                                case 1:
                                    probabilidad = (int) ((Math.random() * 6) + 1);
                                    if (probabilidad > 3) {
                                        sacarEspada(personaje, monstruo);
                                    }
                                    break;
                                case 2:
                                    menuMapamundi(personaje, monstruo);
                                    break;
                            }
                        }while(probabilidad < 3);
                        menuMapamundi(personaje, monstruo);

                    }
                    break;
                case 2:
                    menuMapamundi(personaje, monstruo);
                    break;
            }
        }else{
            System.out.println("Ves la roca sin la espada.");
            System.out.println("Vuelves al mapamundi.");
            leer.nextLine();
            pulsaIntro();
        }
    }

    public static void menuMonstruo(Personaje personaje, Monstruo monstruo) {
        saltoDeLinea();
        if(monstruoVivo){
            mostrarVida(personaje);
            System.out.println("¡Te encuentra a un monstruo!");
            System.out.println("¿Qué quieres hacer?");
            System.out.println("1 - Atacar.");
            System.out.println("2 - Huir");

            int opcion = introduceOpcion();

            while (opcion < 1 || opcion > 2) {
                opcion = introduceOpcion();
            }

            switch (opcion) {
                case 1: //Primer ataque de ambos
                    int danoPersonaje = calcularDanoPersonaje(personaje);
                    int danoMonstruo = calcularDanoMonstruo();
                    atacarMonstruo(danoPersonaje, monstruo);
                    atacarPersonaje(danoMonstruo, personaje);
                    if (monstruo.getVida() <= 0) {
                        matarMonstruo(personaje, monstruo);
                    }

                    while (personaje.getVida() > 0 || monstruo.getVida() > 0) { //Combate
                        int opcion2;
                        do {
                            vidaCombate(personaje, monstruo);
                            System.out.println("¿Qué quieres hacer?");
                            System.out.println("1 - ¡Atacar!");
                            System.out.println("2 - ¡Huir!");
                            opcion2 = introduceOpcion();

                            while (opcion2 < 1 || opcion2 > 2) {
                                opcion2 = introduceOpcion();
                            }

                            switch (opcion2) {
                                case 1:
                                    danoPersonaje = calcularDanoPersonaje(personaje);
                                    danoMonstruo = calcularDanoMonstruo();
                                    atacarMonstruo(danoPersonaje, monstruo);
                                    if (monstruo.getVida() <= 0) {
                                        matarMonstruo(personaje, monstruo);
                                    }
                                    atacarPersonaje(danoMonstruo, personaje);
                                    break;
                                case 2:
                                    menuMapamundi(personaje, monstruo);
                            }
                        } while (opcion2 == 1 && personaje.getVida() > 0 && monstruo.getVida() > 0);
                    }
                    break;
                case 2:
                    menuMapamundi(personaje, monstruo);
                    break;
            }
        }else{
            System.out.println("El monstruo yace muerto delante de ti.");
            System.out.println("Vuelves al mapamundi.");
            leer.nextLine();
            pulsaIntro();
            menuMapamundi(personaje, monstruo);
        }
    }




    //FUNCIONES
    //Mostrar vidas de personaje y monstruo
    private static void mostrarVida(Personaje personaje) {
        System.out.println("Vida: " + personaje.getVida() + "\n");
    }

    private static void mostrarVidaMonstruo(Monstruo monstruo) {
        System.out.println("Vida del monstruo: " + monstruo.getVida() + "\n");
    }

    private static void vidaCombate(Personaje personaje, Monstruo monstruo) {
        System.out.println("Vida: " + personaje.getVida() + " --------- Vida del monstruo: " + monstruo.getVida());
    }


    //Funciones de combate
    private static void atacarGuardia(Personaje personaje, Monstruo monstruo) {
        System.out.println("Intentas atacar al guardia, pero él es más rápido y te tira al suelo.");
        System.out.println("Guardia \"No hagas tonterías.\"");
        System.out.println("¡Has recibido 1 de daño y el guardia te ha echado!");
        personaje.recibirGolpe(1);
        leer.nextLine();
        pulsaIntro();
        menuMapamundi(personaje, monstruo);
    }

    private static int calcularDanoPersonaje(Personaje personaje){
        return (int) (Math.random() * personaje.getDano()) + 1;
    }

    private static int calcularDanoMonstruo(){
        return (int) (Math.random() * 3) + 1;
    }

    private static void atacarMonstruo(int danoPersonaje, Monstruo monstruo) {
        System.out.println("¡Atacas a la bestia!");
        monstruo.recibirGolpe(danoPersonaje);
        if(monstruo.getVida() > 0){
            System.out.println("El monstruo recibe " + danoPersonaje + " de daño.");
            leer.nextLine();
        }else{
            System.out.println("La villa está a salvo.");
            leer.nextLine();    //Limpiamos de búfer
        }

    }

    private static void atacarPersonaje(int danoMonstruo, Personaje personaje) {
        System.out.println("¡La bestia te ataca!");
        personaje.recibirGolpe(danoMonstruo);
        System.out.println("Recibes " + danoMonstruo + " de daño.");
        pulsaIntro();
    }

    private static void matarMonstruo(Personaje personaje, Monstruo monstruo){
            monstruoVivo = false;
            System.out.println("Vuelves al camino.");
            pulsaIntro();
            menuMapamundi(personaje, monstruo);
    }


    //Funciones del menú espada
    private static void sacarEspada(Personaje personaje, Monstruo monstruo){
        System.out.println("¡Conseguiste sacar la espada!");
        System.out.println("Tu ataque ha aumentado.");
        personaje.setDano(5);
        personaje.setArma("espada");
        tieneEspada = true;
        leer.nextLine();
        pulsaIntro();
        menuMapamundi(personaje, monstruo);
    }


    //Funciones de menú
    private static void pulsaIntro(){
        System.out.println("-Pulsa Intro-");
        leer.nextLine();
    }

    private static void saltoDeLinea() {
        System.out.println();
        System.out.println("---------------------------------");
        System.out.println();
    }

    private static int introduceOpcion() {
        int opcion;
        do {
            System.out.println("Introduce una opción.");
            try {
                opcion = leer.nextInt();
                break;      //Salir del bucle si no hay excepción
            } catch (InputMismatchException e) {
                leer.nextLine();
            }
        }while(true);

        return opcion;
    }       //Controla excepciones también
}

