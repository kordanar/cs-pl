package Packet;

public class Game {
    private Blue blue;
    public Game(int cols, int rows){
        Ranges.setSize(new Coor(cols,rows));
        blue= new BlueP()
    }
    public void start(){
        blue.start();
    }

    public Box getBox(Coor coor){
        return blue.get(coor);

    }
    public void Turn(){
        if (BlueCh == GreenCh){
            placeBlue(Coor(x,y));
        }
        if ( BlueCh>GreenCh){
            placeGreen(Coor(x,y));
        }
    }




}
