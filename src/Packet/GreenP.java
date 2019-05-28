package Packet;
class GreenP{
    private Board Payday;
    private int GreenChecks;
    private int GreenCh = 0;

    Blue(int GreenChecks)
    {
        this.BlueChecks=GreenChecks;
    }
    void start(){
        Payday= new Board(Box.Empty);
    }
    Box get(Coor coor){
        return Payday.get(coor);
    }
    private void placeGreen(){
        Payday.set (Coor coor(x,y), Box.Green);
        placeGreen();
        int GreenCh+1;

    }

}