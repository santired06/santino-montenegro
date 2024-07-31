public class Carta {
    private String nombre;
    private int valorAtaque;
    private int valorDefensa;

    public Carta(String nombre, int valorAtaque, int valorDefensa) {
        this.nombre = nombre;
        this.valorAtaque = valorAtaque;
        this.valorDefensa = valorDefensa;
    }

    public String getNombre() {
        return nombre;
    }

    public int getValorAtaque() {
        return valorAtaque;
    }

    public int getValorDefensa() {
        return valorDefensa;
    }
}

public class CartaAtaque extends Carta {
    public CartaAtaque(String nombre, int valorAtaque) {
        super(nombre, valorAtaque, 0);
    }
}

public class CartaDefensa extends Carta {
    public CartaDefensa(String nombre, int valorDefensa) {
        super(nombre, 0, valorDefensa);
    }
}

public class CartaEspecial extends Carta {
    public CartaEspecial(String nombre) {
        super(nombre, 0, 0);
    }
}

import java.util.List;

public interface Jugador {
    void jugarCarta(Carta carta, Jugador oponente);
    void realizarAtaque(Jugador oponente);
    int getPuntosVida();
    void recibirAtaque(int puntosAtaque);
    boolean estaDerrotado();
}

public class JugadorDeCartas implements Jugador {
    private String nombre;
    private int puntosVida;
    private List<Carta> mano;

    public JugadorDeCartas(String nombre, int puntosVida, List<Carta> mano) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.mano = mano;
    }

    public void jugarCarta(Carta carta, Jugador oponente) {
        if (mano.contains(carta)) {
            mano.remove(carta);
        } else {
            throw new CartaNoValidaException("La carta no está en la mano del jugador.");
        }
    }

    public void realizarAtaque(Jugador oponente) {
        int puntosAtaque = 0;
        for (Carta carta : mano) {
            if (carta instanceof CartaAtaque) {
                puntosAtaque += carta.getValorAtaque();
            }
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public void recibirAtaque(int puntosAtaque) {
        puntosVida -= puntosAtaque;
    }

    public boolean estaDerrotado() {
        return puntosVida <= 0;
    }
}
