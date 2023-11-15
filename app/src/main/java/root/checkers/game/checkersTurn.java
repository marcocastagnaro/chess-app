package root.checkers.game;

import root.common.Board;
import root.common.Player;
import root.common.Interfaces.turn;
import root.common.Piece;
import root.common.Position;

import java.util.List;

public class checkersTurn implements turn {
    int dif;
    public checkersTurn(int dif){
        this.dif = dif;
    }
    @Override
    public List<Player> nextTurn(List<Player> players, Board board, Position oldPos, Position newPos) {
        if (hasAnotherMove(board, oldPos, newPos) != board){
            return players;
        }
        else {
            List<Player> newList = players;
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
            return newList;
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
        Board newTablero;
        if (board1.getPosition(newPos.getX() + dif, newPos.getY() + dif) != null) {
            newTablero = board1.movePiece(newPos, board1.getPosition(newPos.getX() + dif, newPos.getY() + dif), piece);
            if (newTablero != board1) {
                return newTablero;
            }
        }
        if (board1.getPosition(newPos.getX() - dif, newPos.getY() + dif) != null) {
            newTablero = board1.movePiece(newPos, board1.getPosition(newPos.getX() - dif, newPos.getY() + dif), piece);
            if (newTablero != board1) {
                return newTablero;
            }
        }
        if (board1.getPosition(newPos.getX() + dif, newPos.getY() - dif) != null) {

            newTablero = board1.movePiece(newPos, board1.getPosition(newPos.getX() + dif, newPos.getY() - dif), piece);
            if (newTablero != board1) {
                return newTablero;
            }
        }
        if (board1.getPosition(newPos.getX() - dif, newPos.getY() - dif) != null) {
            newTablero = board1.movePiece(newPos, board1.getPosition(newPos.getX() - dif, newPos.getY() - dif), piece);
            if (newTablero != board1) {
                return newTablero;
            }
        }
        return board;
    }
}
