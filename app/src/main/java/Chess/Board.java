package Chess;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {
    private Position[][] tablero;

    private int row;
    private int column;

    public Board(int row, int column) {
        this.row = row;
        this.column = column;
        tablero = new Position[row][column];
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                tablero[x][y] = new Position(x, y, null);
            }
        }
    }

    private Board(int row, int column, Position[][] positions) {
        this.row = row;
        this.column = column;
        this.tablero = positions;
    }

    public Position[][] getBoard() {
        return tablero;
    }

    public Piece getPiece(int x, int y) {
        return tablero[x][y].getPiece();
    }
    public Position getPosition(int x, int y) {
        return tablero[x][y];
    }

    public Position getPositionWithPiece(Piece piece) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Position currentPosition = tablero[x][y];
                if (currentPosition.getPiece() != null && currentPosition.getPiece() == piece) {
                    return currentPosition;
                }
            }
        }
        return null;
    }

    public boolean tienePieza(Position position) {
        return tablero[position.getX()][position.getY()].hasPiece();
    }

    public Board movePiece(Position oldPosition, Position newPosition, Piece piece) {
        if (newPosition.hasPiece()) {
            if (!Objects.equals(newPosition.getPiece().getColor(), piece.getColor())) {
                return move(oldPosition, newPosition, piece);
            }
        } else if (!newPosition.hasPiece()) {
            return move(oldPosition, newPosition, piece);
        }
        return this;
    }

    public Board move(Position oldPosition, Position newPosition, Piece piece) {
            if (piece.moveValidator(oldPosition, newPosition) && !piece.obstacle(oldPosition, newPosition, this)) {
                Position[][] newTablero = new Position[row][column];
                for (int x = 0; x < row; x++) {
                    for (int y = 0; y < column; y++) {
                        Piece currentPiece = tablero[x][y].getPiece();
                        if (x == oldPosition.getX() && y == oldPosition.getY()) {
                            newTablero[x][y] = new Position(x, y);
                        } else if (x == newPosition.getX() && y == newPosition.getY()) {
                            newTablero[x][y] = new Position(x, y, piece);
                        } else {
                            newTablero[x][y] = new Position(x, y, currentPiece);
                        }
                    }
                }
                return new Board(row, column, newTablero);
            }
        return this;
    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Piece findKing(Color color) {
        Piece king = null;
        for(int x = 0; x<this.getRow();x++) {
        for (int y = 0; y < this.getColumn(); y++) {
            if (this.getBoard()[x][y].getPiece() != null) {
                if (this.getBoard()[x][y].getPiece().getName() == Piecies.KING && this.getBoard()[x][y].getPiece().getColor() == color) {
                    king = this.getBoard()[x][y].getPiece();
                }
            }
        }
    }
        return king;
    }
    public boolean pieceInterceptsCheck (ChessPlayer chessPlayer){
        List<Piece> livingPiecies = new ArrayList<>();
        for (Position position: chessPlayer.getLivingPiecies(this)){
            livingPiecies.add(position.getPiece());
        }
        for (Piece piece: livingPiecies){
            for (int x=0; x < this.getRow(); x++){
                for (int y=0; y< this.getColumn(); y++){
                    Position possiblePos = this.getBoard()[x][y];
                    Board newBoard = this.movePiece(this.getPositionWithPiece(piece), possiblePos, piece);
                    if (newBoard != this && !chessPlayer.isInCheck(newBoard)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

//    public Board enroqueLeft (Piece piece){
//        for (int x =0; x< getRow(); x++){
//            for (int y =0; y< this.getColumn(); y++){
//                Position position = this.getBoard()[x][y];
//                if (position.hasPiece()){
//                    if (position.getPiece().getName() == Piecies.ROOK && Objects.equals(position.getPiece().getColor(), piece.getColor())){
//                        if (position.getPiece().isFirstMove() && piece.getName()== Piecies.KING){
//                            Position newPositionRook =
//                            Position newPositionKing
//                            this.move(position, newposition) //muevo a la torre
//                            this.move()(getPiecieWithPosition(piece), newpositionking)
//                        }
//                    }
//                }
//            }
//        }
//        return this;
//    }
//    public Board enroqueRight (Piece piece)
//}
