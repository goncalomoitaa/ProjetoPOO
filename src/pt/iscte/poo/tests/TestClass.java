package pt.iscte.poo.tests;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.io.FileNotFoundException;
import java.io.File;

import static pt.iscte.poo.tools.Test.assertEquals;

public class TestClass {
    public static void main(String[] args) throws FileNotFoundException {
        Room room = Room.fromFile(new File("src/pt/iscte/poo/tests/rooms/room1.fixture.txt"));
        Manel m = Manel.getSingleManel(3,8);
        assertEquals(room.objectsAt(new Point2D(7,8)).size(), 0);
        assertEquals(room.objectsAt(new Point2D(3,8)).size(), 0); // Posição do Manel, que não é objeto
        assertEquals(room.objectsAt(new Point2D(8,8)).get(0).getClass(), Stairs.class);
        assertEquals(room.objectsAt(new Point2D(0,0)).get(0).getClass(), Door.class);

        assertEquals(room.solidPosition(new Point2D(8,8)), false);
        assertEquals(room.solidPosition(new Point2D(0,0)), false);
        assertEquals(room.solidPosition(new Point2D(2,0)), false);
        assertEquals(room.solidPosition(new Point2D(8, 8)), false);

        assertEquals(m.getPosition(), new Point2D(3, 8));
        m.move(Direction.LEFT, room);
        assertEquals(m.getPosition(), new Point2D(3, 8));

        m = Manel.getSingleManel(8, 8);
        assertEquals(room.climbablePosition(new Point2D(8, 8)), true);
        m.move(Direction.UP, room);
        assertEquals(room.climbablePosition(new Point2D(8, 7)), true);
    }
}