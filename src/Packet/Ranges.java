package Packet;

import java.util.ArrayList;

public class Ranges {
    private static Coor size;
    private static ArrayList<Coor>allCoords;
    public static void setSize (Coor _size){
        size = _size;
        allCoords = new ArrayList<>();
        for (int y=0; y<size.y;y ++)
            for (int x=0; x<size.x; x++)
                allCoords.add(new Coor(x, y));

    }
    public static Coor getSize (){
        return size;
    }
    public static ArrayList<Coor> getAllCoords(){
        return allCoords;
    }
    static boolean inRange(Coor coor){
        return coor.x>=0&& coor.x<size.x&& coor.y>=0&&coor.y<size.y;
    }

}
