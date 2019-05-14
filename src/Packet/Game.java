package Packet;

public class Game {
    Board Payday;
    public Game(int cols, int rows){
        Ranges.setSize(new Coor(cols,rows));
    }
    public void start(){
        Payday = new Board(Box.empty);
    }

    public Box getBox(Coor coor){
        return Payday.get(coor);

    }


}
