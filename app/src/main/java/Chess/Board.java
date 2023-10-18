package Chess;

import Interfaces.boardInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board implements boardInterface {
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
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
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
        if (piece.moveValidator(oldPosition, newPosition, this)) {
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

    public List<Piece> getPiecies() {
        List<Piece> pieces = new ArrayList<>();
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                if (tablero[x][y].hasPiece()) {
                    pieces.add(tablero[x][y].getPiece());
                }
            }
        }
        return pieces;
    }
    public Board copy (){
        Board newBoard = new Board(row, column);
        for (int x=0; x<row; x++){
            for (int y=0; y<column; y++){
                newBoard.getBoard()[x][y] = new Position(x,y,tablero[x][y].getPiece());
            }
        }
        return newBoard;
    }
}
