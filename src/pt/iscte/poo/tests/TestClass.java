package pt.iscte.poo.tests;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import static pt.iscte.poo.tools.Test.assertEquals;

public class TestClass {
    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<Room> rooms = Room.carregaSalas("src/pt/iscte/poo/tests/rooms/");
        Room room = rooms.getFirst();
        assertEquals(room.objetoNaPosicao(new Point2D(7,8)).getClass(), Background.class);
        assertEquals(room.objetoNaPosicao(new Point2D(8,8)).getClass(), Stairs.class);
        assertEquals(room.objetoNaPosicao(new Point2D(7,8)).isSolid(), false);
        assertEquals(room.objetoNaPosicao(new Point2D(8,8)).isSolid(), false);
        assertEquals(room.objetoNaPosicao(new Point2D(3,8)).getClass(), Manel.class);
        assertEquals(room.objetoNaPosicao(new Point2D(0,0)).getClass(), Door.class);
        assertEquals(room.objetoNaPosicao(new Point2D(0,0)).isSolid(), false);
        assertEquals(room.objetoNaPosicao(new Point2D(2,0)).isSolid(), true);
        //room.moveManel(Direction.LEFT);
        //assertEquals(room.objetoNaPosicao(new Point2D(3,8)).getClass(), Manel.class);
    }
}
