package pt.iscte.poo.tests;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.io.FileNotFoundException;
import java.io.File;

import static pt.iscte.poo.tools.Test.assertEquals;
import static pt.iscte.poo.tools.Test.assertNull;

public class TestClass {
    public static void main(String[] args) throws FileNotFoundException {
        Room room = Room.aPartirDoFicheiro(new File("src/pt/iscte/poo/tests/rooms/room1.fixture.txt"));
        assertNull(room.objetoNaPosicao(new Point2D(7,8))); // Nulos não são objetos em posição
        assertEquals(room.objetoNaPosicao(new Point2D(8,8)).getClass(), Stairs.class);
        assertEquals(room.objetoNaPosicao(new Point2D(8,8)).isSolid(), false);
        assertNull(room.objetoNaPosicao(new Point2D(3,8))); // Posição do Manel, que não é objeto
        assertEquals(room.objetoNaPosicao(new Point2D(0,0)).getClass(), Door.class);
        assertEquals(room.objetoNaPosicao(new Point2D(0,0)).isSolid(), false);
        assertEquals(room.objetoNaPosicao(new Point2D(2,0)).isSolid(), true);
        Manel.getUnicoManel(3,8).move(Direction.LEFT, room); // Posicao (3, 8) tem um uma parede à esquerda, portanto não pode andar para lá
        assertEquals(Manel.getUnicoManel().getPosition(), new Point2D(3, 8));
    }
}