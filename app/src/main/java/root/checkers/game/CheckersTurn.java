package root.checkers.game;

import root.common.Board;
import root.common.Player;
import root.common.Interfaces.Turn;
import root.common.Piece;
import root.common.Position;

import java.util.List;

public class CheckersTurn implements Turn {
    int dif;
    public CheckersTurn(int dif){
        this.dif = dif;
    }
    @Override
    public List<Player> nextTurn(List<Player> players, Board board, Position oldPos, Position newPos) {
        if (hasAnotherMove(board, oldPos, newPos) != board){
            return players;
        }
        else {
            List<Player> newList = players;
            changeTurn(players, newList);
            return newList;
        }
    }

    private static void changeTurn(List<Player> players, List<Player> newList) {
        for (int i = 0; i <= players.toArray().length - 1; i++) {
            if (players.get(i).getTurn()) {
                newList.set(i, players.get(i).changeTurn());
                players.get(i).changeTurn();
                if (i + 1 == players.toArray().length) {
                    newList.set(0, players.get(0).changeTurn());
                    break;
                } else {
                    newList.set(i+1, players.get(i + 1).changeTurn());
                    break;
                }
            }
        }
    }

    @Override
    public Player getCurrent(List<Player> players) {
        return players.stream().filter(Player::getTurn).findFirst().get();
    }
    private Board hasAnotherMove (Board board, Position oldpos, Position newPos){
        Piece piece = newPos.getPiece();
        Board board1 = board.copy();
        int x = oldpos.getX() - newPos.getX();
        if (Math.abs(x)  < dif){
            return board;
        }
        Board newTablero = null;
        if (moveUpRight(newPos, piece, board1)) return newTablero;
        if (moveDownRight(newPos, piece, board1)) return newTablero;
        if (moveUpLeft(newPos, piece, board1)) return newTablero;
        if (moveDownLeft(newPos, piece, board1)) return newTablero;
        return board;
    }

    private boolean moveDownLeft(Position newPos, Piece piece, Board board1) {
        Board newTablero;
        if (board1.getPosition(newPos.getX() - dif, newPos.getY() - dif) != null) {
            newTablero = board1.movePiece(newPos, board1.getPosition(newPos.getX() - dif, newPos.getY() - dif), piece);
            if (newTablero != board1) {
                return true;
            }
        }
        return false;
    }

    private boolean moveUpLeft(Position newPos, Piece piece, Board board1) {
        Board newTablero;
        if (board1.getPosition(newPos.getX() + dif, newPos.getY() - dif) != null) {
            newTablero = board1.movePiece(newPos, board1.getPosition(newPos.getX() + dif, newPos.getY() - dif), piece);
            return newTablero != board1;
        }
        return false;
    }

    private boolean moveDownRight(Position newPos, Piece piece, Board board1) {
        Board newTablero;
        if (board1.getPosition(newPos.getX() - dif, newPos.getY() + dif) != null) {
            newTablero = board1.movePiece(newPos, board1.getPosition(newPos.getX() - dif, newPos.getY() + dif), piece);
            return newTablero != board1;
        }
        return false;
    }

    private boolean moveUpRight(Position newPos, Piece piece, Board board1) {
        Board newTablero;
        if (board1.getPosition(newPos.getX() + dif, newPos.getY() + dif) != null) {
            newTablero = board1.movePiece(newPos, board1.getPosition(newPos.getX() + dif, newPos.getY() + dif), piece);
            return newTablero != board1;
        }
        return false;
    }
}
