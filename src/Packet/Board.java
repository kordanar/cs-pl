package Packet;

class Board {
    private Box[] [] board;
    Board(Box defaultBox){
        board = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coor coor : Ranges.getAllCoords())
            board[coor.x][coor.y] = defaultBox;
    }
    Box get (Coor coor){
        if (Ranges.inRange(coor))
            return board [coor.x][coor.y];
        return null;

    }
    void set(Coor coor, Box box){
        if (Ranges.inRange(coor))
            board [coor.x][coor.y] = box;
    }
}
