package Packet;
class BlueP{
    private Board Payday;
    private int BlueChecks;
    private int BlueCh = 0;

    Blue(int BlueChecks)
    {
        this.BlueChecks=BlueChecks;
    }
    void start(){
        Payday= new Board(Box.Empty);
    }
    Box get(Coor coor){
        return Payday.get(coor);
    }
    private void placeBlue(){
        Payday.set (Coor coor(x,y), Box.Blue);
        placeBlue();
        int BlueCh + 1;

    }

}