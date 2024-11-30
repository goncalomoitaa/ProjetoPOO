package pt.iscte.poo.tests;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.io.File;

import static pt.iscte.poo.tools.Test.assertEquals;

public class TestClass {
    public static void main(String[] args) throws FileNotFoundException {
        Room room = Room.aPartirDoFicheiro(new File("src/pt/iscte/poo/tests/rooms/room1.fixture.txt"));
//        assertEquals(room.objetoNaPosicao(new Point2D(7,8)).getClass(), Background.class);
//        assertEquals(room.objetoNaPosicao(new Point2D(8,8)).getClass(), Stairs.class);
//        assertEquals(room.objetoNaPosicao(new Point2D(7,8)).isSolid(), false);
//        assertEquals(room.objetoNaPosicao(new Point2D(8,8)).isSolid(), false);
//        assertEquals(room.objetoNaPosicao(new Point2D(3,8)).getClass(), Manel.class);
//        assertEquals(room.objetoNaPosicao(new Point2D(0,0)).getClass(), Door.class);
//        assertEquals(room.objetoNaPosicao(new Point2D(0,0)).isSolid(), false);
//        assertEquals(room.objetoNaPosicao(new Point2D(2,0)).isSolid(), true);
//        assertEquals(room.objetoNaPosicao(new Point2D(5,8)).getClass(), Sword.class);
        Manel m = Manel.getUnicoManel(3,8);
        //m.move(Direction.LEFT);
        System.out.println(m.getPosition());
        assertEquals(room.objetoNaPosicao(new Point2D(5,8)).getClass(), Sword.class);
        m.move(Direction.RIGHT);
        m.move(Direction.RIGHT);
        assertEquals(room.objetoNaPosicao(new Point2D(5,8)).getClass(), Sword.class);
    }
}
